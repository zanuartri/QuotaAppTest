package com.g2academy.base;

import com.g2academy.model.User;
import com.g2academy.utilities.GetDataFromExcel;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unchecked")
public class RequestConfig extends TestConfig {
    private static final String LOGIN_PATH = "/api/auth/signin";
    private static final String REGISTER_PATH = "/api/auth/signup";
    private static final String FORGOT_PASSWORD_PATH = "/api/auth/forgot-pasword";
    private static final String INTERNET_DATA_LIST_PATH = "/api/provider/cek-paket";
    private static final String INTERNET_DATA_PURCHASE_PATH  = "/api/transaksi/choice";
    private static final String TRANSACTION_HISTORY_PATH = "/api/transaksi/history";
    private static final String Q_WALLET_PATH = "/api/transaksi/E-wallet";
    private static final String VIRTUAL_ACCOUNT_PATH = "/api/transaksi/virtual-account";
    private static final String UPLOAD_INVOICE_PATH = "/api/transaksi-upload-photo/buktipembayaran";
    private static final String EDIT_PERSONAL_INFORMATION_PATH = "/api/auth/edit-user";
    private static final String REQUEST_RESET_PASSWORD_PATH = "/api/auth/reset-password-inapplication";
    private static final String CHANGE_PASSWORD_PATH = "/api/auth/change-password";
    private static final String LOGOUT_PATH = "/api/auth/signout";
    private static final String DELETE_ACCOUNT_PATH = "/api/auth/delete-user";
    private static final String SEND_OTP_REGISTER_PATH = "/api/auth/confirmation-otp";
    private static final String SEND_OTP_FORGOT_PASSWORD_PATH = "/api/auth/confirmation-forgotPassword";
    private static final String GET_OTP_PATH = "/api/auth/qa-get-otp";
    private static final String SEND_EMAIL_REGISTER_PATH = "/api/auth/confirmation-account";
    private static final String SEND_EMAIL_FORGOT_PASSWORD_PATH = "/api/auth/confirm-password";
    private static final String GET_EMAIL_PATH = "/api/auth/qa-get-token";

    public void getRequest(String path) {
        setResponse(getHttpRequest().request(Method.GET, path));
    }

    public void postRequest(JSONObject requestParams, String path) {
        setResponse(getHttpRequest()
                .body(requestParams.toJSONString())
                .header("Content-Type", "application/json")
                .request(Method.POST, path));
    }

    public void putRequest(JSONObject requestParams, String path) {
        setResponse(getHttpRequest()
                .body(requestParams.toJSONString())
                .header("Content-Type", "application/json")
                .request(Method.PUT, path));
    }

    public void deleteRequest(String path) {
        setResponse(getHttpRequest().request(Method.DELETE, path));
    }

    public void uploadImageRequest(String imageName, String path) {
        File file = new File(imageName);
        setResponse(getHttpRequest()
                .header("content-type", "multipart/form-data")
                .multiPart(file)
                .when()
                .post(path));
        setHttpRequest(RestAssured.given());
    }

    public void login(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhoneNumber());
        requestParams.put("password", user.getPassword());
        postRequest(requestParams, LOGIN_PATH);
    }

    public void register(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", user.getEmail());
        requestParams.put("namaUser", user.getFullName());
        requestParams.put("noTelepon", user.getPhoneNumber());
        requestParams.put("password", user.getPassword());
        requestParams.put("confirmPassword", user.getConfirmPassword());
        requestParams.put("pinTransaksi", user.getPinTransaction());
        postRequest(requestParams, REGISTER_PATH);
    }

    public void forgotPassword(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhoneNumber());
        requestParams.put("email", user.getEmail());
        postRequest(requestParams, FORGOT_PASSWORD_PATH);
    }

    public void getPaketDataList(String phoneNumber) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("nomer_hp", phoneNumber);
        postRequest(requestParams, INTERNET_DATA_LIST_PATH);
    }

    public void purchasePaketData(User user, String nomorPaketData, String provider, String price, String paketData) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhoneNumber());
        requestParams.put("nomorPaketData", nomorPaketData);
        requestParams.put("namaProvider", provider);
        requestParams.put("harga", price);
        requestParams.put("paketData", paketData);
        postRequest(requestParams, INTERNET_DATA_PURCHASE_PATH);
    }

    public void getPaketDataHistory(String phoneNumber) {
        getRequest(TRANSACTION_HISTORY_PATH + "/" + phoneNumber);
    }

    public void payWithQWallet(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhoneNumber());
        requestParams.put("pinTransaksi", user.getPinTransaction());
        postRequest(requestParams, Q_WALLET_PATH);
    }

    public void payWithVirtualAccount(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhoneNumber());
        requestParams.put("virtualAccount", user.getVirtualAccount());
        postRequest(requestParams, VIRTUAL_ACCOUNT_PATH);
    }

    public void uploadInvoice(String path) {
        uploadImageRequest(path, UPLOAD_INVOICE_PATH);
    }


    public void editUser(User user, String fullName, String phoneNumber, String email) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("namaUser", fullName);
        requestParams.put("noTelepon", phoneNumber);
        requestParams.put("email", email);
        putRequest(requestParams, EDIT_PERSONAL_INFORMATION_PATH + "/" + user.getPhoneNumber());
    }

    public void resetPassword(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", user.getEmail());
        requestParams.put("noTelepon", user.getPhoneNumber());
        postRequest(requestParams, REQUEST_RESET_PASSWORD_PATH);
    }

    public void changePassword(User user, String newPassword, String confirmPassword) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", user.getEmail());
        requestParams.put("newPassword", newPassword);
        requestParams.put("confirmPassword", confirmPassword);
        putRequest(requestParams, CHANGE_PASSWORD_PATH);
    }

    public void logout(User user) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("noTelepon", user.getPhoneNumber());
        requestParams.put("password", user.getPassword());
        postRequest(requestParams, LOGOUT_PATH);
    }

    public void deleteAccount(String phoneNumber) {
        deleteRequest(DELETE_ACCOUNT_PATH + "/" + phoneNumber);
    }

    public void getOTPCode(String phoneNumber) {
        getRequest(GET_OTP_PATH + "/" + phoneNumber);
    }

    public void sendOTPCodeRegister(String phoneNumber, String otpCode, String statusOtpCode) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("codeOtp", otpCode);
        requestParams.put("statusOtp", statusOtpCode);
        postRequest(requestParams, SEND_OTP_REGISTER_PATH + "/" + phoneNumber + "/otp");
    }

    public void sendOTPCodeForgotPassword(String phoneNumber, String otpCode, String statusOtpCode, String newPassword, String confirmPassword) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("otp", otpCode);
        requestParams.put("statusOtp", statusOtpCode);
        requestParams.put("newPassword", newPassword);
        requestParams.put("confirmPassword", confirmPassword);
        putRequest(requestParams, SEND_OTP_FORGOT_PASSWORD_PATH + "/" + phoneNumber + "/otp");
    }

    public void getToken(String email) {
        getRequest(GET_EMAIL_PATH + "/" + email);
    }

    public void sendTokenRegister(String token) {
        getRequest(SEND_EMAIL_REGISTER_PATH + "/" + token);
    }

    public void sendTokenForgotPassword(String token) {
        getRequest(SEND_EMAIL_FORGOT_PASSWORD_PATH + "/" + token);
    }

    public Object[][] getDataLoginMenu(String sheetName) throws IOException {
        GetDataFromExcel getDataFromExcel = new GetDataFromExcel();
        return getDataFromExcel.getDataExcel("resources/DataLoginMenu.xlsx", sheetName);
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
