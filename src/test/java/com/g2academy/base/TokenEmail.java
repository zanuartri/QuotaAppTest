package com.g2academy.base;

public class TokenEmail extends RequestConfig {
    public String getToken() {
        getRequest("/api/auth/confirmation-account/");
        return getResponseBody();
    }

    public void sendToken(String token) {
        getRequest("/api/auth/confirmation-account/" + token);
    }
}
