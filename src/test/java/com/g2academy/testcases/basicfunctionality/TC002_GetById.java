package com.g2academy.testcases.basicfunctionality;

import com.g2academy.base.Assertion;
import com.g2academy.base.TestBase;
import io.restassured.http.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class TC002_GetById extends TestBase {
	private Random random = new Random();
	private String randomId;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.out.println("[TC002 Started]");
		int randomNumber = 10 + random.nextInt(10);
		randomId = Integer.toString(randomNumber);
		setResponse(getHttpRequest().request(Method.GET, "/covid19/" + randomId));
		Thread.sleep(3);
	}

	@Test
	public void getByIdAssertion() {
		System.out.println("[GET By ID Assertion]");
		Assertion.statusCode(200);
		Assertion.statusLine("HTTP/1.1 200 OK");
		Assertion.responseBodyContains(randomId);
		Assertion.responseTime(3000);
		Assertion.contentType("application/json");
		Assertion.serverType("Cowboy");
		Assertion.vary("Accept-Encoding");
		Assertion.contentLength(1500);
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("[TC002 Finished]");
		// do some code
	}
}
