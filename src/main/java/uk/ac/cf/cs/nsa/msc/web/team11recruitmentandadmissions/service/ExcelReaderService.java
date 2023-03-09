package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.converter.DateConverter;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.*;

import java.io.InputStream;
import java.util.LinkedList;

public interface ExcelReaderService {

    LinkedList<Candidate> readCandidatesFromExcelSheet(InputStream inputStream);

    LinkedList<HistoricalData> readHistoricalDataFromExcelSheet(InputStream inputStream);

}
