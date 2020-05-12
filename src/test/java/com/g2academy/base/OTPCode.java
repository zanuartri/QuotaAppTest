package com.g2academy.base;

import org.json.simple.JSONObject;

public class OTPCode extends RequestConfig {
    public String getCode(String phoneNumber) {
        getRequest("/api/auth/confirmation-otp/" + phoneNumber + "/otp");
        return getResponseBody();
    }

    public void sendCode(String phoneNumber, String otpCode, String statusOtpCode) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("codeOtp", otpCode);
        requestParams.put("statusOtp", statusOtpCode);
        postRequest(requestParams, "/api/auth/confirmation-otp/" + phoneNumber + "/otp");
    }
}
