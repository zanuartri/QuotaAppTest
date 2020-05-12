package com.g2academy.base;

import org.testng.Assert;

public class Assertion extends TestConfig {
    public void statusCode(int expectedStatusCode) {
        int actualStatusCode = getResponse().getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    public void responseBodyNotNull() {
        String responseBody = getResponse().getBody().asString();
        Assert.assertNotNull(responseBody);
    }

    public void responseBodyContains(String data) {
        String responseBody = getResponse().getBody().asString();
        Assert.assertTrue(responseBody.contains(data));
    }

    public void responseTime(int maxResponseTime) {
        long responseTime = getResponse().getTime();
        Assert.assertTrue(responseTime <= maxResponseTime);
    }

    public void statusLine(String expectedStatusLine) {
        String actualStatusLine = getResponse().getStatusLine();
        Assert.assertEquals(actualStatusLine, expectedStatusLine);
    }

    public void contentType(String expectedContentType) {
        String actualContentType = getResponse().header("Content-Type");
        Assert.assertEquals(actualContentType, expectedContentType);
    }

    public void serverType(String expectedServerType) {
        String actualServerType = getResponse().header("Server");
        Assert.assertEquals(actualServerType, expectedServerType);
    }

    public void contentEncoding(String expectedContentEncoding) {
        String actualContentEncoding = getResponse().header("Content-Encoding");
        Assert.assertEquals(actualContentEncoding, expectedContentEncoding);
    }

    public void transferEncoding(String expectedTransferEncoding) {
        String actualTransferEncoding = getResponse().header("Transfer-Encoding");
        Assert.assertEquals(actualTransferEncoding, expectedTransferEncoding);
    }

    public void vary(String expectedVary) {
        String actualVary = getResponse().header("Vary");
        Assert.assertEquals(actualVary, expectedVary);
    }

    public void contentLength(int maxContentLength) {
        String contentLength = getResponse().header("Content-Length");
        Assert.assertTrue(Integer.parseInt(contentLength) <= maxContentLength);
    }
}
