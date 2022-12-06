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
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.Response;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.ExcelReaderService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.FileUploadService;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Optional;

@Controller
public class SettingsController {

    private FileUploadService fileUploadService;
    private ExcelReaderService excelReaderService;

    @Autowired
    void setFileUploadService(FileUploadService service) {
        fileUploadService = service;
    }

    @Autowired
    void setExcelReaderService(ExcelReaderService service) {
        excelReaderService = service;
    }

    @GetMapping("/settings")
    public String showSettingsPage() {
        return "Settings";
    }

    @ModelAttribute("Response")
    @PostMapping("/settings")
    public String handleFileUpload(
            @RequestParam
            LinkedHashMap<String, MultipartFile> filesFromForm, Model model) {

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

        LinkedHashMap<String, InputStream> mapOfStreamsFromFiles = fileUploadService
                .uploadFileInputStreams(filesFromForm);

        Optional<LinkedList<Candidate>> unconfirmedHistoricalCandidates = mapOfStreamsFromFiles
                .entrySet()
                .parallelStream()
                .filter(streamEntry -> streamEntry.getKey().equals("historical-spread-sheet-file"))
                .map(streamEntry -> excelReaderService.readCandidatesFromExcelSheet(streamEntry.getValue()))
                .findFirst();

        Optional<LinkedList<Candidate>> unconfirmedCurrentCandidates = mapOfStreamsFromFiles
                .entrySet()
                .parallelStream()
                .filter(streamEntry -> streamEntry.getKey().equals("current-spread-sheet-file"))
                .map(streamEntry -> excelReaderService.readCandidatesFromExcelSheet(streamEntry.getValue()))
                .findFirst();

        if (unconfirmedHistoricalCandidates.isPresent() && unconfirmedCurrentCandidates.isPresent()) {
            System.out.println("HISTORICAL CANDIDATE DATA");
            System.out.println(unconfirmedHistoricalCandidates.get() + "\n\n\n\n");
            System.out.println("CURRENT CANDIDATE DATA");
            System.out.println(unconfirmedCurrentCandidates.get() + "\n\n\n\n");
            model.addAttribute("success", new Response(HttpStatus.OK.value(), "Upload successful",
                    System.currentTimeMillis()));
            // Here add all excel sheet information which is stored in LinkedList<Candidate> INTO Database #Done by Faisal this section

            return "redirect:/setting";
        }

        model.addAttribute("success", new Response(HttpStatus.OK.value(), "Upload successful",
                System.currentTimeMillis()));
        return "redirect:/settings";
    }


}

