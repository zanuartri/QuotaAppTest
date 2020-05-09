package com.g2academy.base;

import org.testng.Assert;

public class Assertion extends TestBase {
    public static void statusCode(int expectedStatusCode) {
        int actualStatusCode = getResponse().getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    public static void responseBodyNotNull() {
        String responseBody = getResponse().getBody().asString();
        Assert.assertNotNull(responseBody);
    }

    public static void responseBodyContains(String data) {
        String responseBody = getResponse().getBody().asString();
        Assert.assertTrue(responseBody.contains(data));
    }

    public static void responseTime(int maxResponseTime) {
        long responseTime = getResponse().getTime();
        Assert.assertTrue(responseTime <= maxResponseTime);
    }

    public static void statusLine(String expectedStatusLine) {
        String actualStatusLine = getResponse().getStatusLine();
        Assert.assertEquals(actualStatusLine, expectedStatusLine);
    }

    public static void contentType(String expectedContentType) {
        String actualContentType = getResponse().header("Content-Type");
        Assert.assertEquals(actualContentType, expectedContentType);
    }

    public static void serverType(String expectedServerType) {
        String actualServerType = getResponse().header("Server");
        Assert.assertEquals(actualServerType, expectedServerType);
    }

    public static void contentEncoding(String expectedContentEncoding) {
        String actualContentEncoding = getResponse().header("Content-Encoding");
        Assert.assertEquals(actualContentEncoding, expectedContentEncoding);
    }

    public static void transferEncoding(String expectedTransferEncoding) {
        String actualTransferEncoding = getResponse().header("Transfer-Encoding");
        Assert.assertEquals(actualTransferEncoding, expectedTransferEncoding);
    }

    public static void vary(String expectedVary) {
        String actualVary = getResponse().header("Vary");
        Assert.assertEquals(actualVary, expectedVary);
    }

    public static void contentLength(int maxContentLength) {
        String contentLength = getResponse().header("Content-Length");
        Assert.assertTrue(Integer.parseInt(contentLength) <= maxContentLength);
    }
}
