package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.converter.DateConverter;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.*;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.CandidateRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class ExcelReaderServiceImpl implements ExcelReaderService {
    private DataFormatter cellFormatter;

    private DateConverter dateConverter;

    @Autowired
    void setCellFormatter(DataFormatter cellFormatter){
        this.cellFormatter = cellFormatter;
    }

    @Autowired
    void setDateConverter(DateConverter converter) {
        this.dateConverter = converter;
    }

    @Override
    public LinkedList<Candidate> readCandidatesFromExcelSheet(InputStream inputStream) {
        LinkedList<Candidate> candidates = new LinkedList<>();
        try {
            XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
            XSSFSheet xssfSheet = workBook.getSheet(workBook.getSheetName(0));
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
                    readFromCurrentCell(cellIndex, candidate, cell, cellFormatter, dateConverter);
                    cellIndex++;
                }

                candidates.add(candidate);
            }
        }catch (IOException e){
            e.getStackTrace();
        }
        return candidates;
    }

    @Override
    public LinkedList<HistoricalData> readHistroicalDataFromExcelSheet(InputStream inputStream) {
        LinkedList<HistoricalData> historicalDataLinkedList = new LinkedList<>();
        try{
            XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workBook.getSheet(workBook.getSheetName(0));
            int rowIndex = 0;
            for(Row row : sheet){
                if(rowIndex == 0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                HistoricalData historicalData = new HistoricalData();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 :
                            historicalData.setAcademicYear((int) cell.getNumericCellValue());
                            break;
                        case 1 :
                            historicalData.setFundedPlaces((int) cell.getNumericCellValue());
                            break;
                        case 2 :
                            historicalData.setOffersMade((int) cell.getNumericCellValue());
                            historicalData.setRatio();
                            break;
                        default: {
                            break;
                        }
                    }
                    cellIndex++;
                }
                historicalDataLinkedList.add(historicalData);
            }
        } catch (IOException e){
            e.getStackTrace();
        }
        return historicalDataLinkedList;
    }

}
