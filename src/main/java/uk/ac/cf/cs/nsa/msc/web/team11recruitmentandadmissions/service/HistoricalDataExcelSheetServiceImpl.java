package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.HistoricalData;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.CandidateRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.HistoricalDataRepository;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricalDataExcelSheetServiceImpl implements HistoricalDataExcelSheetService {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private HistoricalDataRepository repository;

    @Autowired
    void setRepository(HistoricalDataRepository repository) {
        this.repository = repository;
    }

    public HistoricalDataExcelSheetServiceImpl() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("HistoricalDataExcelSheet");
    }

    @Override
    public void writeHeaderRow() {
        // Header Row
        Row row = sheet.createRow(0);


        // First cell in the header
        Cell cell = row.createCell(0);
        cell.setCellValue("academicYear");

        // Second cell in the header
        cell = row.createCell(1);
        cell.setCellValue("fundedPlaces");

        // Third cell in the header
        cell = row.createCell(2);
        cell.setCellValue("offersMade");


    }

    @Override
    public void createExcelSheet(HttpServletResponse response) throws IOException {
        writeHeaderRow();


        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

    @Override
    public List<HistoricalData> saveAll(LinkedList<HistoricalData> historicalDataLinkedList) {
        List<HistoricalData> unsavedHistoricalData = historicalDataLinkedList.stream().filter(historicalData -> !repository.existsById(historicalData.getAcademicYear()))
                .collect(Collectors.toList());
        return repository.saveAll(unsavedHistoricalData);
    }
}
