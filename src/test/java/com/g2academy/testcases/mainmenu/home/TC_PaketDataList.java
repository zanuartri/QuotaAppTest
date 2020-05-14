package com.g2academy.testcases.mainmenu.home;

import com.g2academy.base.Assertion;
import com.g2academy.base.MainMenuConfig;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_PaketDataList extends MainMenuConfig {
    private User user = new User();
    private Assertion assertion = new Assertion();
    private String[][] result = new String[50][5];
    private int testCaseIndex;

    @DataProvider(name="paketDataList")
    Object[][] getDataFromExcel() throws IOException {
        return getDataPurchase("Paket Data List");
    }

    @BeforeClass
    public void beforeClass() {
        testCaseIndex = 1;
        result[0][0] = "description";
        result[0][1] = "phone number";
        result[0][2] = "statusCodeRequest";
        result[0][3] = "responseBodyRequest";
        result[0][4] = "status";
    }

    @Test(dataProvider = "paketDataList")
    public void testPaketDataList(
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

        user.setPhonenumber(phoneNumber);
        getPaketDataList(user);
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
        SetDataToExcel excel = new SetDataToExcel();
        excel.writeExcel(result, "Paket Data Purchase");
    }
}
