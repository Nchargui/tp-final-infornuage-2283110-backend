package com.kriNad.backend.DTO;

public class RegisterRequest {
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private String password;

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
