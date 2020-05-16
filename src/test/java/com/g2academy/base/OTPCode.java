package com.g2academy.base;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class OTPCode extends RequestConfig {
    public String getCode(String phoneNumber) {
        getRequest("/api/auth/qa-get-otp/" + phoneNumber);
        return (String) getResponse().jsonPath().getString("codeOtp");
    }

    public void sendCodeRegister(String phoneNumber, String otpCode, String statusOtpCode) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("codeOtp", otpCode);
        requestParams.put("statusOtp", statusOtpCode);
        postRequest(requestParams, "/api/auth/confirmation-otp/" + phoneNumber + "/otp");
    }

    public void sendCodeForgotPassword(String phoneNumber, String otpCode, String statusOtpCode, String newPassword, String confirmPassword) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("otp", otpCode);
        requestParams.put("statusOtp", statusOtpCode);
        requestParams.put("newPassword", newPassword);
        requestParams.put("confirmPassword", confirmPassword);
        putRequest(requestParams, "/api/auth/confirmation-forgotPassword/" + phoneNumber + "/otp");
    }
}
