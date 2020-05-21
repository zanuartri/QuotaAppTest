package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.RequestConfig;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_EditUser extends RequestConfig {
    private User user = new User();
    private int testCaseIndex;
    private String[][] result = new String[100][16];

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
    }

    @BeforeMethod
    public void beforeMethod() {
        user.setFullName("Zanuar Tri Romadon");
        user.setEmail("testedituserbackend23@gmail.com");
        user.setPhoneNumber("+6281252930368");
        user.setPassword("Zanuar30@@");
        user.setConfirmPassword("Zanuar30@@");
        user.setPinTransaction("123456");
        register(user);
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), 200);
        Assert.assertTrue(getResponse().jsonPath().getString("message").contains(user.getPhoneNumber()));

        getOTPCode(user.getPhoneNumber());
        String generatedOtpCode = getResponse().jsonPath().getString("codeOtp");
        Assert.assertEquals(getResponse().jsonPath().getString("email"), user.getEmail());
        Assert.assertEquals(getResponse().jsonPath().getString("mobileNumber"), user.getPhoneNumber());
        Assert.assertTrue(getResponse().jsonPath().getBoolean("statusOtp"));

        sendOTPCodeRegister(user.getPhoneNumber(), generatedOtpCode, "true");
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), 200);
        Assert.assertTrue(getResponse().jsonPath().getString("message").contains("signup is successfully"));
        Assert.assertEquals(getResponse().jsonPath().getString("noTelepon"), user.getPhoneNumber());
        Assert.assertEquals(getResponse().jsonPath().getString("email"), user.getEmail());
        Assert.assertEquals(getResponse().jsonPath().getString("pinTransaksi"), user.getPinTransaction());
        Assert.assertEquals(getResponse().jsonPath().getInt("saldo"), 1000000);
    }

    @Test(dataProvider = "dataEditUser", timeOut = 30000)
    public void testEditUser(
            String description,
            String fullname,
            String phoneNumber,
            String email,
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

        editUser(user, fullname, phoneNumber, email);
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), Integer.parseInt(statusCodeRequest));
        Assert.assertTrue(getResponse().jsonPath().getString("message").contains(responseBodyRequest));
        result[testCaseIndex][6] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        testCaseIndex++;
        deleteAccount("+6281252930368");
    }

    @AfterClass
    public void afterClass() throws IOException {
        SetDataToExcel.write(result, "Edit User");
    }
}
