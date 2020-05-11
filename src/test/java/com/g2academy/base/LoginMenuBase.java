package com.g2academy.base;

import com.g2academy.model.User;
import io.restassured.http.Method;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class LoginMenuBase extends TestBase {
    public static void login(User user) throws InterruptedException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", user.getUsername());
        requestParams.put("password", user.getPassword());

        getHttpRequest().header("Content-Type", "application/json");
        getHttpRequest().body(requestParams.toJSONString());
        setResponse(getHttpRequest().request(Method.POST, "/login"));

        Thread.sleep(5);
    }

    public static void register(User user, String confirmPassword) throws InterruptedException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("namauser", user.getNamaUser());
        requestParams.put("email", user.getEmail());
        requestParams.put("username", user.getUsername());
        requestParams.put("password", user.getPassword());
        requestParams.put("confirmpassword", confirmPassword);

        getHttpRequest().header("Content-Type", "application/json");
        getHttpRequest().body(requestParams.toJSONString());
        setResponse(getHttpRequest().request(Method.POST, "/register"));

        Thread.sleep(5);
    }

    public static void forgotPassword(String newPassword) throws InterruptedException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("newPassword", newPassword);

        getHttpRequest().header("Content-Type", "application/json");
        getHttpRequest().body(requestParams.toJSONString());
        setResponse(getHttpRequest().request(Method.POST, "/forgotpassword"));

        Thread.sleep(5);
    }
}
