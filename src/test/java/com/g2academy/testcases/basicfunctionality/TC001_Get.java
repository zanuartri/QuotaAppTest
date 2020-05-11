package com.g2academy.testcases.basicfunctionality;

import com.g2academy.base.Assertion;
import com.g2academy.base.TestBase;
import io.restassured.http.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC001_Get extends TestBase {
	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.out.println("[TC001 Started]");
		setResponse(getHttpRequest().request(Method.GET, "/covid19"));
		Thread.sleep(3);
	}

	@Test
	public void getAssertion() {
		System.out.println("[GET Assertion]");
		Assertion.statusCode(200);
		Assertion.responseBodyNotNull();
		Assertion.statusLine("HTTP/1.1 200 OK");
		Assertion.responseTime(5000);
		Assertion.contentType("application/json");
		Assertion.serverType("Cowboy");
		Assertion.contentEncoding("gzip");
		Assertion.transferEncoding("chunked");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("[TC001 Finished]");
		// do some code
	}
}
