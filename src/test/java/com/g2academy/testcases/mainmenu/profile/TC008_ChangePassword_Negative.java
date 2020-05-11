package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.MainMenuBase;
import com.g2academy.model.User;
import org.testng.annotations.Test;

public class TC008_ChangePassword_Negative extends MainMenuBase {
    private User user = new User();
    private String newPassword;

    @Test
    public void wrongNewPasswordFormat() throws InterruptedException {
        user.setPassword(""); // correct old password format
        newPassword = ""; // wrong new password format
        changePassword(user, newPassword);
    }

    @Test
    public void wrongPasswordFormat() throws InterruptedException {
        user.setPassword(""); // wrong old password format
        newPassword = ""; // correct new password format
        changePassword(user, newPassword);
    }

    @Test
    public void wrongPasswordAndNewPasswordFormat() throws InterruptedException {
        user.setPassword(""); // wrong old password format
        newPassword = ""; // wrong new password format
        changePassword(user, newPassword);
    }

    @Test
    public void blankNewPassword() throws InterruptedException {
        user.setPassword(""); // cor=rect old password format
        newPassword = ""; // blank new password
        changePassword(user, newPassword);
    }

    @Test
    public void blankPassword() throws InterruptedException {
        user.setPassword(""); // blank old password
        newPassword = ""; // correct new password format
        changePassword(user, newPassword);
    }

    @Test
    public void blankPasswordAndNewPassword() throws InterruptedException {
        user.setPassword(""); // blank old password
        newPassword = ""; // blank new password
        changePassword(user, newPassword);
    }

    @Test
    public void wrongPasswordData() throws InterruptedException {
        user.setPassword(""); // wrong old password
        newPassword = ""; // correct new password format
        changePassword(user, newPassword);
    }

    @Test
    public void wrongOTPCodeFormat() throws InterruptedException {
        user.setPassword(""); // correct old password format
        newPassword = ""; // correct new password format but wrong otp code format
        changePassword(user, newPassword);
    }

    @Test
    public void blankOTPCode() throws InterruptedException {
        user.setPassword(""); // correct old password format
        newPassword = ""; // correct new password format but blank otp code
        changePassword(user, newPassword);
    }

    @Test
    public void wrongOTPCode() throws InterruptedException {
        user.setPassword(""); // correct old password format
        newPassword = ""; // correct new password format but wrong otp code
        changePassword(user, newPassword);
    }

    @Test
    public void timeoutOTPCode() throws InterruptedException {
        user.setPassword(""); // correct old password format
        newPassword = ""; // correct new password format but otp code timeout
        changePassword(user, newPassword);
    }
}
