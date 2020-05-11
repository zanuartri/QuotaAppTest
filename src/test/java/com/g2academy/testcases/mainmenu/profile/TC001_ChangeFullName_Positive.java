package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.MainMenuBase;
import com.g2academy.model.User;
import org.testng.annotations.Test;

public class TC001_ChangeFullName_Positive extends MainMenuBase {
    private User user = new User();

    @Test
    public void correctAllData() throws InterruptedException {
        user.setNamaUser(""); // correct full name
        user.setPassword(""); // correct password
        changeFullName(user);
    }
}
