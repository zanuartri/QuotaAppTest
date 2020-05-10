package com.g2academy.basicfunctionality;

import com.g2academy.base.Assertion;
import com.g2academy.base.TestBase;
import com.g2academy.model.User;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC003_Post extends TestBase {
	private static User user = new User();

	@SuppressWarnings("unchecked")
	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.out.println("[TC003 Started]");

		user.setUsername("081252930398");
		user.setNamaUser("Zanuar Tri Romadon");
		user.setEmail("triromadon@gmail.com");
		user.setPassword("123456");

		JSONObject requestParams = new JSONObject();

		requestParams.put("username", user.getUsername());
		requestParams.put("namauser", user.getNamaUser());
		requestParams.put("email", user.getEmail());
		requestParams.put("password", user.getPassword());
		
		getHttpRequest().header("Content-Type", "application/json");
		getHttpRequest().body(requestParams.toJSONString());

		setResponse(
				getHttpRequest().request(Method.POST, "/covid19")
		);

		Thread.sleep(5);
	}
	
	@Test
	public void postAssertion() {
		System.out.println("[POST Assertion]");

		Assertion.statusCode(201);
		Assertion.statusLine("HTTP/1.1 201 Created");

		Assertion.responseBodyContains(user.getUsername());
		Assertion.responseBodyContains(user.getNamaUser());
		Assertion.responseBodyContains(user.getEmail());
		Assertion.responseBodyContains(user.getPassword());

		Assertion.contentType("application/json");
		Assertion.serverType("Cowboy");
		Assertion.vary("Accept-Encoding");
		Assertion.contentLength(1500);
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("[TC003 Finished]");
		// do some code
	}
}
