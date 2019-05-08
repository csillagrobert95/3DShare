package com.gad.a3dshare.service;

import com.gad.a3dshare.model.LoginResponse;

public class LoginService {
    public LoginResponse login (String email, String password){
        LoginResponse response = new LoginResponse(false, "");

        if (email.equals("")) {
            response.setMessage("Please type in your email");
        } else {
            if(password.equals("")){
                response.setMessage("Please type in your password");
            } else {
                response.setMessage("Authentication successful");
                response.setAuthenticated(true);
            }
        }

        return response;

    }
}
