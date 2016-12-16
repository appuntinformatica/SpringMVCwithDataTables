package com.mibesoft.demo.jpaDataTablesSpringMVC.model;

import com.fasterxml.jackson.annotation.JsonView;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

public class FileItem {
    
    private final long KB = 1024;
    private final long MB = KB * KB;
    private final long GB = MB * MB * MB;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    @Getter
    @Setter
    @JsonView(DataTablesOutput.View.class)
    private String filename;
    
    @Getter
    @Setter
    @JsonView(DataTablesOutput.View.class)
    private String filesize;
    
    @Getter
    @JsonView(DataTablesOutput.View.class)
    private String datetime;
    
    public void setSize(long size) {
        if (size < KB) {
            filesize = size + " B";
        } else if (size < MB) {
            filesize = (size / KB) + " KB";
        } else if (size < GB) {
            filesize = (size / MB) + " MB";
        } else {
            filesize = (size / GB) + " GB";
        }
    }
    
    public void setDatetime(long time) {
        datetime = sdf.format(new Date(time));
    }
    
}
