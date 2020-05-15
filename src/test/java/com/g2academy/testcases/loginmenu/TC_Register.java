package com.g2academy.testcases.loginmenu;

import com.g2academy.base.Assertion;
import com.g2academy.base.LoginMenuConfig;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_Register extends LoginMenuConfig {
    private User user = new User();
    private Assertion assertion = new Assertion();
    private String[][] result = new String[200][16];
    private int testCaseIndex;

    @DataProvider(name="dataRegister")
    Object[][] getDataFromExcel() throws IOException {
        return getDataLoginMenu("Register");
    }

    @BeforeClass
    public void beforeClass() {
        testCaseIndex = 1;
        result[0][0] = "description";
        result[0][1] = "pinTransaction";
        result[0][2] = "fullName";
        result[0][3] = "email";
        result[0][4] = "phoneNumber";
        result[0][5] = "password";
        result[0][6] = "confirmPassword";
        result[0][7] = "statusCodeRequest";
        result[0][8] = "responseBodyRequest";
        result[0][9] = "verificationMethod";
        result[0][10] = "otpCode";
        result[0][11] = "statusOtpCode";
        result[0][12] = "token";
        result[0][13] = "statusCodeConfirmation";
        result[0][14] = "responseBodyConfirmation";
        result[0][15] = "status";
    }

    @Test(dataProvider = "dataRegister", timeOut = 15000)
    public void testRegister(
            String description,
            String pinTransaction,
            String fullName,
            String email,
            String phoneNumber,
            String password,
            String confirmPassword,
            String statusCodeRequest,
            String responseBodyRequest,
            String verificationMethod,
            String otpCode,
            String statusOtpCode,
            String token,
            String statusCodeConfirmation,
            String responseBodyConfirmation
    ) {
        result[testCaseIndex][0] = description;
        result[testCaseIndex][1] = pinTransaction;
        result[testCaseIndex][2] = fullName;
        result[testCaseIndex][3] = email;
        result[testCaseIndex][4] = phoneNumber;
        result[testCaseIndex][5] = password;
        result[testCaseIndex][6] = confirmPassword;
        result[testCaseIndex][7] = statusCodeRequest;
        result[testCaseIndex][8] = responseBodyRequest;
        result[testCaseIndex][9] = verificationMethod;
        result[testCaseIndex][10] = otpCode;
        result[testCaseIndex][11] = statusOtpCode;
        result[testCaseIndex][12] = token;
        result[testCaseIndex][13] = statusCodeConfirmation;
        result[testCaseIndex][14] = responseBodyConfirmation;
        result[testCaseIndex][15] = "FAILED";

        user.setFullname(fullName);
        user.setEmail(email);
        user.setPhonenumber(phoneNumber);
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);
        user.setPinTransaction(pinTransaction);
        register(user);
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);

        if (verificationMethod.equals("OTP") || verificationMethod.equals("TOKEN")) {
            setOtpAndTokenRegister(user, verificationMethod, otpCode, statusOtpCode, token);
            assertion.statusCode(Integer.parseInt(statusCodeConfirmation));
            assertion.responseBodyContains(responseBodyConfirmation);
            deleteAcount(user.getPhonenumber());
        }

        result[testCaseIndex][15] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        testCaseIndex++;
    }

    @AfterClass
    public void afterClass() throws IOException {
        SetDataToExcel excel = new SetDataToExcel();
        excel.writeExcel(result, "Register");
    }
}
