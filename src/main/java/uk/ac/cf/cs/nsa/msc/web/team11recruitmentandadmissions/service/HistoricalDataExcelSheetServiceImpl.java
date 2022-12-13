package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateHistoricalDataExcelSheetImpl implements CreateHistoricalDataExcelSheet{
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public CreateHistoricalDataExcelSheetImpl() {
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
}
