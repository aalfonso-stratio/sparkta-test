package com.stratio.tests.policyContexts;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.stratio.tests.utils.Utils;

public class Get {
    Properties defaultProps;
    String swagger_url;
    
    @BeforeTest
    public void prepareGetTest() throws Exception {
	// Read properties file
	Properties swaggerProps = new Properties();
	swaggerProps.load(new FileInputStream("swagger.properties"));
	swagger_url = swaggerProps.getProperty("swagger_url");
	defaultProps = new Properties();
	defaultProps.load(new FileInputStream("policyContexts.properties"));
	
	// Clean everything
	Utils.cleanUp(swagger_url);
    }
    
    @Test(description = "Get all policy contexts when none available")
    public void get01() throws Exception {
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get01 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get01 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get01 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	// TIMING OUT
	//Assert.assertEquals(responseBody, "[]");
    }
    
    @Test(description = "Get policy context using empty parameter")
    public void get02() throws Exception {
	String url = swagger_url + "/policyContext/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get02 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get02 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get02 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");
    }
    
    @Test(description = "Get policy context when none available")
    public void get03() throws Exception {
	String url = swagger_url + "/policyContext/" + "name";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get03 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get03 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get03 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	// TIMING OUT
	//Assert.assertEquals(responseBody, "The requested resource could not be found.");
    }
    
    @Test(description = "Get all policy contexts")
    public void get04() throws Exception {
	// Add a policy context
	String policyExample = defaultProps.getProperty("policyExample");	
	
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, policyExample);

	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	System.out.println("get04: Policy Context added.");
	
	// Get all policy contexts
	String url2 = swagger_url + "/policyContext";
	HttpResponse response2 = Utils.sendGetRequest(url2);
	String responseBody2 = Utils.getResponseBody(response2);
	
	System.out.println("get04 Response Code: " + response2.getStatusLine().getStatusCode());
	System.out.println("get04 Response Message: " + response2.getStatusLine().getReasonPhrase());
	System.out.println("get04 Response Body: " + responseBody2);
	
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "OK");
	
	JSONArray bodyJson  = new JSONArray(responseBody2);
	Assert.assertEquals(bodyJson.length(), 1);
	
	// TIMING OUT
    }
    
    @Test(description = "Get non-existing policy context")
    public void get05() throws Exception {
	String url = swagger_url + "/policyContext/" + "name";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get05 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get05 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get05 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	// TIMING OUT
    }
    
    @Test(description = "Get existing policy context")
    public void get06() throws Exception {
	String policyExampleName = defaultProps.getProperty("policyExampleName");
	
	String url = swagger_url + "/policyContext/" + policyExampleName;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get06 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get06 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get06 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	// TIMING OUT
    }    
    
    @AfterTest
    public void cleanGetTest() throws Exception {
	Utils.cleanUp(swagger_url);
    }   
}