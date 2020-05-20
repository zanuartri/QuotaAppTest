package com.g2academy.testcases.loginmenu;

import com.g2academy.base.RequestConfig;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_ForgotPassword extends RequestConfig {
    private User user = new User();
    private int testCaseIndex;
    private String[][] result = new String[100][16];

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
        result[0][11] = "newPassword";
        result[0][12] = "confirmNewPassword";
        result[0][13] = "statusCodeNewPassword";
        result[0][14] = "responseBodyNewPassword";
        result[0][15] = "status";

        user.setFullName("Zanuar Tri Romadon");
        user.setEmail("testchangepasswordbackend23@gmail.com");
        user.setPhoneNumber("+6281252930360");
        user.setPassword("Zanuar30@@");
        user.setConfirmPassword("Zanuar30@@");
        user.setPinTransaction("123456");
        register(user);
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), 200);
        Assert.assertTrue(getResponse().jsonPath().getString("message").contains(user.getPhoneNumber()));

        getOTPCode(user.getPhoneNumber());
        String generatedOtpCode = getResponse().jsonPath().getString("codeOtp");
        Assert.assertEquals(getResponse().jsonPath().getString("email"), user.getEmail());
        Assert.assertEquals(getResponse().jsonPath().getString("mobileNumber"), user.getPhoneNumber());
        Assert.assertTrue(getResponse().jsonPath().getBoolean("statusOtp"));

        sendOTPCodeRegister(user.getPhoneNumber(), generatedOtpCode, "true");
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), 200);
        Assert.assertTrue(getResponse().jsonPath().getString("message").contains("signup is successfully"));
        Assert.assertEquals(getResponse().jsonPath().getString("noTelepon"), user.getPhoneNumber());
        Assert.assertEquals(getResponse().jsonPath().getString("email"), user.getEmail());
        Assert.assertEquals(getResponse().jsonPath().getString("pinTransaksi"), user.getPinTransaction());
        Assert.assertEquals(getResponse().jsonPath().getInt("saldo"), 1000000);

        login(user);
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), 200);
        Assert.assertTrue(getResponse().jsonPath().getString("message").contains("successfully"));
        Assert.assertNotNull(getResponse().jsonPath().getString("token"));
        Assert.assertEquals(getResponse().jsonPath().getString("type"), "Bearer");
        Assert.assertEquals(getResponse().jsonPath().getString("username"), user.getPhoneNumber());
        Assert.assertEquals(getResponse().jsonPath().getString("email"), user.getEmail());
        Assert.assertNotNull(getResponse().jsonPath().getString("saldo"));
    }

    @Test(dataProvider = "dataForgotPassword", timeOut = 30000)
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
            String responseBodyConfirmation,
            String newPassword,
            String confirmNewPassword,
            String statusCodeNewPassword,
            String responseBodyNewPassword
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
        result[testCaseIndex][11] = newPassword;
        result[testCaseIndex][12] = confirmNewPassword;
        result[testCaseIndex][13] = statusCodeNewPassword;
        result[testCaseIndex][14] = responseBodyNewPassword;
        result[testCaseIndex][15] = "FAILED";

        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        forgotPassword(user);
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), Integer.parseInt(statusCodeRequest));
        Assert.assertTrue(getResponse().jsonPath().getString("message").contains(responseBodyRequest));

        if (verificationMethod.equals("OTP")) {
            if (otpCode.equals("TRUE")) {
                getOTPCode(user.getPhoneNumber());
                String generatedOtpCode = getResponse().jsonPath().getString("codeOtp");
                Assert.assertEquals(getResponse().jsonPath().getString("email"), user.getEmail());
                Assert.assertEquals(getResponse().jsonPath().getString("mobileNumber"), user.getPhoneNumber());
                Assert.assertTrue(getResponse().jsonPath().getBoolean("statusOtp"));

                sendOTPCodeForgotPassword(user.getPhoneNumber(), generatedOtpCode, statusOtpCode, newPassword, confirmNewPassword);
                Assert.assertEquals(getResponse().jsonPath().getInt("status"), Integer.parseInt(statusCodeConfirmation));
                Assert.assertTrue(getResponse().jsonPath().getString("message").contains(responseBodyConfirmation));
            } else {
                sendOTPCodeForgotPassword(user.getPhoneNumber(), otpCode, statusOtpCode, newPassword, confirmNewPassword);
                Assert.assertEquals(getResponse().jsonPath().get("status"), statusCodeConfirmation);
                Assert.assertTrue(getResponse().jsonPath().getString("message").contains(responseBodyConfirmation));
            }
        } else if (verificationMethod.equals("TOKEN")) {
            if (token.equals("TRUE")) {
                getToken(user.getEmail());
                String generatedToken = getResponse().jsonPath().getString("codeVerify");
                Assert.assertEquals(getResponse().jsonPath().getString("email"), user.getEmail());
                Assert.assertEquals(getResponse().jsonPath().getString("mobileNumber"), user.getPhoneNumber());
                Assert.assertTrue(getResponse().jsonPath().getBoolean("statusEmailVerify"));

                sendTokenForgotPassword(generatedToken);
                Assert.assertEquals(getResponse().jsonPath().getInt("status"), Integer.parseInt(statusCodeConfirmation));
                Assert.assertTrue(getResponse().jsonPath().getString("message").contains(responseBodyConfirmation));

                changePassword(user, newPassword, confirmNewPassword);
                Assert.assertEquals(getResponse().jsonPath().getInt("status"), Integer.parseInt(statusCodeNewPassword));
                Assert.assertTrue(getResponse().jsonPath().getString("message").contains(responseBodyNewPassword));
            } else {
                sendTokenRegister(token);
                Assert.assertEquals(getResponse().jsonPath().getInt("status"), Integer.parseInt(statusCodeConfirmation));
                Assert.assertTrue(getResponse().jsonPath().getString("message").contains(responseBodyConfirmation));
            }
        }

        result[testCaseIndex][15] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        testCaseIndex++;
    }

    @AfterClass
    public void afterClass() throws IOException {
        deleteAccount("+6281252930360");
        Assert.assertEquals(getResponse().getStatusCode(), 200);
        SetDataToExcel.write(result, "Forgot Password");
    }
}
