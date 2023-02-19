package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.HistoricalData;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.HistoricalDataRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.utils.ExcelWriterUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExcelWriterServiceImpl implements ExcelWriterService {
    private HistoricalDataRepository repository;

    @Autowired
    void setRepository(HistoricalDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean createExcelSheet(HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("HistoricalDataExcelSheet");
        boolean hasWrittenToExcel = ExcelWriterUtils.writeHistoricalData(sheet, repository.findAll());
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        return hasWrittenToExcel;
    }



}
