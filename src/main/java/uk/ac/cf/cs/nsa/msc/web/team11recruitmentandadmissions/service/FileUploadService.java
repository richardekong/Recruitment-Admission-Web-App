package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface FileUploadService {

    Path uploadFiles(LinkedHashMap<String, MultipartFile> files);

    LinkedHashMap<String,InputStream> uploadFileInputStreams(LinkedHashMap<String, MultipartFile> files);

}
