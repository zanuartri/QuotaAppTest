package com.g2academy.base;

import com.g2academy.model.User;
import com.g2academy.utilities.GetDataFromExcel;
import org.json.simple.JSONObject;

import java.io.IOException;

@SuppressWarnings("unchecked")
public class MainMenuConfig extends RequestConfig {
    public void changeFullName(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("namauser", user.getFullname());
        requestParams.put("username", user.getPhonenumber());
        requestParams.put("password", user.getPassword());
        postRequest(requestParams, "/changefullname");
    }

    public void changeEmail(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", user.getEmail());
        requestParams.put("username", user.getPhonenumber());
        requestParams.put("password", user.getPassword());
        postRequest(requestParams, "/changeemail");
    }

    public void changePhoneNumber(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", user.getPhonenumber());
        requestParams.put("password", user.getPassword());
        postRequest(requestParams, "/changephonenumber");
    }

    public void changePassword(User user, String newPassword) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", user.getPhonenumber());
        requestParams.put("password", user.getPassword());
        requestParams.put("newpassword", newPassword);
        postRequest(requestParams, "/changepassword");
    }

    public void setOtpAndToken(User user, String verificationMethod, String otpCode, String statusOtpCode, String token) throws InterruptedException {
        OTPCode otp = new OTPCode();
        TokenEmail tokenEmail = new TokenEmail();

        switch (verificationMethod) {
            case "OTP":
                String generatedOtpCode = "";
                if (otpCode.equals("TRUE")) {
                    generatedOtpCode = otp.getCode(user.getPhonenumber());
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

    public Object[][] getDataProfileMenu(String sheetName) throws IOException {
        GetDataFromExcel getDataFromExcel = new GetDataFromExcel();
        return getDataFromExcel.getDataExcel("resources/DataProfileMenu.xlsx", sheetName);
    }
}
