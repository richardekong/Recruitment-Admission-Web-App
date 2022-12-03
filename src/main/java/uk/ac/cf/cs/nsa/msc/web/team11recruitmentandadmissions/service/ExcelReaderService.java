package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import java.io.InputStream;
import java.util.LinkedList;

public interface ExcelReaderService {
    LinkedList<Candidate> readCandidatesFromExcelSheet(InputStream inputStream);
}
