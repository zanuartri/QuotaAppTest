package com.g2academy.base;

import com.g2academy.model.User;
import com.g2academy.utilities.GetDataFromExcel;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unchecked")
public class LoginMenuConfig extends RequestConfig {
    public void login(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhonenumber());
        requestParams.put("password", user.getPassword());
        postRequest(requestParams, "/api/auth/signin");
    }

    public void register(User user, String confirmPassword) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", user.getEmail());
        requestParams.put("namaUser", user.getFullname());
        requestParams.put("noTelepon", user.getPhonenumber());
        requestParams.put("password", user.getPassword());
        requestParams.put("confirmPassword", confirmPassword);
        postRequest(requestParams, "/api/auth/signup");
    }

    public void forgotPassword(User user) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String createdDate = now.format(formatter);

        JSONObject requestParams = new JSONObject();
        requestParams.put("createdDate", createdDate);
        requestParams.put("email", user.getEmail());
        requestParams.put("idReset", 0);
        requestParams.put("password", user.getPassword());
        requestParams.put("tokenReset", "");
        postRequest(requestParams, "/api/auth/forgot-password");
    }

    public void setOtpAndToken(User user, String verificationMethod, String otpCode, String statusOtpCode, String token) throws InterruptedException {
        OTPCode otp = new OTPCode();
        TokenEmail tokenEmail = new TokenEmail();

        switch (verificationMethod) {
            case "OTP":
                String generatedOtpCode = "";
                if (otpCode.equals("TRUE")) {
                    generatedOtpCode = "0657";
                    Thread.sleep(100);
                } else {
                    generatedOtpCode = otpCode;
                }
                otp.sendCode(user.getPhonenumber(), generatedOtpCode, statusOtpCode);
                break;
            case "TOKEN":
                String generatedToken = "";
                if (token.equals("TRUE")) {
                    generatedToken = tokenEmail.getToken();
                    Thread.sleep(100);
                } else {
                    generatedToken = token;
                }
                tokenEmail.sendToken(generatedToken);
                break;
            default:
                break;
        }
    }

    public Object[][] getDataLoginMenu(String sheetName) throws IOException {
        GetDataFromExcel getDataFromExcel = new GetDataFromExcel();
        return getDataFromExcel.getDataExcel("resources/DataLoginMenu.xlsx", sheetName);
    }
}