package com.g2academy.testcases.loginmenu;

import com.g2academy.base.Assertion;
import com.g2academy.base.LoginMenuConfig;
import com.g2academy.model.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_ForgotPassword extends LoginMenuConfig {
    private User user = new User();
    private Assertion assertion = new Assertion();

    @DataProvider(name = "dataForgotPassword")
    Object[][] getDataFromExcel() throws IOException {
        return getDataLoginMenu("Forgot Password");
    }

    @Test(dataProvider = "dataForgotPassword", timeOut = 10000)
    public void testForgotPassword(
            String description,
            String newPassword,
            String email,
            String statusCodeRequest,
            String responseBodyRequest,
            String verificationMethod,
            String otpCode,
            String token,
            String statusOtpCode,
            String statusCodeConfirmation,
            String responseBodyConfirmation
    ) throws InterruptedException {
        user.setPassword(newPassword);
        user.setEmail(email);

        forgotPassword(user);
        Thread.sleep(100);
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);

        Thread.sleep(1000);

        setOtpAndToken(user, verificationMethod, otpCode, statusOtpCode, token);
        Thread.sleep(100);
        assertion.statusCode(Integer.parseInt(statusCodeConfirmation));
        assertion.responseBodyContains(responseBodyConfirmation);
    }
}
