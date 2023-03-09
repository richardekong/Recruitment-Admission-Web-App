package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.converter.DateConverter;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.HistoricalData;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.utils.ExcelReaderUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

@Service
public class ExcelReaderServiceImpl implements ExcelReaderService {
    private DataFormatter cellFormatter;

    private DateConverter dateConverter;

    @Autowired
    void setCellFormatter(DataFormatter cellFormatter) {
        this.cellFormatter = cellFormatter;
    }

    @Autowired
    void setDateConverter(DateConverter converter) {
        this.dateConverter = converter;
    }

    @Override
    public LinkedList<Candidate> readCandidatesFromExcelSheet(InputStream inputStream) {
        LinkedList<Candidate> candidates;
        try {
            candidates = ExcelReaderUtils.readCandidatesFromExcelSheet(
                    inputStream,
                    cellFormatter,
                    dateConverter
            );
        } catch (IOException e) {
            throw new CustomException(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return candidates;
    }

    @Override
    public LinkedList<HistoricalData> readHistoricalDataFromExcelSheet(InputStream inputStream) {
        LinkedList<HistoricalData> historicalData;
        try {
            historicalData = ExcelReaderUtils.readHistoricalDataFromExcelSheet(inputStream);
        } catch (IOException ioe) {
            throw new CustomException(
                    ioe.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return historicalData;
    }

}
