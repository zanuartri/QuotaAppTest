package com.g2academy.testcases.mainmenu.home;

import com.g2academy.base.Assertion;
import com.g2academy.base.LoginMenuConfig;
import com.g2academy.base.MainMenuConfig;
import com.g2academy.model.User;
import com.g2academy.utilities.SetDataToExcel;
import org.testng.annotations.*;

import java.io.IOException;

public class TC_PaketDataPurchase extends MainMenuConfig {
    private User user = new User();
    private Assertion assertion = new Assertion();
    private String[][] result = new String[200][18];
    private int testCaseIndex;
    private LoginMenuConfig loginMenuConfig = new LoginMenuConfig();

    @DataProvider(name="paketDataPurchase")
    Object[][] getDataFromExcel() throws IOException {
        return getDataPurchase("Paket Data Purchase");
    }

    @BeforeClass
    public void beforeClass() {
        testCaseIndex = 1;
        result[0][0] = "description";
        result[0][1] = "phoneNumber";
        result[0][2] = "phoneNumberForPaketData";
        result[0][3] = "provider";
        result[0][4] = "price";
        result[0][5] = "paketData";
        result[0][6] = "statusCodeRequest";
        result[0][7] = "responseBodyRequest";
        result[0][8] = "paymentMethod";
        result[0][9] = "phoneNumberForPayment";
        result[0][10] = "pinTransaction";
        result[0][11] = "virtualAccount";
        result[0][12] = "statusCodeConfirmation";
        result[0][13] = "responseBodyConfirmation";
        result[0][14] = "imageName";
        result[0][15] = "statusCodeInvoice";
        result[0][16] = "responseBodyInvoice";
        result[0][17] = "status";
        user.setFullName("Zanuar Tri Romadon");
        user.setEmail("testpurchasebackend@gmail.com");
        user.setPhoneNumber("+6281252930398");
        user.setPassword("Zanuar30@@");
        user.setConfirmPassword("Zanuar30@@");
        user.setPinTransaction("123456");
        loginMenuConfig.deleteAcount(user.getPhoneNumber());
        System.out.println(getResponse().getBody().asString());
        loginMenuConfig.register(user);
        System.out.println(getResponse().getBody().asString());
        loginMenuConfig.setOtpAndTokenRegister(user, "OTP", "TRUE", "true", "");
        System.out.println(getResponse().getBody().asString());
        loginMenuConfig.login(user);
        System.out.println(getResponse().getBody().asString());
    }

    @Test(dataProvider = "paketDataPurchase", timeOut = 30000)
    public void testPaketDataPurchase(
            String description,
            String phoneNumber,
            String phoneNumberForPaketData,
            String provider,
            String price,
            String paketData,
            String statusCodeRequest,
            String responseBodyRequest,
            String paymentMethod,
            String phoneNumberForPayment,
            String pinTransaction,
            String virtualAccount,
            String statusCodeConfirmation,
            String responseBodyConfirmation,
            String imageName,
            String statusCodeInvoice,
            String responseBodyInvoice
    ) {
        result[testCaseIndex][0] = description;
        result[testCaseIndex][1] = phoneNumber;
        result[testCaseIndex][2] = phoneNumberForPaketData;
        result[testCaseIndex][3] = provider;
        result[testCaseIndex][4] = price;
        result[testCaseIndex][5] = paketData;
        result[testCaseIndex][6] = statusCodeRequest;
        result[testCaseIndex][7] = responseBodyRequest;
        result[testCaseIndex][8] = paymentMethod;
        result[testCaseIndex][9] = phoneNumberForPayment;
        result[testCaseIndex][10] = pinTransaction;
        result[testCaseIndex][11] = virtualAccount;
        result[testCaseIndex][12] = statusCodeConfirmation;
        result[testCaseIndex][13] = responseBodyConfirmation;
        result[testCaseIndex][14] = imageName;
        result[testCaseIndex][15] = statusCodeInvoice;
        result[testCaseIndex][16] = responseBodyInvoice;
        result[testCaseIndex][17] = "FAILED";

        user.setPhoneNumber(phoneNumber);
        purchasePaketData(user, phoneNumberForPaketData, provider, price, paketData);
        System.out.println(getResponse().getBody().asString());
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);

        if (paymentMethod.equals("QWALLET")) {
            user.setPhoneNumber(phoneNumberForPayment);
            user.setPinTransaction(pinTransaction);
            payWithQWallet(user);
            System.out.println(getResponse().getBody().asString());
            assertion.statusCode(Integer.parseInt(statusCodeConfirmation));
            assertion.responseBodyContains(responseBodyConfirmation);
        } else if (paymentMethod.equals("VA")) {
            user.setPhoneNumber(phoneNumberForPayment);
            user.setVirtualAccount(virtualAccount);
            payWithVirtualAccount(user);
            System.out.println(getResponse().getBody().asString());
            assertion.statusCode(Integer.parseInt(statusCodeConfirmation));
            assertion.responseBodyContains(responseBodyConfirmation);

            if (!imageName.equals("-")) {
                uploadInvoice("resources/image/DANA.jpg");
                System.out.println(getResponse().getBody().asString());
                assertion.statusCode(Integer.parseInt(statusCodeInvoice));
                assertion.responseBodyContains(responseBodyInvoice);
            }
        }

        result[testCaseIndex][17] = "SUCCESS";
    }

    @AfterMethod
    public void afterMethod() {
        testCaseIndex++;
    }

    @AfterClass
    public void afterClass() throws IOException {
        loginMenuConfig.deleteAcount("+6281252930398");
        System.out.println(getResponse().getBody().asString());
        SetDataToExcel excel = new SetDataToExcel();
        excel.writeExcel(result, "Paket Data Purchase");
    }
}
