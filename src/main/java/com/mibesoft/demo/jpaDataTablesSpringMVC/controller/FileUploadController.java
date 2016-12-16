package com.mibesoft.demo.jpaDataTablesSpringMVC.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    private final Logger log = Logger.getLogger(FileUploadController.class);

    private final String UPLOAD_DIR = System.getProperty("java.io.tmpdir");
    
    @JsonView(FineUploaderOutput.View.class)
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public FineUploaderOutput upload(@RequestParam("file") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) {
        log.info("Start");
        
        for (Object key : request.getParameterMap().keySet()) {
            String param = request.getParameter(key.toString());
            log.info("\t " + key.toString() + " : " + param);
        }

        if (files != null) {
            log.warn("files.length = " + files.length);
            if (files.length > 0) {
                for (int index = 0; index < files.length; index++) {
                    try {
                        String filename = files[index].getOriginalFilename();
                        log.info("files[" + index + "] = " + filename);
                        byte[] bytes = files[index].getBytes();
                        File file = new File(UPLOAD_DIR, filename);
                        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file))) {
                            stream.write(bytes);
                            stream.close();
                        }
                        log.info("'" + file.getAbsolutePath() + "' uploaded");
                    } catch (Exception ex) {                        
                        log.error(ex.getMessage(), ex);
                    }
                }
            }
        } else {
            log.warn("files is null");
        }
        
        FineUploaderOutput output = new FineUploaderOutput();
        log.info("End");
        return output;
    }
    

    @RequestMapping(value = "/abort", method = RequestMethod.GET)
    public void abortUploading(HttpServletRequest request, HttpServletResponse response) {
        log.info("Start");
        //request.getSession().getServletContext().
        log.info("End");
    }
}
