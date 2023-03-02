package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.HistoricalData;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.PlacesOffered;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.SummaryFragmentModel;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.Response;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Optional;

@Controller
public class SettingsController implements SummaryFragmentModel {

    private FileUploadService fileUploadService;
    private ExcelReaderService excelReaderService;

    private CandidateService candidateService;

    private PlacesOfferedService placesOfferedService;

    private PredictionService predictionService;

    private ExcelWriterService excelWriterService;

    private HistoricalDataService historicalDataService;

    @Autowired
    void setFileUploadService(FileUploadService service) {
        fileUploadService = service;
    }

    @Autowired
    public void setExcelReaderService(ExcelReaderService excelReaderService) {
        this.excelReaderService = excelReaderService;
    }
    @Autowired
    public void setExcelWriterService(ExcelWriterService excelWriterService) {
        this.excelWriterService = excelWriterService;
    }
    @Autowired
    public void setHistoricalDataService(HistoricalDataService historicalDataService) {
        this.historicalDataService = historicalDataService;
    }

    @Autowired
    void setCandidateService(CandidateService service) {
        this.candidateService = service;
    }

    @Autowired
    void setPlacesOfferedService(PlacesOfferedService service) {
        placesOfferedService = service;
    }

    @Autowired
    void setPredictionService(PredictionService service) {
        this.predictionService = service;
    }


    @GetMapping("/settings")
    public String showSettingsPage(Model model) {
        setModelsAttributesForSummaryFragment(model, predictionService, placesOfferedService, candidateService);
        return "Settings";
    }

    @ModelAttribute("Response")
    @PostMapping("/settings")
    public String handleFileUpload(
            @RequestParam
            LinkedHashMap<String, MultipartFile> filesFromForm,
            @RequestParam Integer placesOffered,
            Model model) {

        boolean anyFileIsEmpty = filesFromForm
                .values()
                .parallelStream()
                .anyMatch(MultipartFile::isEmpty);

        if (anyFileIsEmpty) {
            String message = "Trying to upload an invalid or empty file";
            model.addAttribute("error", new Response(
                    HttpStatus.FORBIDDEN.value(),
                    message,
                    System.currentTimeMillis()));
            throw new CustomException(
                    message,
                    HttpStatus.FORBIDDEN);
        }

        if (placesOffered != null) {
            placesOfferedService.savePlacesOffered(new PlacesOffered(placesOffered));
        }

        LinkedHashMap<String, InputStream> mapOfStreamsFromFiles = fileUploadService
                .uploadFileInputStreams(filesFromForm);

        Optional<LinkedList<HistoricalData>> unconfirmedHistoricalData = mapOfStreamsFromFiles
                .entrySet()
                .parallelStream()
                .filter(streamEntry -> streamEntry.getKey().equals("historical-spread-sheet-file"))
                .map(streamEntry -> excelReaderService.readHistoricalDataFromExcelSheet(streamEntry.getValue()))
                .findFirst();

        Optional<LinkedList<Candidate>> unconfirmedCurrentCandidates = mapOfStreamsFromFiles
                .entrySet()
                .parallelStream()
                .filter(streamEntry -> streamEntry.getKey().equals("current-spread-sheet-file"))
                .map(streamEntry -> excelReaderService.readCandidatesFromExcelSheet(streamEntry.getValue()))
                .findFirst();

        if (unconfirmedHistoricalData.isPresent() && unconfirmedCurrentCandidates.isPresent()) {
            model.addAttribute("success", new Response(HttpStatus.OK.value(), "Upload successful",
                    System.currentTimeMillis()));
            // Here add all excel sheet information which is stored in LinkedList<HistoricalData>
            // INTO Database #Done by Faisal this section
            historicalDataService.saveAll(unconfirmedHistoricalData.get());
            // Here add all excel sheet information which is stored in LinkedList<Candidate>
            // INTO Database #Done by Faisal this section
            candidateService.saveAll(unconfirmedCurrentCandidates.get());
        } else {
            model.addAttribute("error", new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Upload Failure",
                    System.currentTimeMillis()));
        }

        model.addAttribute("success", new Response(HttpStatus.OK.value(), "Upload successful",
                System.currentTimeMillis()));
        setModelsAttributesForSummaryFragment(model, predictionService, placesOfferedService, candidateService);
        return "redirect:/settings";
    }

    @GetMapping("/download_excel")
    public void createExcelSheet(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=historicalCandidatesSheet.xlsx";
        response.setHeader(headerKey, headerValue);
        excelWriterService.createExcelSheet(response, historicalDataService.findAll());
    }

    @Override
    public void setModelsAttributesForSummaryFragment(
            Model model,
            PredictionService predictionService,
            PlacesOfferedService placesOfferedService,
            CandidateService candidateService) {
        SummaryFragmentModel.super.setModelsAttributesForSummaryFragment(model, predictionService, placesOfferedService, candidateService);
    }


}

