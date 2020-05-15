package com.g2academy.testcases.loginmenu;

import com.g2academy.base.Assertion;
import com.g2academy.base.LoginMenuConfig;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_ForgotPassword extends LoginMenuConfig {
    private User user = new User();
    private Assertion assertion = new Assertion();
    private String[][] result = new String[100][12];
    private int testCaseIndex;

    @DataProvider(name = "dataForgotPassword")
    Object[][] getDataFromExcel() throws IOException {
        return getDataLoginMenu("Forgot Password");
    }

    @BeforeClass
    public void beforeClass() {
        testCaseIndex = 1;
        result[0][0] = "description";
        result[0][1] = "phoneNumber";
        result[0][2] = "email";
        result[0][3] = "statusCodeRequest";
        result[0][4] = "responseBodyRequest";
        result[0][5] = "verificationMethod";
        result[0][6] = "otpCode";
        result[0][7] = "statusOtpCode";
        result[0][8] = "token";
        result[0][9] = "statusCodeConfirmation";
        result[0][10] = "responseBodyConfirmation";
        result[0][11] = "status";
    }

    @Test(dataProvider = "dataForgotPassword", timeOut = 15000)
    public void testForgotPassword(
            String description,
            String phoneNumber,
            String email,
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
        result[testCaseIndex][1] = phoneNumber;
        result[testCaseIndex][2] = email;
        result[testCaseIndex][3] = statusCodeRequest;
        result[testCaseIndex][4] = responseBodyRequest;
        result[testCaseIndex][5] = verificationMethod;
        result[testCaseIndex][6] = otpCode;
        result[testCaseIndex][7] = statusOtpCode;
        result[testCaseIndex][8] = token;
        result[testCaseIndex][9] = statusCodeConfirmation;
        result[testCaseIndex][10] = responseBodyConfirmation;
        result[testCaseIndex][11] = "FAILED";

        user.setPhonenumber(phoneNumber);
        user.setEmail(email);
        forgotPassword(user);
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);

        if (verificationMethod.equals("OTP") || verificationMethod.equals("TOKEN")) {
            setOtpAndTokenForgotPassword(user, verificationMethod, otpCode, statusOtpCode, token);
            assertion.statusCode(Integer.parseInt(statusCodeConfirmation));
            assertion.responseBodyContains(responseBodyConfirmation);
        }

        result[testCaseIndex][11] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        testCaseIndex++;
    }

    @AfterClass
    public void afterClass() throws IOException {
        SetDataToExcel excel = new SetDataToExcel();
        excel.writeExcel(result, "Forgot Password");
    }
}
