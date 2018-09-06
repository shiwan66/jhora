package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.DBFile;
import com.mycompany.myapp.exception.FileStorageException;
import com.mycompany.myapp.repository.DBFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DBFileStorageService {

    private final Logger logger = LoggerFactory.getLogger(DBFileStorageService.class);

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
            logger.debug("fileName="+fileName);
            logger.debug("file.getContentType()="+file.getContentType());
            logger.debug("file.getBytes()="+file.getBytes());


            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(Long fileId) {
//        dbFileRepository.getOne(fileId)
        return dbFileRepository.getOne(fileId);
//            .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}
