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

    public void setOtpAndToken(User user, String verificationMethod, String otpCode, String statusOtpCode, String token) {
        OTPCode otp = new OTPCode();
        TokenEmail tokenEmail = new TokenEmail();

        switch (verificationMethod) {
            case "OTP":
                String generatedOtpCode = "";
                if (otpCode.equals("TRUE")) generatedOtpCode = otp.getCode();
                else generatedOtpCode = otpCode;
                otp.sendCode(user.getPhonenumber(), generatedOtpCode, statusOtpCode);
                break;
            case "TOKEN":
                String generatedToken = "";
                if (token.equals("TRUE")) generatedToken = tokenEmail.getToken();
                else generatedToken = token;
                tokenEmail.sendToken(generatedToken);
                break;
            default:
                break;
        }
    }

    public void getPaketDataList(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("nomer_hp", user.getPhonenumber());
        postRequest(requestParams, "/api/provider/cek-paket");
    }

    public void purchasePaketData(User user, String nomorPaketData, String provider, String price, String paketData) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhonenumber());
        requestParams.put("nomorPaketData", nomorPaketData);
        requestParams.put("nama_provider", provider);
        requestParams.put("harga", price);
        requestParams.put("paket_data", paketData);
        postRequest(requestParams, "/api/transaksi/choice");
    }

    public void payWithQWallet(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhonenumber());
        requestParams.put("pinTransaksi", user.getPinTransaction());
        postRequest(requestParams, "/api/transaksi/E-wallet");
    }

    public void payWithVirtualAccount(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhonenumber());
        requestParams.put("virtualAccount", user.getVirtualAccount());
        postRequest(requestParams, "/api/transaksi/E-wallet");
    }

    public Object[][] getDataProfileMenu(String sheetName) throws IOException {
        GetDataFromExcel getDataFromExcel = new GetDataFromExcel();
        return getDataFromExcel.getDataExcel("resources/DataProfileMenu.xlsx", sheetName);
    }

    public Object[][] getDataPurchase(String sheetName) throws IOException {
        GetDataFromExcel getDataFromExcel = new GetDataFromExcel();
        return getDataFromExcel.getDataExcel("resources/DataPurchase.xlsx", sheetName);
    }
}
