package com.g2academy.testcases.mainmenu.history;

import com.g2academy.base.RequestConfig;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_PaketDataHistory extends RequestConfig {
    private User user = new User();
    private int testCaseIndex;
    private String[][] result = new String[50][5];

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

        user.setFullName("Zanuar Tri Romadon");
        user.setEmail("testhistorybackend25@gmail.com");
        user.setPhoneNumber("+6281252930353");
        user.setPassword("Zanuar30@@");
        user.setConfirmPassword("Zanuar30@@");
        user.setPinTransaction("123456");

        register(user);
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), 200);
        Assert.assertTrue(getResponse().jsonPath().getString("message").contains(user.getPhoneNumber()));

        getToken(user.getEmail());
        String generatedToken = getResponse().jsonPath().getString("codeVerify");
        Assert.assertEquals(getResponse().jsonPath().getString("email"), user.getEmail());
        Assert.assertEquals(getResponse().jsonPath().getString("mobileNumber"), user.getPhoneNumber());
        Assert.assertTrue(getResponse().jsonPath().getBoolean("statusEmailVerify"));

        sendTokenRegister(generatedToken);
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), 200);
        Assert.assertEquals(getResponse().jsonPath().getString("message"), "signup is successfully");
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

        purchasePaketData(user, user.getPhoneNumber(), "Simpati", "27000", "Paket-Internet-1GB");
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), 200);
        Assert.assertTrue(getResponse().jsonPath().getString("message").contains("successfully"));
        Assert.assertEquals(getResponse().jsonPath().getString("namaProvider"), "Simpati");
        Assert.assertEquals(getResponse().jsonPath().getString("harga"), "27000");
        Assert.assertEquals(getResponse().jsonPath().getString("paketData"), "Paket-Internet-1GB");
        Assert.assertEquals(getResponse().jsonPath().getString("nomorPaketData"), user.getPhoneNumber());
        Assert.assertEquals(getResponse().jsonPath().getString("noTelepon"), user.getPhoneNumber());

        payWithQWallet(user);
        Assert.assertEquals(getResponse().jsonPath().getInt("status"), 200);
        Assert.assertTrue(getResponse().jsonPath().getString("message").contains("Transaction successfully"));
        Assert.assertEquals(getResponse().jsonPath().getString("namaUser"), user.getFullName());
        Assert.assertEquals(getResponse().jsonPath().getString("nomorTeleponUser"), user.getPhoneNumber());
        Assert.assertEquals(getResponse().jsonPath().getString("nomorPaketData"), user.getPhoneNumber());
        Assert.assertEquals(getResponse().jsonPath().getString("namaProvider"), "Simpati");
        Assert.assertEquals(getResponse().jsonPath().getString("paketData"), "Paket-Internet-1GB");
        Assert.assertEquals(getResponse().jsonPath().getString("harga"), "27000");
        Assert.assertEquals(getResponse().jsonPath().getString("pembayaranMelalui"), "E-Walet");
        Assert.assertTrue(getResponse().jsonPath().getBoolean("statusPembayaran"));

    }

    @Test(dataProvider = "paketDataHistory", timeOut = 30000)
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
        Assert.assertEquals(getResponse().getStatusCode(), Integer.parseInt(statusCodeRequest));

        if (!responseBodyRequest.isEmpty()) {
            Assert.assertTrue(getResponse().jsonPath().getString("history").contains(user.getFullName()));
            Assert.assertTrue(getResponse().jsonPath().getString("history").contains(user.getPhoneNumber()));
            Assert.assertTrue(getResponse().jsonPath().getString("history").contains("Simpati"));
            Assert.assertTrue(getResponse().jsonPath().getString("history").contains("Paket-Internet-1GB"));
            Assert.assertTrue(getResponse().jsonPath().getString("history").contains("27000"));
            Assert.assertTrue(getResponse().jsonPath().getString("history").contains("E-Walet"));
            Assert.assertTrue(getResponse().jsonPath().getString("history").contains("Transaction successfully"));
        }

        result[testCaseIndex][4] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        testCaseIndex++;
    }

    @AfterClass
    public void afterClass() throws IOException {
        deleteAccount("+6281252930353");
        Assert.assertEquals(getResponse().getStatusCode(), 200);
        SetDataToExcel.write(result, "Paket Data History");
    }
}
