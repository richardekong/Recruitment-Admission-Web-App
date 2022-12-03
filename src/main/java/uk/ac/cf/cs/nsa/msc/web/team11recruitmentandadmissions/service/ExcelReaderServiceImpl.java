package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;

@Service
public class ExcelReaderServiceImpl implements ExcelReaderService{
    @Override
    public LinkedList<Candidate> readCandidatesFromExcelSheet(InputStream inputStream) {
        LinkedList<Candidate> candidates = new LinkedList<>();
        try{
            XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
            XSSFSheet xssfSheet = workBook.getSheet(Candidate.class.getSimpleName());
            int rowIndex = 0;
            for(Row row : xssfSheet){
                if(rowIndex == 0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Candidate candidate = new Candidate();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 2: candidate.setRecordFirstCreated(LocalDate.parse(cell.getStringCellValue()));
                        case 3: candidate.setEntryYear(cell.getStringCellValue());
                        case 4: candidate.setStudentNo(cell.getStringCellValue());
                        case 5: candidate.setPersonalID(cell.getStringCellValue());
                        //case 6: candidate.setApplic
                    }
                }

            }
        }
    }
}
