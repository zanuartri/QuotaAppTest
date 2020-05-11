package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.MainMenuBase;
import com.g2academy.model.User;
import org.testng.annotations.Test;

public class TC006_ChangePhoneNumber_Negative extends MainMenuBase {
    private User user = new User();

    @Test
    public void wrongPhoneNumberFormat() throws InterruptedException {
        user.setUsername(""); // wrong phone number format
        user.setPassword(""); // correct password format
        changePhoneNumber(user);
    }

    @Test
    public void wrongPasswordFormat() throws InterruptedException {
        user.setUsername(""); // correct phone number format
        user.setPassword(""); // wrong password format
        changePhoneNumber(user);
    }

    @Test
    public void wrongPhoneNumberAndPasswordFormat() throws InterruptedException {
        user.setUsername(""); // wrong phone number format
        user.setPassword(""); // wrong password format
        changePhoneNumber(user);
    }

    @Test
    public void blankPhoneNumber() throws InterruptedException {
        user.setUsername(""); // blank phone number
        user.setPassword(""); // correct password format
        changePhoneNumber(user);
    }

    @Test
    public void blankPassword() throws InterruptedException {
        user.setUsername(""); // correct phone number format
        user.setPassword(""); // blank password
        changePhoneNumber(user);
    }

    @Test
    public void blankPhoneNumberAndPassword() throws InterruptedException {
        user.setUsername(""); // blank phone number
        user.setPassword(""); // blank password
        changePhoneNumber(user);
    }

    @Test
    public void invalidPhoneNumber() throws InterruptedException {
        user.setUsername(""); // correct phone number format but invalid phone number
        user.setPassword(""); // correct password format
        changePhoneNumber(user);
    }

    @Test
    public void wrongPasswordData() throws InterruptedException {
        user.setUsername(""); // correct phone number
        user.setPassword(""); // wrong password
        changePhoneNumber(user);
    }

    @Test
    public void invalidPhoneNumberAndWrongPassword() throws InterruptedException {
        user.setUsername(""); // invalid phone number
        user.setPassword(""); // wrong password
        changePhoneNumber(user);
    }

    @Test
    public void wrongOTPCodeFormat() throws InterruptedException {
        user.setUsername(""); // correct phone number but wrong otp code format
        user.setPassword(""); // correct password format
        changePhoneNumber(user);
    }

    @Test
    public void blankOTPCode() throws InterruptedException {
        user.setUsername(""); // correct phone number but blank otp code
        user.setPassword(""); // correct password format
        changePhoneNumber(user);
    }

    @Test
    public void wrongOTPCode() throws InterruptedException {
        user.setUsername(""); // correct phone number but wrong otp code
        user.setPassword(""); // correct password format
        changePhoneNumber(user);
    }

    @Test
    public void timeoutOTPCode() throws InterruptedException {
        user.setUsername(""); // correct phone number but otp code timeout
        user.setPassword(""); // correct password format
        changePhoneNumber(user);
    }
}
