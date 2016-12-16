package com.mibesoft.demo.jpaDataTablesSpringMVC.controller;

import com.fasterxml.jackson.annotation.JsonView;


public class FineUploaderOutput {
    
    public FineUploaderOutput() {
        this.success = true;
        this.error = "";
        this.reset = false;
    }
    
    public static interface View {
    
    }
    
    @JsonView(value = {FineUploaderOutput.View.class})
    private boolean success;

    private String error;
    
    private boolean reset;
    
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

}