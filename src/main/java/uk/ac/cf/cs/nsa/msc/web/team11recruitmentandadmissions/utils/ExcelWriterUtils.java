package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

public class ExcelWriterUtils {

    public static <T> boolean writeData(HttpServletResponse response, List<T> data) throws IOException {
        int colsPerRow = data.get(0).getClass().getDeclaredFields().length;
        int totalRowCount = data.size();
        AtomicInteger rowIndex = new AtomicInteger(1);
        AtomicInteger col = new AtomicInteger();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("HistoricalDataExcelSheet");
        writeHeaderRow(sheet, data.get(0).getClass());
        data.forEach(datum -> {
            XSSFRow row = sheet.createRow(rowIndex.getAndIncrement());
            col.set(0);
            Arrays.stream(datum.getClass().getDeclaredFields())
                    .forEach(field -> {
                        boolean wasAccessible = false;
                        if (!field.canAccess(datum)) {
                            field.setAccessible(true);
                            wasAccessible = true;
                        }
                        try {
                            Object value = field.get(datum);
                            XSSFCell cell = row.createCell(col.getAndIncrement());
                            if (value instanceof Integer)
                                cell.setCellValue((Integer) value);
                            else if (value instanceof Double)
                                cell.setCellValue((Double) value);
                            else if (value instanceof ApplicationStatusCode)
                                cell.setCellValue(((ApplicationStatusCode) value).getCode());
                            else if (value instanceof FeeStatus)
                                cell.setCellValue(((FeeStatus) value).getFeeStatus());
                            else if (value instanceof Gender)
                                cell.setCellValue(((Gender) value).getGender());
                            else if (value instanceof WelshSpeaker)
                                cell.setCellValue(((WelshSpeaker) value).getResponse());
                            else if (value instanceof YesOrNoOption)
                                cell.setCellValue(((YesOrNoOption) value).getOption());
                            else if (value instanceof LocalDate) {
                                int year = ((LocalDate) value).getYear();
                                int month = ((LocalDate) value).getMonthValue();
                                int day = ((LocalDate) value).getDayOfMonth();
                                cell.setCellValue(format("%d-%d-%d", year, month, day));
                            } else {
                                cell.setCellValue((String) value);
                            }

                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                        if (wasAccessible) {
                            field.setAccessible(false);
                        }
                    });

        });
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        return (rowIndex.decrementAndGet() == totalRowCount)
                && (colsPerRow == col.get());
    }

    private static void writeHeaderRow(XSSFSheet sheet, Class<?> struct) {
        AtomicInteger columns = new AtomicInteger(0);
        XSSFRow row = sheet.createRow(0);
        Arrays.stream(struct.getDeclaredFields())
                .forEach(field -> {
                    row.createCell(columns.getAndIncrement())
                            .setCellValue(field.getName());
                });
    }

}
