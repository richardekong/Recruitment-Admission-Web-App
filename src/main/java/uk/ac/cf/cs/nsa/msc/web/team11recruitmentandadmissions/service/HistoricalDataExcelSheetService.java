package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HistoricalDataExcelSheetService {

    void writeHeaderRow();
    void createExcelSheet(HttpServletResponse response) throws IOException;
}
