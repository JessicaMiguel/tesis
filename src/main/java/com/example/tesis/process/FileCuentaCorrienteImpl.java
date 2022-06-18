package com.example.tesis.process;

import com.example.tesis.domain.CuentaCorriente;
import com.example.tesis.definition.ColumnsFile;
import com.example.tesis.service.FileCuentaCorrienteServiceImpl;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

@ComponentScan(basePackages = {"com.example.tesis.service"})
public class FileCuentaCorrienteImpl extends FileProcessImpl implements FileProcess
{
    private CuentaCorriente cuentaCorrienteCliente = new CuentaCorriente();

    @Autowired
    private FileCuentaCorrienteServiceImpl cuentaCorrienteService;

    @Autowired
    private FileProcessImpl fileProcess;

    @Override
    public void readMassiveFile(File file) {

        Workbook workbook = null;
        FileInputStream fileFIS = null;

        Sheet sheet = workbook.getSheetAt(workbook.getFirstVisibleTab());
        Iterator<Row> rowIterator = sheet.rowIterator();

        for(int i=0; i<8; i++){
            rowIterator.next();
        }

        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            String cellK = row.getCell(ColumnsFile.columnK).getStringCellValue(); //SALDO CLIENTE
            String cellI = row.getCell(ColumnsFile.columnI).getStringCellValue(); //SALDO ANTERIOR
            String cellG = row.getCell(ColumnsFile.columnG).getStringCellValue(); //contiene estados del recibo

            if(cellK.isEmpty() && cellI.isEmpty() && cellG.isEmpty()){
                readInfoClient(row);
            } else if (cellK.isEmpty() && cellI.isEmpty()) { //leo recibos
                recordLoadMassiveFile(row);
            }
        }
    }

    /**
     * Se encarga de leer los recibos
     * @param row
     */
    @Override
    public void recordLoadMassiveFile(Row row) {
        CuentaCorriente cuentaCorriente = new CuentaCorriente();

        cuentaCorriente.setId_cliente(this.cuentaCorrienteCliente.getId_cliente());
        cuentaCorriente.setRazon_social(this.cuentaCorrienteCliente.getRazon_social());
        cuentaCorriente.setDireccion_cliente(this.cuentaCorrienteCliente.getDireccion_cliente());
        cuentaCorriente.setTelefono_cliente(this.cuentaCorrienteCliente.getTelefono_cliente());

        cuentaCorriente.setFecha_pago(row.getCell(ColumnsFile.columnA).getDateCellValue());
        cuentaCorriente.setFecha_vto(row.getCell(ColumnsFile.columnB).getDateCellValue());
        cuentaCorriente.setSucursal((long) row.getCell(ColumnsFile.columnC).getNumericCellValue());
        cuentaCorriente.setComprobante(row.getCell(ColumnsFile.columnD).getStringCellValue());
        cuentaCorriente.setCuota((long) row.getCell(ColumnsFile.columnF).getNumericCellValue());
        cuentaCorriente.setEstado(row.getCell(ColumnsFile.columnG).getStringCellValue());
        cuentaCorriente.setConcepto(row.getCell(ColumnsFile.columnH).getStringCellValue());
        cuentaCorriente.setTot_gen(row.getCell(ColumnsFile.columnJ).getNumericCellValue());
        cuentaCorriente.setDebe(row.getCell(ColumnsFile.columnL).getNumericCellValue());
        cuentaCorriente.setHaber(row.getCell(ColumnsFile.columnM).getNumericCellValue());

        this.cuentaCorrienteService.insertNewFile(cuentaCorriente);

    }

    /**
     * Se encarga de leer el id cliente, razon social, direccion y telefono
     */
    private void readInfoClient(Row row){

        this.cuentaCorrienteCliente.setId_cliente((long) row.getCell(ColumnsFile.columnA).getNumericCellValue());
        this.cuentaCorrienteCliente.setRazon_social(row.getCell(ColumnsFile.columnB).getStringCellValue());
        this.cuentaCorrienteCliente.setDireccion_cliente(row.getCell(ColumnsFile.columnH).getStringCellValue());
        this.cuentaCorrienteCliente.setTelefono_cliente(row.getCell(ColumnsFile.columnM).getStringCellValue());

    }

}