package com.g2academy.base;

import io.restassured.http.Method;
import org.json.simple.JSONObject;

import java.io.File;

public class RequestConfig extends TestConfig {
    public void getRequest(String path) {
        setResponse(getHttpRequest().request(Method.GET, path));
    }

    public void postRequest(JSONObject requestParams, String path) {
        getHttpRequest().header("Content-Type", "application/json");
        getHttpRequest().body(requestParams.toJSONString());
        setResponse(getHttpRequest().request(Method.POST, path));
    }

    public void putRequest(JSONObject requestParams, String path) {
        getHttpRequest().header("Content-Type", "application/json");
        getHttpRequest().body(requestParams.toJSONString());
        setResponse(getHttpRequest().request(Method.PUT, path));
    }

    public void deleteRequest(String path) {
        setResponse(getHttpRequest().request(Method.DELETE, path));
    }

    public void uploadImage(String imageName, String path) {
        File file = new File(imageName);
        setResponse(getHttpRequest().multiPart(file).when().post(path));
    }

    public String getResponseBody() {
        return (String) getResponse().getBody().asString();
    }
}
