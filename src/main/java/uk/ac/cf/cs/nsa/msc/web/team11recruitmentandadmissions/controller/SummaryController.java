package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.CandidateService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.ExcelExporterServiceImpl;

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
    public void exoprtDataIntoExcelSheet(HttpServletResponse resopnse) throws IOException {
        resopnse.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=candidates.xlsx";

        resopnse.setHeader(headerKey, headerValue);

        List<Candidate> candidateList = candidateService.findAll();
        ExcelExporterServiceImpl excelExporterService = new ExcelExporterServiceImpl(candidateList);
        excelExporterService.export(resopnse);

    }

}
