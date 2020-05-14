package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.Assertion;
import com.g2academy.base.LoginMenuConfig;
import com.g2academy.base.MainMenuConfig;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_Logout extends MainMenuConfig {
    LoginMenuConfig loginMenu = new LoginMenuConfig();
    private User user = new User();
    private Assertion assertion = new Assertion();
    private String[][] result = new String[100][6];
    private int testCaseIndex;

    @DataProvider(name="dataLogout")
    Object[][] getDataFromExcel() throws IOException {
        return getDataProfileMenu("Logout");
    }

    @BeforeClass
    public void beforeClass() {
        testCaseIndex = 1;
        result[0][0] = "description";
        result[0][1] = "phoneNumber";
        result[0][2] = "password";
        result[0][3] = "statusCodeRequest";
        result[0][4] = "responseBodyRequest";
        result[0][5] = "status";
    }

    @BeforeMethod
    public void beforeMethod() {
        user.setPhonenumber("+6281252930398");
        user.setPassword("Zanuar30@@");
        loginMenu.login(user);
    }

    @Test(dataProvider = "dataLogout", timeOut = 15000)
    public void testLogout(
            String description,
            String phoneNumber,
            String password,
            String statusCodeRequest,
            String responseBodyRequest
    ) {
        result[testCaseIndex][0] = description;
        result[testCaseIndex][1] = phoneNumber;
        result[testCaseIndex][2] = password;
        result[testCaseIndex][3] = statusCodeRequest;
        result[testCaseIndex][4] = responseBodyRequest;
        result[testCaseIndex][5] = "FAILED";

        user.setPhonenumber(phoneNumber);
        user.setPassword(password);
        loginMenu.logout(user);
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);
        result[testCaseIndex][5] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        testCaseIndex++;
    }

    @AfterClass
    public void afterClass() throws IOException {
        SetDataToExcel excel = new SetDataToExcel();
        excel.writeExcel(result, "Logout");
    }
}
