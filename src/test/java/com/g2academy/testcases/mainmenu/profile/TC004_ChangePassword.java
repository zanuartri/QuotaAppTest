package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.MainMenuConfig;
import com.g2academy.model.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC004_ChangePassword extends MainMenuConfig {
    private User user = new User();

    @DataProvider(name="dataChangePassword")
    Object[][] getDataFromExcel() throws IOException {
        return getDataProfileMenu("Change Password");
    }

    @Test(dataProvider = "dataChangePassword")
    public void testChangePassword(
            String description,
            String phonenumber,
            String password,
            String newpassword,
            String otpcode,
            String statuscode,
            String responsebody
    ) throws InterruptedException {
        user.setPhonenumber(phonenumber);
        user.setPassword(password);
        changePassword(user, newpassword);
    }
}
