package com.g2academy.base;

public class TokenEmail extends RequestConfig {
    public String getToken() {
        getRequest("/api/auth/confirmation-account/");
        return getResponseBody();
    }

    public void sendTokenRegister(String token) {
        getRequest("/api/auth/confirmation-account/" + token);
    }

    public void sendTokenForgotPassword(String token) {
        getRequest("/api/auth/confirmation-account/" + token);
    }
}
