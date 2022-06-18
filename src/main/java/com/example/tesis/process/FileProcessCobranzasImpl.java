package com.example.tesis.process;

import com.example.tesis.domain.Cobranza;
import com.example.tesis.service.FileCobranzasServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@ComponentScan(basePackages = {"com.example.tesis.service"})
public class FileProcessCobranzasImpl implements FileProcess{

    @Autowired
    private FileCobranzasServiceImpl fileService;

    @Override
    public void readMassiveFile(File file) {
        Workbook workbook = null;
        FileInputStream fileFIS = null;

        try {
            fileFIS = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileFIS);

            Sheet firstSheet = workbook.getSheetAt(workbook.getFirstVisibleTab());
            Iterator<Row> rowIterator = firstSheet.rowIterator();
            //Se tienen que ignorar las primeras 6 lineas
            //Habría que intentar que esas lineas no estan
            for (int i=0; i<5; i++){
                rowIterator.next();
            }

            List<Cobranza> chequesCreate = new ArrayList<>();

            while(rowIterator.hasNext()){
                Row recordRow = rowIterator.next();
                Cell cell0 = recordRow.getCell(0);
                if(!cell0.getDateCellValue().toString().isEmpty()){
                    recordLoadMassiveFile(recordRow);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void recordLoadMassiveFile(Row row) {

        Cobranza cobranza = new Cobranza();

        cobranza.setFecha_recibo(row.getCell(0).getDateCellValue());
        cobranza.setFecha_cobro(row.getCell(1).getDateCellValue());
        cobranza.setSucursal((long) row.getCell(3).getNumericCellValue());
        cobranza.setComprobante(row.getCell(4).getStringCellValue());
        cobranza.setCod_cliente((long) row.getCell(5).getNumericCellValue());
        cobranza.setNum_cliente((long) row.getCell(6).getNumericCellValue());
        cobranza.setId_cliente((long) row.getCell(7).getNumericCellValue());
        cobranza.setRazon_social(row.getCell(8).getStringCellValue());
        cobranza.setImporte(row.getCell(12).getNumericCellValue());

        this.fileService.insertNewFile(cobranza);

    }
}