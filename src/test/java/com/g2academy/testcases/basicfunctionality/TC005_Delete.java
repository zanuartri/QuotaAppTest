package com.g2academy.testcases.basicfunctionality;

import com.g2academy.base.Assertion;
import com.g2academy.base.TestBase;
import io.restassured.http.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class TC005_Delete extends TestBase {
	private Random random = new Random();

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.out.println("[TC005 Started]");
		int randomNumber = 20 + random.nextInt(10);
		String randomId = Integer.toString(randomNumber);
		setResponse(getHttpRequest().request(Method.DELETE, "/covid19/" + randomId));
		Thread.sleep(3);
	}
	
	@Test
	public void deleteAssertion() {
		System.out.println("[DELETE Assertion]");
		Assertion.statusCode(200);
		Assertion.statusLine("HTTP/1.1 200 OK");
		Assertion.contentType("application/json");
		Assertion.serverType("Cowboy");
		Assertion.vary("Accept-Encoding");
		Assertion.contentLength(1500);
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("[TC005 Finished]");
		// do some code
	}
}
