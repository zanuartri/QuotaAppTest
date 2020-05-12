package com.g2academy.base;

import io.restassured.http.Method;
import org.json.simple.JSONObject;

public class RequestConfig extends TestConfig {
    public void getRequest(String path) {
        setResponse(getHttpRequest().request(Method.GET, path));
    }

    public void postRequest(JSONObject requestParams, String path) {
        getHttpRequest().header("Content-Type", "application/json");
        getHttpRequest().body(requestParams.toJSONString());
        setResponse(getHttpRequest().request(Method.POST, path));
    }

    public String getResponseBody() {
        return (String) getResponse().getBody().asString();
    }
}
