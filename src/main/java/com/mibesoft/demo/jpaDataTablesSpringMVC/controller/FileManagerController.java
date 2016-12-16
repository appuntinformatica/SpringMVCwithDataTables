package com.mibesoft.demo.jpaDataTablesSpringMVC.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mibesoft.demo.jpaDataTablesSpringMVC.model.FileItem;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileManagerController {
    private final Logger log = Logger.getLogger(FileManagerController.class);
    
    private final String UPLOAD_DIR = System.getProperty("java.io.tmpdir");
    
    @JsonView(DataTablesOutput.View.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public DataTablesOutput<FileItem> list(@Valid DataTablesInput input) {
        log.info("Start, " + input.toString());
        DataTablesOutput<FileItem> output = new DataTablesOutput<>();
        
        output.setDraw(input.getDraw());
        output.setData(Collections.emptyList());
        List<FileItem> data = new ArrayList<>();
        File dir = new File(UPLOAD_DIR);
        int count = 0;
        log.info("'" + dir.getAbsolutePath() + "' files: " + dir.listFiles().length);
        for (File f : dir.listFiles()) {
            if (!f.isDirectory()) {
                if (input.getStart() <= count && (input.getStart() + input.getLength() >= count)) {
                    FileItem e = new FileItem();
                    e.setFilename(f.getName());
                    e.setSize(f.length());
                    e.setDatetime(f.lastModified());
                    data.add(e);
                }
                count++;
            }
        }
        output.setData(data);
        output.setRecordsTotal(count);
        output.setRecordsFiltered(count);
        
        log.info("End, " + output.toString());
        return output;
    }      
}