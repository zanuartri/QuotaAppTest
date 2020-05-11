package com.g2academy.testcases.loginmenu;

import com.g2academy.base.LoginMenuBase;
import com.g2academy.model.User;
import org.testng.annotations.Test;

public class TC001_Login_Positive extends LoginMenuBase {
    private User user = new User();

    @Test
    public void correctAllData() throws InterruptedException {
        user.setUsername(""); // correct username
        user.setPassword(""); // correct password
        login(user);
    }
}
