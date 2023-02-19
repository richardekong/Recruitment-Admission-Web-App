package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.HistoricalData;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ExcelWriterUtils {

    public static boolean writeHistoricalData(XSSFSheet sheet, List<HistoricalData> historicalData) {
        AtomicInteger rowIndex = new AtomicInteger(1);
        AtomicInteger col = new AtomicInteger();
        int colsPerRow = historicalData.get(0).getClass().getDeclaredFields().length;
        writeHeaderRow(sheet, historicalData.get(0).getClass());
        int totalRowCount = historicalData.size();
        historicalData.forEach(data -> {
            XSSFRow row = sheet.createRow(rowIndex.getAndIncrement());
            col.set(0);
            Arrays.stream(data.getClass().getDeclaredFields())
                    .forEach(field -> {
                        field.setAccessible(true);
                        try {
                            Object value = field.get(data);
                            XSSFCell cell = row.createCell(col.getAndIncrement());
                            if (value instanceof Integer)
                                cell.setCellValue((Integer) value);
                            if (value instanceof Double)
                                cell.setCellValue((Double) value);

                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    });

        });

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
