package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.MainMenuBase;
import com.g2academy.model.User;
import org.testng.annotations.Test;

public class TC007_ChangePassword_Positive extends MainMenuBase {
    private User user = new User();
    private String newPassword;

    @Test
    public void correctAllData() throws InterruptedException {
        user.setPassword(""); // correct old password
        newPassword = ""; // correct new password
        changePassword(user, newPassword);
    }
}
