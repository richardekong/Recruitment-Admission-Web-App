package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;

@Service
public class ExcelReaderServiceImpl implements ExcelReaderService {


    private DataFormatter cellFormatter;

    @Autowired
    void setCellFormatter(DataFormatter cellFormatter){
        this.cellFormatter = cellFormatter;
    }

    @Override
    public LinkedList<Candidate> readCandidatesFromExcelSheet(InputStream inputStream) {
        LinkedList<Candidate> candidates = new LinkedList<>();
        try {
            XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
            XSSFSheet xssfSheet = workBook.getSheet(Candidate.class.getSimpleName());
            int rowIndex = 0;
            for (Row row : xssfSheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Candidate candidate = new Candidate();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    readFromCurrentCell(cellIndex, candidate, cell, cellFormatter);
                    cellIndex++;
                }

                candidates.add(candidate);
            }
        }catch (IOException e){
            e.getStackTrace();
        }
        return candidates;
    }

}
