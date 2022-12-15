package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.PlacesOffered;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.CandidateService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.ExcelExporterServiceImpl;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.PlacesOfferedService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.PredictionService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class SummaryController {
    private CandidateService candidateService;


    @Autowired
    void setCandidateService(CandidateService service) {
        this.candidateService = service;
    }

    @GetMapping("/export")
    public void exportDataIntoExcelSheet(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=candidates.xlsx";

        response.setHeader(headerKey, headerValue);

        List<Candidate> candidateList = candidateService.findAll();
        ExcelExporterServiceImpl excelExporterService = new ExcelExporterServiceImpl(candidateList);
        excelExporterService.export(response);

    }




}
