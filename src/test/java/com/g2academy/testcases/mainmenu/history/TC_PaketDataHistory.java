package com.g2academy.testcases.mainmenu.history;

import com.g2academy.base.Assertion;
import com.g2academy.base.LoginMenuConfig;
import com.g2academy.base.MainMenuConfig;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_PaketDataHistory extends MainMenuConfig {
    private User user = new User();
    private Assertion assertion = new Assertion();
    private String[][] result = new String[50][5];
    private int testCaseIndex;
    private LoginMenuConfig loginMenuConfig = new LoginMenuConfig();

    @DataProvider(name="paketDataHistory")
    Object[][] getDataFromExcel() throws IOException {
        return getDataPurchase("Paket Data History");
    }

    @BeforeClass
    public void beforeClass() {
        testCaseIndex = 1;
        result[0][0] = "description";
        result[0][1] = "phone number";
        result[0][2] = "statusCodeRequest";
        result[0][3] = "responseBodyRequest";
        result[0][4] = "status";

        user.setFullname("Zanuar Tri Romadon");
        user.setEmail("triromadon@gmail.com");
        user.setPhonenumber("+6281252930398");
        user.setPassword("Zanuar30@@");
        user.setConfirmPassword("Zanuar30@@");
        user.setPinTransaction("123456");
        loginMenuConfig.register(user);
        loginMenuConfig.setOtpAndTokenRegister(user, "OTP", "TRUE", "true", "TRUE");
        loginMenuConfig.login(user);
    }

    @Test(dataProvider = "paketDataHistory")
    public void testPaketDataHistory(
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

        getPaketDataHistory(phoneNumber);
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);
        result[testCaseIndex][4] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        testCaseIndex++;
    }

    @AfterClass
    public void afterClass() throws IOException {
        loginMenuConfig.deleteAcount("+6281252930398");
        SetDataToExcel excel = new SetDataToExcel();
        excel.writeExcel(result, "Paket Data History");
    }
}
