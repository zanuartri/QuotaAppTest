package com.g2academy.base;

import com.g2academy.model.User;
import io.restassured.http.Method;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class MainMenuBase extends TestBase {
    public static void changeFullName(User user) throws InterruptedException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("namauser", user.getNamaUser());
        requestParams.put("username", user.getUsername());
        requestParams.put("password", user.getPassword());

        getHttpRequest().header("Content-Type", "application/json");
        getHttpRequest().body(requestParams.toJSONString());
        setResponse(getHttpRequest().request(Method.POST, "/login"));

        Thread.sleep(5);
    }

    public static void changeEmail(User user) throws InterruptedException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", user.getEmail());
        requestParams.put("username", user.getUsername());
        requestParams.put("password", user.getPassword());

        getHttpRequest().header("Content-Type", "application/json");
        getHttpRequest().body(requestParams.toJSONString());
        setResponse(getHttpRequest().request(Method.POST, "/login"));

        Thread.sleep(5);
    }

    public static void changePhoneNumber(User user) throws InterruptedException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", user.getUsername());
        requestParams.put("password", user.getPassword());

        getHttpRequest().header("Content-Type", "application/json");
        getHttpRequest().body(requestParams.toJSONString());
        setResponse(getHttpRequest().request(Method.POST, "/login"));

        Thread.sleep(5);
    }

    public static void changePassword(User user, String newPassword) throws InterruptedException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", user.getUsername());
        requestParams.put("password", user.getPassword());
        requestParams.put("newpassword", newPassword);

        getHttpRequest().header("Content-Type", "application/json");
        getHttpRequest().body(requestParams.toJSONString());
        setResponse(getHttpRequest().request(Method.POST, "/login"));

        Thread.sleep(5);
    }
}
