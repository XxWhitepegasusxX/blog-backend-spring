package com.example.blog.models.user;

public enum Role {
    
    USER("user"),
    ADMIN("admin");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
