package com.example.tesis.process;

import com.example.tesis.domain.ChequesPago;
import com.example.tesis.service.FileChequesPagoServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


/**
 * Este metodo atrapa el archivo "cheques_pagos_mes"
 */
@ComponentScan(basePackages = {"com.example.tesis.service"})
public class FileProcessChecksImpl implements FileProcess {

    @Autowired
    private FileChequesPagoServiceImpl fileChequesService;

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

        ChequesPago chequesPago = new ChequesPago();

        chequesPago.setFecha(row.getCell(0).getDateCellValue());
        chequesPago.setFecha_vto(row.getCell(1).getDateCellValue());
        chequesPago.setId_banco((long) row.getCell(2).getNumericCellValue());
        chequesPago.setDescrip_banco(row.getCell(3).getStringCellValue());
        chequesPago.setId_cliente((long) row.getCell(6).getNumericCellValue());
        chequesPago.setDescrip_cliente(row.getCell(7).getStringCellValue());
        chequesPago.setCheque_nro((long) row.getCell(11).getNumericCellValue());
        chequesPago.setImporte(row.getCell(12).getNumericCellValue());
        chequesPago.setEstado_cheque(row.getCell(13).getStringCellValue());

        this.fileChequesService.insertNewFile(chequesPago);

    }


}