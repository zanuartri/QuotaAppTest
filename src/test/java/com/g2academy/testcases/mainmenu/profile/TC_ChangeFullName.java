package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.MainMenuConfig;
import com.g2academy.model.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_ChangeFullName extends MainMenuConfig {
    private User user = new User();

    @DataProvider(name="dataChangeFullName")
    Object[][] getDataFromExcel() throws IOException {
        return getDataProfileMenu("Change Full Name");
    }

    @Test(dataProvider = "dataChangeFullName")
    public void testChangeFullName(
            String description,
            String fullname,
            String phonenumber,
            String password,
            String otpcode,
            String statuscode,
            String responsebody
    ) throws InterruptedException {
        user.setFullname(fullname);
        user.setPhonenumber(phonenumber);
        user.setPassword(password);
        changeFullName(user);
    }
}
