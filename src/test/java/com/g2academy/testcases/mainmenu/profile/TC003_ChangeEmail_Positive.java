package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.MainMenuBase;
import com.g2academy.model.User;
import org.testng.annotations.Test;

public class TC003_ChangeEmail_Positive extends MainMenuBase {
    private User user = new User();

    @Test
    public void correctAllData() throws InterruptedException {
        user.setEmail(""); // correct email
        user.setPassword(""); // correct password
        changeEmail(user);
    }
}
