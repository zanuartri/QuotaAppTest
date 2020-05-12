package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.MainMenuConfig;
import com.g2academy.model.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC003_ChangePhoneNumber extends MainMenuConfig {
    private User user = new User();

    @DataProvider(name="dataChangePhoneNumber")
    Object[][] getDataFromExcel() throws IOException {
        return getDataProfileMenu("Change Phone Number");
    }

    @Test(dataProvider = "dataChangePhoneNumber")
    public void testChangePhoneNumber(
            String description,
            String phonenumber,
            String password,
            String otpcode,
            String statuscode,
            String responsebody
    ) throws InterruptedException {
        user.setPhonenumber(phonenumber);
        user.setPassword(password);
        changePhoneNumber(user);
    }
}
