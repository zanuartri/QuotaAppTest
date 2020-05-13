package com.g2academy.testcases.loginmenu;

import com.g2academy.base.Assertion;
import com.g2academy.base.LoginMenuConfig;
import com.g2academy.model.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_Register extends LoginMenuConfig {
    private User user = new User();
    private Assertion assertion = new Assertion();

    @DataProvider(name="dataRegister")
    Object[][] getDataFromExcel() throws IOException {
        return getDataLoginMenu("Register");
    }

    @Test(dataProvider = "dataRegister", timeOut = 15000)
    public void testRegister(
            String description,
            String fullName,
            String email,
            String phoneNumber,
            String password,
            String confirmPassword,
            String statusCodeRequest,
            String responseBodyRequest,
            String verificationMethod,
            String otpCode,
            String token,
            String statusOtpCode,
            String statusCodeConfirmation,
            String responseBodyConfirmation
    ) throws InterruptedException {
        System.out.println(description);

        user.setFullname(fullName);
        user.setEmail(email);
        user.setPhonenumber(phoneNumber);
        user.setPassword(password);

        register(user, confirmPassword);
        Thread.sleep(100);
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);
        Thread.sleep(1000);

//        setOtpAndToken(user, verificationMethod, otpCode, statusOtpCode, token);
//        Thread.sleep(100);
//        assertion.statusCode(Integer.parseInt(statusCodeConfirmation));
//        assertion.responseBodyContains(responseBodyConfirmation);
    }
}
