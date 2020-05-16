package com.g2academy.base;

public class TokenEmail extends RequestConfig {
    public String getToken(String email) {
        getRequest("/api/auth/qa-get-token/" + email);
        return (String) getResponse().jsonPath().getString("codeVerify");
    }

    public void sendTokenRegister(String token) {
        getRequest("/api/auth/confirmation-account/" + token);
    }

    public void sendTokenForgotPassword(String token) {
        getRequest("/api/auth/confirm-password/" + token);
    }
}
