package com.g2academy.testcases.mainmenu.home;

import com.g2academy.base.Assertion;
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
    }

    @Test(dataProvider = "paketDataPurchase")
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
        result[0][0] = description;
        result[0][1] = phoneNumber;
        result[0][2] = phoneNumberForPaketData;
        result[0][3] = provider;
        result[0][4] = price;
        result[0][5] = paketData;
        result[0][6] = statusCodeRequest;
        result[0][7] = responseBodyRequest;
        result[0][8] = paymentMethod;
        result[0][9] = phoneNumberForPayment;
        result[0][10] = pinTransaction;
        result[0][11] = virtualAccount;
        result[0][12] = statusCodeConfirmation;
        result[0][13] = responseBodyConfirmation;
        result[0][14] = imageName;
        result[0][15] = statusCodeInvoice;
        result[0][16] = responseBodyInvoice;
        result[0][17] = "FAILED";

        user.setPhonenumber(phoneNumber);
        user.setPinTransaction(pinTransaction);
        user.setVirtualAccount(virtualAccount);
        purchasePaketData(user, phoneNumberForPaketData, provider, price, paketData);
        assertion.statusCode(Integer.parseInt(statusCodeRequest));
        assertion.responseBodyContains(responseBodyRequest);

        if (paymentMethod.equals("QWALLET")) {
            payWithQWallet(user);
            assertion.statusCode(Integer.parseInt(statusCodeConfirmation));
            assertion.responseBodyContains(responseBodyConfirmation);
        } else if (paymentMethod.equals("VA")) {
            payWithVirtualAccount(user);
            assertion.statusCode(Integer.parseInt(statusCodeConfirmation));
            assertion.responseBodyContains(responseBodyConfirmation);

            if (!imageName.equals("-")) {
                uploadBuktiPembayaran("resources/image/DANA.jpg");
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
        SetDataToExcel excel = new SetDataToExcel();
        excel.writeExcel(result, "Paket Data Purchase");
    }
}
