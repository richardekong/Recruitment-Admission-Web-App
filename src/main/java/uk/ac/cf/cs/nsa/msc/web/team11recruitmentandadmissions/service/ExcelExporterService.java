package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ExcelExporterService {

    void writeHeaderRow();

    void writeDataRows();

    void export(HttpServletResponse response) throws IOException;

}
