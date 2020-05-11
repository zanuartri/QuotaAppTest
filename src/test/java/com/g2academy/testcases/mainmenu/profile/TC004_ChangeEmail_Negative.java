package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.MainMenuBase;
import com.g2academy.model.User;
import org.testng.annotations.Test;

public class TC004_ChangeEmail_Negative extends MainMenuBase {
    private User user = new User();

    @Test
    public void wrongEmailFormat() throws InterruptedException {
        user.setEmail(""); // wrong email format
        user.setPassword(""); // correct password format
        changeEmail(user);
    }

    @Test
    public void wrongPasswordFormat() throws InterruptedException {
        user.setEmail(""); // correct email format
        user.setPassword(""); // wrong password format
        changeEmail(user);
    }

    @Test
    public void wrongEmailAndPasswordFormat() throws InterruptedException {
        user.setEmail(""); // wrong email format
        user.setPassword(""); // wrong password format
        changeEmail(user);
    }

    @Test
    public void blankEmail() throws InterruptedException {
        user.setEmail(""); // blank email
        user.setPassword(""); // correct password format
        changeEmail(user);
    }

    @Test
    public void blankPassword() throws InterruptedException {
        user.setEmail(""); // correct email format
        user.setPassword(""); // blank password
        changeEmail(user);
    }

    @Test
    public void blankEmailAndPassword() throws InterruptedException {
        user.setEmail(""); // blank email
        user.setPassword(""); // blank password
        changeEmail(user);
    }

    @Test
    public void wrongPasswordData() throws InterruptedException {
        user.setEmail(""); // correct email format
        user.setPassword(""); // wrong password
        changeEmail(user);
    }

    @Test
    public void wrongOTPCodeFormat() throws InterruptedException {
        user.setEmail(""); // correct email format
        user.setPassword(""); // correct password but wrong otp code format
        changeEmail(user);
    }

    @Test
    public void blankOTPCode() throws InterruptedException {
        user.setEmail(""); // correct email format
        user.setPassword(""); // correct password but blank otp code
        changeEmail(user);
    }

    @Test
    public void wrongOTPCode() throws InterruptedException {
        user.setEmail(""); // correct email format
        user.setPassword(""); // correct password but wrong otp code
        changeEmail(user);
    }

    @Test
    public void timeoutOTPCode() throws InterruptedException {
        user.setEmail(""); // correct email format
        user.setPassword(""); // correct password but otp code timeout
        changeEmail(user);
    }
}
