package com.g2academy.testcases.mainmenu.profile;

import com.g2academy.base.RequestConfig;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_Logout extends RequestConfig {
    private User user = new User();
    private int testCaseIndex;
    private String[][] result = new String[100][6];

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

        user.setFullName("Zanuar Tri Romadon");
        user.setEmail("testlogoutbackend23@gmail.com");
        user.setPhoneNumber("+6281252930369");
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

        login(user);
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), 200);
        Assert.assertTrue(getResponse().jsonPath().getString("message").contains("successfully"));
        Assert.assertNotNull(getResponse().jsonPath().getString("token"));
        Assert.assertEquals(getResponse().jsonPath().getString("type"), "Bearer");
        Assert.assertEquals(getResponse().jsonPath().getString("username"), user.getPhoneNumber());
        Assert.assertEquals(getResponse().jsonPath().getString("email"), user.getEmail());
        Assert.assertNotNull(getResponse().jsonPath().getString("saldo"));
    }

    @Test(dataProvider = "dataLogout", timeOut = 30000)
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

        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        logout(user);
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), Integer.parseInt(statusCodeRequest));

        if (statusCodeRequest.equals("200")) {
            Assert.assertNull(getResponse().jsonPath().getString("token"));
            Assert.assertEquals(getResponse().jsonPath().getString("type"), "Bearer");
            Assert.assertEquals(getResponse().jsonPath().getString("username"), user.getPhoneNumber());
            Assert.assertEquals(getResponse().jsonPath().getString("email"), user.getEmail());
            Assert.assertNull(getResponse().jsonPath().getString("saldo"));

            login(user);
            Assert.assertEquals(getResponse().jsonPath().getInt("status"), 200);
            Assert.assertTrue(getResponse().jsonPath().getString("message").contains("successfully"));
            Assert.assertNotNull(getResponse().jsonPath().getString("token"));
            Assert.assertEquals(getResponse().jsonPath().getString("type"), "Bearer");
            Assert.assertEquals(getResponse().jsonPath().getString("username"), user.getPhoneNumber());
            Assert.assertEquals(getResponse().jsonPath().getString("email"), user.getEmail());
            Assert.assertNotNull(getResponse().jsonPath().getString("saldo"));
        } else {
            Assert.assertTrue(getResponse().jsonPath().getString("message").contains(responseBodyRequest));
        }

        result[testCaseIndex][5] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        testCaseIndex++;
    }

    @AfterClass
    public void afterClass() throws IOException {
        deleteAccount("+6281252930369");
        SetDataToExcel.write(result, "Logout");
    }
}
