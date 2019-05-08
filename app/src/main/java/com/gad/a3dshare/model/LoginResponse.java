package com.gad.a3dshare.model;

public class LoginResponse {
    private boolean isAuthenticated;
    private String message;

    public LoginResponse(boolean isAuthenticated, String message) {
        this.isAuthenticated = isAuthenticated;
        this.message = message;
    }

    public LoginResponse() {
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
