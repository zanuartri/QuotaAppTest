package com.g2academy.base;

import com.g2academy.model.User;
import com.g2academy.utilities.GetDataFromExcel;
import org.json.simple.JSONObject;

import java.io.IOException;

@SuppressWarnings("unchecked")
public class MainMenuConfig extends RequestConfig {
    public void editUser(User user, String fullName, String phoneNumber, String email) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("namaUser", fullName);
        requestParams.put("noTelepon", phoneNumber);
        requestParams.put("email", email);
        putRequest(requestParams, "/api/auth/edit-user/" + user.getPhoneNumber());
    }

    public void resetPassword(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", user.getEmail());
        requestParams.put("noTelepon", user.getPhoneNumber());
        postRequest(requestParams, "/api/auth/reset-password-inapplication");
    }

    public void changePassword(User user, String newPassword, String confirmPassword) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", user.getEmail());
        requestParams.put("newPassword", newPassword);
        requestParams.put("confirmPassword", confirmPassword);
        putRequest(requestParams, "/api/auth/change-password");
    }

    public void getPaketDataList(String phoneNumber) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("nomer_hp", phoneNumber);
        postRequest(requestParams, "/api/provider/cek-paket");
    }

    public void purchasePaketData(User user, String nomorPaketData, String provider, String price, String paketData) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhoneNumber());
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
        requestParams.put("noTelepon", user.getPhoneNumber());
        requestParams.put("pinTransaksi", user.getPinTransaction());
        postRequest(requestParams, "/api/transaksi/E-wallet");
    }

    public void payWithVirtualAccount(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhoneNumber());
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
