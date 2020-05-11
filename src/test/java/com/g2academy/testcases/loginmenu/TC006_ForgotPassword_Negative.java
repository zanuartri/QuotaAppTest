package com.g2academy.testcases.loginmenu;

import com.g2academy.base.LoginMenuBase;
import org.testng.annotations.Test;

public class TC006_ForgotPassword_Negative extends LoginMenuBase {
    private String newPassword;

    @Test
    public void wrongNewPasswordFormat() throws InterruptedException {
        newPassword = ""; // wrong new password format
        forgotPassword(newPassword);
    }

    @Test
    public void blankNewPassword() throws InterruptedException {
        newPassword = ""; // blank new password
        forgotPassword(newPassword);
    }

    @Test
    public void wrongOTPCodeFormat() throws InterruptedException {
        newPassword = ""; // correct new password format but wrong otp code format
        forgotPassword(newPassword);
    }

    @Test
    public void blankOTPCode() throws InterruptedException {
        newPassword = ""; // correct new password format but blank otp code
        forgotPassword(newPassword);
    }

    @Test
    public void wrongOTPCode() throws InterruptedException {
        newPassword = ""; // correct new password format but wrong otp code
        forgotPassword(newPassword);
    }

    @Test
    public void timeoutOTPCode() throws InterruptedException {
        newPassword = ""; // correct new password format but otp code timeout
        forgotPassword(newPassword);
    }
}
