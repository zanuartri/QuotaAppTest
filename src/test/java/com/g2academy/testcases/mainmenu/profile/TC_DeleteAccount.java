package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.Assertion;
import com.g2academy.base.LoginMenuConfig;
import com.g2academy.base.MainMenuConfig;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_DeleteAccount extends MainMenuConfig {
    LoginMenuConfig loginMenu = new LoginMenuConfig();
    private User user = new User();
    private Assertion assertion = new Assertion();
    private String[][] result = new String[100][6];
    private int testCaseIndex;

    @DataProvider(name="dataDelete")
    Object[][] getDataFromExcel() throws IOException {
        return getDataProfileMenu("Delete");
    }

    @BeforeClass
    public void beforeClass() {
        testCaseIndex = 1;
        result[0][0] = "description";
        result[0][1] = "phoneNumber";
        result[0][2] = "statusCodeRequest";
        result[0][3] = "responseBodyRequest";
        result[0][4] = "status";
    }

    @BeforeMethod
    public void beforeMethod() {
        user.setFullName("Zanuar Tri Romadon");
        user.setEmail("testdeletebackend23@gmail.com");
        user.setPhoneNumber("+6281252930367");
        user.setPassword("Zanuar30@@");
        user.setConfirmPassword("Zanuar30@@");
        user.setPinTransaction("123456");
        loginMenu.register(user);
        System.out.println(getResponse().getBody().asString());
        loginMenu.setOtpAndTokenRegister(user, "OTP", "TRUE", "true", "");
        System.out.println(getResponse().getBody().asString());
    }

    @Test(dataProvider = "dataDelete", timeOut = 30000)
    public void testDeleteAccount(
            String description,
            String phoneNumber,
            String statusCodeRequest,
            String responseBodyRequest
    ) {
        result[testCaseIndex][0] = description;
        result[testCaseIndex][1] = phoneNumber;
        result[testCaseIndex][2] = statusCodeRequest;
        result[testCaseIndex][3] = responseBodyRequest;
        result[testCaseIndex][4] = "FAILED";

        loginMenu.deleteAcount(phoneNumber);
        System.out.println(getResponse().getBody().asString());
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);
        result[testCaseIndex][4] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        testCaseIndex++;
        loginMenu.deleteAcount("+6281252930367");
        System.out.println(getResponse().getBody().asString());
    }

    @AfterClass
    public void afterClass() throws IOException {
        SetDataToExcel excel = new SetDataToExcel();
        excel.writeExcel(result, "Delete");
    }
}
