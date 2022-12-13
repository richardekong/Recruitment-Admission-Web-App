package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CreateHistoricalDataExcelSheet {

    void writeHeaderRow();
    void createExcelSheet(HttpServletResponse response) throws IOException;
}
