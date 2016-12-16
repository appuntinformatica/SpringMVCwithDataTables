package com.mibesoft.demo.jpaDataTablesSpringMVC.model;

import lombok.Getter;

public enum UserRole {
    ADMIN("admin"), AUTHOR("author"), USER("user");

    @Getter
    private String role;

    private UserRole(String role) {
        this.role = role;
    }
}