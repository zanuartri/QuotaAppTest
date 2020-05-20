package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.RequestConfig;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_DeleteAccount extends RequestConfig {
    private User user = new User();
    private int testCaseIndex;
    private String[][] result = new String[100][6];

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

        user.setFullName("Zanuar Tri Romadon");
        user.setEmail("testdeletebackend23@gmail.com");
        user.setPhoneNumber("+6281252930367");
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

        deleteAccount(phoneNumber);
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), Integer.parseInt(statusCodeRequest));
        Assert.assertTrue(getResponse().jsonPath().getString("message").contains(responseBodyRequest));

        if (statusCodeRequest.equals("200")) {
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

        result[testCaseIndex][4] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        testCaseIndex++;
    }

    @AfterClass
    public void afterClass() throws IOException {
        deleteAccount("+6281252930367");
        Assert.assertEquals(getResponse().getStatusCode(), 200);
        SetDataToExcel.write(result, "Delete");
    }
}
