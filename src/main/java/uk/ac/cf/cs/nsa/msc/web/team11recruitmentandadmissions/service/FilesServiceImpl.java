package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedList;

@Service
public class FilesServiceImpl implements FileUploadService {

    @Value("${historical-spreed-sheet-file-path}")
    private String historicalSpreedSheetFilePath;

    @Value("${current-spreed-sheet-file-path}")
    private String currentSpreedSheetFilePath;

    @Override
    public Path uploadFile(MultipartFile file) {

        return null;
    }

    @Override
    public Path uploadFiles(LinkedHashMap<String, MultipartFile> files) {
        return null;
    }

    @Override
    public FileInputStream uploadFileInputStream(MultipartFile file) {
        return null;
    }

    @Override
    public LinkedList<FileInputStream> uploadFileInputStreams(LinkedHashMap<String, MultipartFile> files) {
        return null;
    }
}
