package com.example.tesis.process;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.util.Iterator;

public abstract class FileProcessImpl implements FileProcess{

    static int firstCell = 0;

    public int searchNextInfo(Sheet sheet, int rowNumber){
        int index = rowNumber;
        Iterator<Row> rowIterator = sheet.iterator();
        boolean found = false;

        while(rowIterator.hasNext() && rowIterator.next().getRowNum()!=rowNumber){
            Row row = rowIterator.next();
        }
        while (rowIterator.hasNext() && !found) {
            Row row = rowIterator.next();
            Cell cell = row.getCell(firstCell);
            if (!cell.getStringCellValue().isEmpty()) {
                found = true;
            }
            index = row.getRowNum();
        }

        return index;
    }

    public boolean isRowEmpty(Row row){
        boolean isEmpty = false;
        if(row != null){
            for(Cell cell : row){
                if(cell.getStringCellValue().isEmpty()){
                    isEmpty = true;
                }
            }
        }
        return isEmpty;
    }

}