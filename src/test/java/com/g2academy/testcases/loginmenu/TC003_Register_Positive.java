package com.g2academy.testcases.loginmenu;

import com.g2academy.base.LoginMenuBase;
import com.g2academy.model.User;
import org.testng.annotations.Test;

public class TC003_Register_Positive extends LoginMenuBase {
    private User user = new User();
    private String confirmPassword;

    @Test
    public void correcAlltData() throws InterruptedException {
        user.setNamaUser(""); // correct nama user
        user.setEmail(""); // correct email
        user.setUsername(""); // correct username
        user.setPassword(""); // correct password
        confirmPassword = ""; // correct confirm password
        register(user, confirmPassword);
    }
}
