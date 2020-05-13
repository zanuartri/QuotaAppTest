package com.g2academy.testcases.loginmenu;

import com.g2academy.base.Assertion;
import com.g2academy.base.LoginMenuConfig;
import com.g2academy.model.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_Login extends LoginMenuConfig {
    private User user = new User();
    private Assertion assertion = new Assertion();

    @DataProvider(name="dataLogin")
    Object[][] getDataFromExcel() throws IOException {
        return getDataLoginMenu("Login");
    }

    @Test(dataProvider = "dataLogin", timeOut = 15000)
    public void testLogin(
            String description,
            String phoneNumber,
            String password,
            String statusCodeRequest,
            String responseBodyRequest
    ) throws InterruptedException {
        System.out.println(description);

        user.setPhonenumber(phoneNumber);
        user.setPassword(password);

        login(user);
        Thread.sleep(100);
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);
        Thread.sleep(1000);
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        user.setPhonenumber("+6281252930398");
        user.setPassword("Zanuar30@@");
        logout(user);
        Thread.sleep(1000);
    }
}