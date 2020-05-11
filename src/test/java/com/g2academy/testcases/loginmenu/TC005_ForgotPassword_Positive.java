package com.g2academy.testcases.loginmenu;

import com.g2academy.base.LoginMenuBase;
import com.g2academy.model.User;
import org.testng.annotations.Test;

public class TC005_ForgotPassword_Positive extends LoginMenuBase {
    private String newPassword;

    @Test
    public void correctAllData() throws InterruptedException {
        newPassword = ""; // correct new password
        forgotPassword(newPassword);
    }
}
