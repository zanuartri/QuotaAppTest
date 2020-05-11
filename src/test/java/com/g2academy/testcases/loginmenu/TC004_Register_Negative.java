package com.g2academy.testcases.loginmenu;

import com.g2academy.base.LoginMenuBase;
import com.g2academy.model.User;
import org.testng.annotations.Test;

public class TC004_Register_Negative extends LoginMenuBase {
    private User user = new User();
    private String confirmPassword;

    @Test
    public void wrongNamaUserFormat() throws InterruptedException {
        user.setNamaUser(""); // wrong nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void wrongEmailFormat() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // wrong email format
        user.setUsername(""); // correct username format
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void wrongUsernameFormat() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // wrong username format
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void wrongPasswordFormat() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format
        user.setPassword(""); // wrong password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void wrongConfirmPasswordFormat() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format
        user.setPassword(""); // correct password format
        confirmPassword = ""; // wrong confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void wrongAllFormat() throws InterruptedException {
        user.setNamaUser(""); // wrong nama user format
        user.setEmail(""); // wrong email format
        user.setUsername(""); // wrong username format
        user.setPassword(""); // wrong password format
        confirmPassword = ""; // wrong confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void blankNamaUser() throws InterruptedException {
        user.setNamaUser(""); // blank nama user
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void blankEmail() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // blank emailß
        user.setUsername(""); // correct username format
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void blankUsername() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // blank username
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void blankPassword() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format
        user.setPassword(""); // blank password
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void blankConfirmPassword() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format
        user.setPassword(""); // correct password format
        confirmPassword = ""; // blank confirm password
        register(user, confirmPassword);
    }

    @Test
    public void blankAllData() throws InterruptedException {
        user.setNamaUser(""); // blank nama user
        user.setEmail(""); // correct email
        user.setUsername(""); // correct username
        user.setPassword(""); // correct password
        confirmPassword = ""; // correct confirm password
        register(user, confirmPassword);
    }

    @Test
    public void invalidPhoneNumber() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format but invalid phone number
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void userAlreadyExist() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format but user already exist
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void invalidPhoneNumberAndUserAlreadyExist() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format but invalid phone number and user already exist
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void wrongPasswordConfirmation() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format but different with password
        register(user, confirmPassword);
    }

    @Test
    public void wrongOTPCodeFormat() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format but wrong otp code format
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void blankOTPCode() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format but blank otp code
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void wrongOTPCode() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format but wrong otp code
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }

    @Test
    public void timeoutOTPCode() throws InterruptedException {
        user.setNamaUser(""); // correct nama user format
        user.setEmail(""); // correct email format
        user.setUsername(""); // correct username format but otp code timeout
        user.setPassword(""); // correct password format
        confirmPassword = ""; // correct confirm password format
        register(user, confirmPassword);
    }
}
