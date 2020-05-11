package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.MainMenuBase;
import com.g2academy.model.User;
import org.testng.annotations.Test;

public class TC002_ChangeFullName_Negative extends MainMenuBase {
    private static User user = new User();

    @Test
    public void wrongFullNameFormat() throws InterruptedException {
        user.setNamaUser(""); // wrong full name format
        user.setPassword(""); // correct password format
        changeFullName(user);
    }

    @Test
    public void wrongPasswordFormat() throws InterruptedException {
        user.setNamaUser(""); // correct full name format
        user.setPassword(""); // wrong password format
        changeFullName(user);
    }

    @Test
    public void wrongFullNameAndPasswordFormat() throws InterruptedException {
        user.setNamaUser(""); // wrong full name format
        user.setPassword(""); // wrong password format
        changeFullName(user);
    }

    @Test
    public void blankFullName() throws InterruptedException {
        user.setNamaUser(""); // blank full name
        user.setPassword(""); // correct password format
        changeFullName(user);
    }

    @Test
    public void blankPassword() throws InterruptedException {
        user.setNamaUser(""); // correct full name format
        user.setPassword(""); // blank password
        changeFullName(user);
    }

    @Test
    public void blankFullNameAndPassword() throws InterruptedException {
        user.setNamaUser(""); // blank full name
        user.setPassword(""); // blank password
        changeFullName(user);
    }

    @Test
    public void wrongPasswordData() throws InterruptedException {
        user.setNamaUser(""); // correct full name format
        user.setPassword(""); // wrong password
        changeFullName(user);
    }

    @Test
    public void wrongOTPCodeFormat() throws InterruptedException {
        user.setNamaUser(""); // correct full name format
        user.setPassword(""); // correct password but wrong otp code format
        changeFullName(user);
    }

    @Test
    public void blankOTPCode() throws InterruptedException {
        user.setNamaUser(""); // correct full name format
        user.setPassword(""); // correct password but blank otp code
        changeFullName(user);
    }


    @Test
    public void wrongOTPCode() throws InterruptedException {
        user.setNamaUser(""); // correct full name format
        user.setPassword(""); // correct password but wrong otp code
        changeFullName(user);
    }

    @Test
    public void timeoutOTPCode() throws InterruptedException {
        user.setNamaUser(""); // correct full name format
        user.setPassword(""); // correct password but otp code timeout
        changeFullName(user);
    }
}
