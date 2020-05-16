package com.g2academy.base;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;

import java.io.File;

public class RequestConfig extends TestConfig {
    public void getRequest(String path) {
        setResponse(getHttpRequest().request(Method.GET, path));
    }

    public void postRequest(JSONObject requestParams, String path) {
        setResponse(getHttpRequest()
                .body(requestParams.toJSONString())
                .header("Content-Type", "application/json")
                .request(Method.POST, path));
    }

    public void putRequest(JSONObject requestParams, String path) {
        setResponse(getHttpRequest()
                .body(requestParams.toJSONString())
                .header("Content-Type", "application/json")
                .request(Method.PUT, path));
    }

    public void deleteRequest(String path) {
        setResponse(getHttpRequest().request(Method.DELETE, path));
    }

    public void uploadImage(String imageName, String path) {
        File file = new File(imageName);
        setResponse(getHttpRequest()
                .header("content-type", "multipart/form-data")
                .multiPart(file)
                .when()
                .post(path));
        setHttpRequest(RestAssured.given());
    }

    public String getResponseBody() {
        return (String) getResponse().getBody().asString();
    }
}
