package com.g2academy.testcases.loginmenu;

import com.g2academy.base.LoginMenuBase;
import com.g2academy.model.User;
import org.testng.annotations.Test;

public class TC002_Login_Negative extends LoginMenuBase {
    private User user = new User();

    @Test
    public void wrongUsernameFormat() throws InterruptedException {
        user.setUsername(""); // wrong username format
        user.setPassword(""); // wrong password format
        login(user);
    }

    @Test
    public void wrongPasswordFormat() throws InterruptedException {
        user.setUsername(""); // correct username format
        user.setPassword(""); // wrong password format
        login(user);
    }

    @Test
    public void wrongUsernameAndPasswordFormat() throws InterruptedException {
        user.setUsername(""); // wrong username format
        user.setPassword(""); // correct password format
        login(user);
    }

    @Test
    public void blankUsername() throws InterruptedException {
        user.setUsername(""); // blank username
        user.setPassword(""); // correct password format
        login(user);
    }

    @Test
    public void blankPassword() throws InterruptedException {
        user.setUsername(""); // correct username format
        user.setPassword(""); // blank password
        login(user);
    }

    @Test
    public void blankUsernameAndPassword() throws InterruptedException {
        user.setUsername(""); // blank username
        user.setPassword(""); // blank password
        login(user);
    }

    @Test
    public void wrongUsernameData() throws InterruptedException {
        user.setUsername(""); // correct username format but wrong username data
        user.setPassword(""); // correct password format
        login(user);
    }

    @Test
    public void wrongPasswordData() throws InterruptedException {
        user.setUsername(""); // correct username format
        user.setPassword(""); // correct password format but wrong password data
        login(user);
    }

    @Test
    public void wrongUsernameAndPasswordData() throws InterruptedException {
        user.setUsername(""); // correct username format but wrong username data
        user.setPassword(""); // correct password format but wrong password data
        login(user);
    }

    @Test
    public void invalidPhoneNumber() throws InterruptedException {
        user.setUsername(""); // correct username format but invalid phone number
        user.setPassword(""); // correct password format
        login(user);
    }

    @Test
    public void invalidPhoneNumberAndPasswordData() throws InterruptedException {
        user.setUsername(""); // correct username format but invalid phone number
        user.setPassword(""); // correct password format but wrong password data
        login(user);
    }

    @Test
    public void wrongOTPCodeFormat() throws InterruptedException {
        user.setUsername(""); // correct username but wrong otp code format
        user.setPassword(""); // correct password
        login(user);
    }

    @Test
    public void blankOTPCode() throws InterruptedException {
        user.setUsername(""); // correct username but wrong otp code
        user.setPassword(""); // correct password
        login(user);
    }

    @Test
    public void wrongOTPCode() throws InterruptedException {
        user.setUsername(""); // correct username but wrong otp code
        user.setPassword(""); // correct password
        login(user);
    }

    @Test
    public void timeoutOTPCode() throws InterruptedException {
        user.setUsername(""); // correct username but otp code timeout
        user.setPassword(""); // correct password
        login(user);
    }
}
