package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.MainMenuConfig;
import com.g2academy.model.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_ChangeEmail extends MainMenuConfig {
    private User user = new User();

    @DataProvider(name="dataChangeEmail")
    Object[][] getDataFromExcel() throws IOException {
        return getDataProfileMenu("Change Email");
    }

    @Test(dataProvider = "dataChangeEmail")
    public void testChangeEmail(
            String description,
            String email,
            String phonenumber,
            String password,
            String otpcode,
            String statuscode,
            String responsebody
    ) throws InterruptedException {
        user.setEmail(email);
        user.setPhonenumber(phonenumber);
        user.setPassword(password);
        changeEmail(user);
    }
}
