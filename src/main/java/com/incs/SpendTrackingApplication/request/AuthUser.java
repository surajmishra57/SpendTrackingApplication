package com.incs.SpendTrackingApplication.request;

import javax.validation.constraints.NotEmpty;

public class AuthUser {
    @NotEmpty(message = "Email Required")
    private String email;
    @NotEmpty(message = "Password Required")
    private String password;

    public AuthUser() {
    }

    public AuthUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
