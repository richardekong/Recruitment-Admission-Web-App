package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public interface FileUploadService {

    Path uploadFile(MultipartFile file);

    Path uploadFiles(LinkedHashMap<String, MultipartFile> files);

    FileInputStream uploadFileInputStream(MultipartFile file);

    LinkedList<FileInputStream> uploadFileInputStreams(LinkedHashMap<String, MultipartFile> files);

}
