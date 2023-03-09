package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.HistoricalData;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public interface ExcelWriterService {
    <T> boolean createExcelSheet(HttpServletResponse response, List<T> data) throws IOException;
}
