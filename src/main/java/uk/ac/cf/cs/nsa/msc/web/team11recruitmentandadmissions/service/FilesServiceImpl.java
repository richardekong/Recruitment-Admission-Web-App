package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

@Service
public class FilesServiceImpl implements FileUploadService {

    @Value("${historical-spreed-sheet-file-path}")
    private String historicalSpreedSheetFilePath;

    @Value("${current-spreed-sheet-file-path}")
    private String currentSpreedSheetFilePath;

    @Override
    public Path uploadFiles(LinkedHashMap<String, MultipartFile> files) {
        Path path = null;
        if (files.isEmpty()) {
            throw new CustomException("Trying to upload an invalid file", HttpStatus.FORBIDDEN);
        }
        try {
            for (Map.Entry<String, MultipartFile> entry : files.entrySet()) {
                MultipartFile file = entry.getValue();
                byte[] bytes = file.getBytes();
                switch (entry.getKey()) {
                    case "historical-spread-sheet-file":
                        path = Paths.get(historicalSpreedSheetFilePath + file.getOriginalFilename());
                    case "current-spread-sheet-file":
                        path = Paths.get(currentSpreedSheetFilePath + file.getOriginalFilename());
                    default:
                        break;
                }
                Files.write(Objects.requireNonNull(path), bytes);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return path;
    }


    @Override
    public LinkedList<InputStream> uploadFileInputStreams(LinkedHashMap<String, MultipartFile> files) {
        return null;
    }
}
