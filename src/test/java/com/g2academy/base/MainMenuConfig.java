package com.g2academy.base;

import com.g2academy.model.User;
import com.g2academy.utilities.GetDataFromExcel;
import org.json.simple.JSONObject;

import java.io.IOException;

@SuppressWarnings("unchecked")
public class MainMenuConfig extends RequestConfig {
    public void editUser(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("namauser", user.getFullname());
        requestParams.put("noTelepon", user.getPhonenumber());
        requestParams.put("email", user.getEmail());
        putRequest(requestParams, "/api/auth/edit-user/" + user.getPhonenumber());
    }

    public void resetPassword(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", user.getEmail());
        requestParams.put("noTelepon", user.getPhonenumber());
        postRequest(requestParams, "/api/auth/reset-password-inapplication");
    }

    public void changePassword(User user, String newPassword, String confirmPassword) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", user.getEmail());
        requestParams.put("newPassword", newPassword);
        requestParams.put("confirmPassword", confirmPassword);
        putRequest(requestParams, "/api/auth/change-password");
    }

//    public void setOtpAndToken(User user, String verificationMethod, String otpCode, String statusOtpCode, String token) {
//        OTPCode otp = new OTPCode();
//        TokenEmail tokenEmail = new TokenEmail();
//
//        switch (verificationMethod) {
//            case "OTP":
//                String generatedOtpCode = "";
//                if (otpCode.equals("TRUE")) generatedOtpCode = otp.getCode(user.getPhonenumber());
//                else generatedOtpCode = otpCode;
////                otp.sendCodeForgotPassword(user.getPhonenumber(), generatedOtpCode, statusOtpCode);
//                break;
//            case "TOKEN":
//                String generatedToken = "";
//                if (token.equals("TRUE")) generatedToken = tokenEmail.getToken(user.getEmail());
//                else generatedToken = token;
//                tokenEmail.sendTokenRegister(generatedToken);
//                break;
//            default:
//                break;
//        }
//    }

    public void getPaketDataList(String phoneNumber) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("nomer_hp", phoneNumber);
        postRequest(requestParams, "/api/provider/cek-paket");
    }

    public void purchasePaketData(User user, String nomorPaketData, String provider, String price, String paketData) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhonenumber());
        requestParams.put("nomorPaketData", nomorPaketData);
        requestParams.put("namaProvider", provider);
        requestParams.put("harga", price);
        requestParams.put("paketData", paketData);
        postRequest(requestParams, "/api/transaksi/choice");
    }

    public void getPaketDataHistory(String phoneNumber) {
        getRequest("/api/transaksi/history/" + phoneNumber);
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
        postRequest(requestParams, "/api/transaksi/virtual-account");
    }

    public void uploadInvoice(String path) {
        uploadImage(path, "/api/transaksi-upload-photo/buktipembayaran");
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
