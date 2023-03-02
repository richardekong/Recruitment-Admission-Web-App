package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.PlacesOffered;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class SummaryController {
    private CandidateService candidateService;

    private ExcelWriterService excelWriterService;


    @Autowired
    void setCandidateService(CandidateService service) {
        this.candidateService = service;
    }
    @Autowired
    public void setExcelWriterService(ExcelWriterService excelWriterService) {
        this.excelWriterService = excelWriterService;
    }

    @GetMapping("/summary/download_excel")
    public void exportDataIntoExcelSheet(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=candidates.xlsx";
        response.setHeader(headerKey, headerValue);
        excelWriterService.createExcelSheet(response, candidateService.findAll());
    }

}
