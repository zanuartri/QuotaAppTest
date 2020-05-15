package com.g2academy.base;

import com.g2academy.model.User;
import com.g2academy.utilities.GetDataFromExcel;
import org.json.simple.JSONObject;

import java.io.IOException;

@SuppressWarnings("unchecked")
public class LoginMenuConfig extends RequestConfig {
    public void login(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhonenumber());
        requestParams.put("password", user.getPassword());
        postRequest(requestParams, "/api/auth/signin");
    }

    public void logout(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhonenumber());
        requestParams.put("password", user.getPassword());
        postRequest(requestParams, "/api/auth/signout");
    }

    public void deleteAcount(String phoneNumber) {
        deleteRequest("api/auth/delete-user/" + phoneNumber);
    }

    public void register(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", user.getEmail());
        requestParams.put("namaUser", user.getFullname());
        requestParams.put("noTelepon", user.getPhonenumber());
        requestParams.put("password", user.getPassword());
        requestParams.put("confirmPassword", user.getConfirmPassword());
        requestParams.put("pinTransaksi", user.getPinTransaction());
        postRequest(requestParams, "/api/auth/signup");
    }

    public void forgotPassword(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhonenumber());
        requestParams.put("email", user.getEmail());
        postRequest(requestParams, "/api/auth/forgot-password");
    }

    public void setOtpAndTokenRegister(User user, String verificationMethod, String otpCode, String statusOtpCode, String token) {
        OTPCode otp = new OTPCode();
        TokenEmail tokenEmail = new TokenEmail();

        switch (verificationMethod) {
            case "OTP":
                String generatedOtpCode = "";
                if (otpCode.equals("TRUE")) generatedOtpCode = otp.getCode(user.getPhonenumber());
                else generatedOtpCode = otpCode;
                otp.sendCodeRegister(user.getPhonenumber(), generatedOtpCode, statusOtpCode);
                break;
            case "TOKEN":
                String generatedToken = "";
                if (token.equals("TRUE")) generatedToken = tokenEmail.getToken();
                else generatedToken = token;
                tokenEmail.sendTokenRegister(generatedToken);
                break;
            default:
                break;
        }
    }

    public void setOtpAndTokenForgotPassword(User user, String verificationMethod, String otpCode, String statusOtpCode, String token) {
        OTPCode otp = new OTPCode();
        TokenEmail tokenEmail = new TokenEmail();

        switch (verificationMethod) {
            case "OTP":
                String generatedOtpCode = "";
                if (otpCode.equals("TRUE")) generatedOtpCode = otp.getCode(user.getPhonenumber());
                else generatedOtpCode = otpCode;
                otp.sendCodeForgotPassword(user.getPhonenumber(), generatedOtpCode, statusOtpCode);
                break;
            case "TOKEN":
                String generatedToken = "";
                if (token.equals("TRUE")) generatedToken = tokenEmail.getToken();
                else generatedToken = token;
                tokenEmail.sendTokenRegister(generatedToken);
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