package com.g2academy.testcases.loginmenu;

import com.g2academy.base.Assertion;
import com.g2academy.base.LoginMenuConfig;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_Login extends LoginMenuConfig {
    private User user = new User();
    private Assertion assertion = new Assertion();
    private String[][] result = new String[100][6];
    private int testCaseIndex;

    @DataProvider(name="dataLogin")
    Object[][] getDataFromExcel() throws IOException {
        return getDataLoginMenu("Login");
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

        user.setFullName("Zanuar Tri Romadon");
        user.setEmail("testlogin@gmail.com");
        user.setPhoneNumber("+6281252930398");
        user.setPassword("Zanuar30@@");
        user.setConfirmPassword("Zanuar30@@");
        user.setPinTransaction("123456");
        deleteAcount(user.getPhoneNumber());
        System.out.println(getResponse().getBody().asString());
        register(user);
        System.out.println(getResponse().getBody().asString());
        setOtpAndTokenRegister(user, "OTP", "TRUE", "true", "");
        System.out.println(getResponse().getBody().asString());
    }

    @Test(dataProvider = "dataLogin", timeOut = 30000)
    public void testLogin(
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

        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        logout(user);
        System.out.println(getResponse().getBody().asString());
        login(user);
        System.out.println(getResponse().getBody().asString());
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);
        result[testCaseIndex][5] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        logout(user);
        System.out.println(getResponse().getBody().asString());
        testCaseIndex++;
    }

    @AfterClass
    public void afterClass() throws IOException {
        deleteAcount("+6281252930398");
        System.out.println(getResponse().getBody().asString());
        SetDataToExcel excel = new SetDataToExcel();
        excel.writeExcel(result, "Login");
    }
}
