package com.g2academy.base;

import com.g2academy.model.User;
import com.g2academy.utilities.GetDataFromExcel;
import org.json.simple.JSONObject;

import java.io.IOException;

@SuppressWarnings("unchecked")
public class LoginMenuConfig extends RequestConfig {
    public void login(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhoneNumber());
        requestParams.put("password", user.getPassword());
        postRequest(requestParams, "/api/auth/signin");
    }

    public void logout(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhoneNumber());
        requestParams.put("password", user.getPassword());
        postRequest(requestParams, "/api/auth/signout");
    }

    public void deleteAcount(String phoneNumber) {
        deleteRequest("/api/auth/delete-user/" + phoneNumber);
    }

    public void register(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", user.getEmail());
        requestParams.put("namaUser", user.getFullName());
        requestParams.put("noTelepon", user.getPhoneNumber());
        requestParams.put("password", user.getPassword());
        requestParams.put("confirmPassword", user.getConfirmPassword());
        requestParams.put("pinTransaksi", user.getPinTransaction());
        postRequest(requestParams, "/api/auth/signup");
    }

    public void forgotPassword(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhoneNumber());
        requestParams.put("email", user.getEmail());
        postRequest(requestParams, "/api/auth/forgot-password");
    }

    public Object[][] getDataLoginMenu(String sheetName) throws IOException {
        GetDataFromExcel getDataFromExcel = new GetDataFromExcel();
        return getDataFromExcel.getDataExcel("resources/DataLoginMenu.xlsx", sheetName);
    }
}