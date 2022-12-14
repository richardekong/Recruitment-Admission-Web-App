package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.HistoricalData;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public interface HistoricalDataExcelSheetService {

    void writeHeaderRow();
    void createExcelSheet(HttpServletResponse response) throws IOException;

    List<HistoricalData> saveAll(LinkedList<HistoricalData> historicalDataLinkedList);
}
