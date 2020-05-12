package com.g2academy.testcases.loginmenu;

import com.g2academy.base.Assertion;
import com.g2academy.base.LoginMenuConfig;
import com.g2academy.model.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC001_Login extends LoginMenuConfig {
    private User user = new User();
    private Assertion assertion = new Assertion();

    @DataProvider(name="dataLogin")
    Object[][] getDataFromExcel() throws IOException {
        return getDataLoginMenu("Login");
    }

    @Test(dataProvider = "dataLogin", timeOut = 10000)
    public void testLogin(
            String description,
            String phoneNumber,
            String password,
            String statusCodeRequest,
            String responseBodyRequest
    ) throws InterruptedException {
        user.setPhonenumber(phoneNumber);
        user.setPassword(password);

        login(user);
        Thread.sleep(100);
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);
        Thread.sleep(3000);
    }
}
