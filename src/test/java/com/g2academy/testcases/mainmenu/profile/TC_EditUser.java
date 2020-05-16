package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.*;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_EditUser extends MainMenuConfig {
    private User user = new User();
    private Assertion assertion = new Assertion();
    private String[][] result = new String[100][16];
    private int testCaseIndex;
    private LoginMenuConfig loginMenuConfig = new LoginMenuConfig();

    @DataProvider(name="dataEditUser")
    Object[][] getDataFromExcel() throws IOException {
        return getDataProfileMenu("Edit User");
    }

    @BeforeClass
    public void beforeClass() {
        testCaseIndex = 1;
        result[0][0] = "description";
        result[0][1] = "fullName";
        result[0][2] = "phoneNumber";
        result[0][3] = "email";
        result[0][4] = "statusCodeRequest";
        result[0][5] = "responseBodyRequest";
        result[0][6] = "status";

        user.setFullname("Zanuar Tri Romadon");
        user.setEmail("triromadon@gmail.com");
        user.setPhonenumber("+6281252930398");
        user.setPassword("Zanuar30@@");
        user.setConfirmPassword("Zanuar30@@");
        user.setPinTransaction("123456");
        loginMenuConfig.deleteAcount(user.getPhonenumber());
        loginMenuConfig.register(user);
        loginMenuConfig.setOtpAndTokenRegister(user, "OTP", "TRUE", "true", "TRUE");
        loginMenuConfig.login(user);
    }

    @Test(dataProvider = "dataEditUser")
    public void testEditUser(
            String description,
            String fullname,
            String email,
            String phoneNumber,
            String statusCodeRequest,
            String responseBodyRequest
    ) {
        result[testCaseIndex][0] = description;
        result[testCaseIndex][1] = fullname;
        result[testCaseIndex][2] = phoneNumber;
        result[testCaseIndex][3] = email;
        result[testCaseIndex][4] = statusCodeRequest;
        result[testCaseIndex][5] = responseBodyRequest;
        result[testCaseIndex][6] = "FAILED";

        user.setFullname(fullname);
        user.setEmail(email);
        user.setPhonenumber(phoneNumber);
        editUser(user);
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);
        result[testCaseIndex][6] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        testCaseIndex++;
    }

    @AfterClass
    public void afterClass() throws IOException {
        loginMenuConfig.deleteAcount("+6281252930398");
        SetDataToExcel excel = new SetDataToExcel();
        excel.writeExcel(result, "Edit User");
    }
}
