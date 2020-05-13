package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.Assertion;
import com.g2academy.base.LoginMenuConfig;
import com.g2academy.base.MainMenuConfig;
import com.g2academy.model.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_Logout extends MainMenuConfig {
    LoginMenuConfig loginMenu = new LoginMenuConfig();
    private User user = new User();
    private Assertion assertion = new Assertion();

    @DataProvider(name="dataLogout")
    Object[][] getDataFromExcel() throws IOException {
        return getDataProfileMenu("Logout");
    }

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        user.setPhonenumber("+6281252930398");
        user.setPassword("Zanuar30@@");
        loginMenu.login(user);
        Thread.sleep(1000);
    }

    @Test(dataProvider = "dataLogout", timeOut = 15000)
    public void testLogout(
            String description,
            String phoneNumber,
            String password,
            String statusCodeRequest,
            String responseBodyRequest
    ) throws InterruptedException {
        System.out.println(description);

        user.setPhonenumber(phoneNumber);
        user.setPassword(password);
        loginMenu.logout(user);
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);
        Thread.sleep(1000);
    }
}
