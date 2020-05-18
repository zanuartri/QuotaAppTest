package com.g2academy.testcases.loginmenu;

import com.g2academy.base.*;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_ForgotPassword extends LoginMenuConfig {
    private User user = new User();
    private Assertion assertion = new Assertion();
    private String[][] result = new String[100][16];
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
        result[0][11] = "newPassword";
        result[0][12] = "confirmNewPassword";
        result[0][13] = "statusCodeNewPassword";
        result[0][14] = "responseBodyNewPassword";
        result[0][15] = "status";

        user.setFullName("Zanuar Tri Romadon");
        user.setEmail("testforgotpasswordbackend@gmail.com");
        user.setPhoneNumber("+6281252930398");
        user.setPassword("Zanuar30@@");
        user.setConfirmPassword("Zanuar30@@");
        user.setConfirmPassword("Zanuar30@@");
        user.setPinTransaction("123456");
        deleteAcount(user.getPhoneNumber());
        System.out.println(getResponse().getBody().asString());
        register(user);
        System.out.println(getResponse().getBody().asString());
        setOtpAndTokenRegister(user, "OTP", "TRUE", "true", "");
        System.out.println(getResponse().getBody().asString());
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
        System.out.println(getResponse().getBody().asString());
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);

        if (verificationMethod.equals("OTP") || verificationMethod.equals("TOKEN")) {
            OTPCode otp = new OTPCode();
            TokenEmail tokenEmail = new TokenEmail();

            if (verificationMethod.equals("OTP")) {
                String generatedOTP = "";
                if (otpCode.equals("TRUE")) generatedOTP = otp.getCode(user.getPhoneNumber());
                else generatedOTP = otpCode;
                otp.sendCodeForgotPassword(user.getPhoneNumber(), generatedOTP, statusOtpCode, newPassword, confirmNewPassword);
                System.out.println(getResponse().getBody().asString());
                assertion.statusCode(Integer.parseInt(statusCodeNewPassword));
                assertion.responseBodyContains(responseBodyNewPassword);
            } else if (verificationMethod.equals("TOKEN")){
                String generatedToken = "";
                if (token.equals("TRUE")) generatedToken = tokenEmail.getToken(user.getEmail());
                else generatedToken = token;
                tokenEmail.sendTokenForgotPassword(generatedToken);
                System.out.println(getResponse().getBody().asString());
                assertion.statusCode(Integer.parseInt(statusCodeConfirmation));
                assertion.responseBodyContains(responseBodyConfirmation);

                if (token.equals("TRUE")) {
                    MainMenuConfig mainMenuConfig = new MainMenuConfig();
                    mainMenuConfig.changePassword(user, newPassword, confirmNewPassword);
                    System.out.println(getResponse().getBody().asString());
                    assertion.statusCode(Integer.parseInt(statusCodeNewPassword));
                    assertion.responseBodyContains(responseBodyNewPassword);
                }
            }
        }

        System.out.println(getResponse().getBody().asString());
        result[testCaseIndex][15] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        testCaseIndex++;
    }

    @AfterClass
    public void afterClass() throws IOException {
        deleteAcount("+6281252930398");
        System.out.println(getResponse().getBody().asString());
        SetDataToExcel excel = new SetDataToExcel();
        excel.writeExcel(result, "Forgot Password");
    }
}
