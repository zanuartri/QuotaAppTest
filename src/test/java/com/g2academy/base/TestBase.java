package com.g2academy.base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.logging.Logger;

public class TestBase {
	private static RequestSpecification httpRequest;
	private static Response response;
	private static Logger logger;

	public static RequestSpecification getHttpRequest() {
		return httpRequest;
	}

	public static void setHttpRequest(RequestSpecification httpRequest) {
		TestBase.httpRequest = httpRequest;
	}

	public static Response getResponse() {
		return response;
	}

	public static void setResponse(Response response) {
		TestBase.response = response;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		TestBase.logger = logger;
	}

	@BeforeTest
	public void setup() {
		logger = Logger.getLogger("QuotaAppRestAPI");
		PropertyConfigurator.configure("Log4j.properties");
		RestAssured.baseURI = "https://5e9fa8e411b078001679c9e5.mockapi.io/g2academy/";
		httpRequest = RestAssured.given();
	}

	@AfterTest
	public void teardown() {
		// do some code

	}
}
