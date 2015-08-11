package com.stratio.tests.policies;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.stratio.tests.utils.Utils;

public class Delete {
    Properties defaultProps;
    String swagger_url;
    
    @BeforeTest
    public void prepareGetTest() throws Exception {
	// Read properties file
	Properties swaggerProps = new Properties();
	swaggerProps.load(new FileInputStream("swagger.properties"));
	swagger_url = swaggerProps.getProperty("swagger_url");
	defaultProps = new Properties();
	defaultProps.load(new FileInputStream("policies.properties"));
	
	// Clean everything
	Utils.cleanUp(swagger_url);
    }
    
    @Test(description = "Delete a policy when no policies available")
    public void delete01() throws Exception {
	String nonExistingPolicy = defaultProps.getProperty("nonExistingPolicy");
	
	String url = swagger_url + "/policy/" + nonExistingPolicy;
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("delete01 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("delete01 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("delete01 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Delete a non-existing policy")
    public void delete02() throws Exception {
	String nonExistingPolicy = defaultProps.getProperty("nonExistingPolicy");
	
	String url = swagger_url + "/policy/" + nonExistingPolicy;
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("delete02 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("delete02 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("delete02 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Delete a existing policy")
    public void delete03() throws Exception {
	// Add policy
	String policyExample = defaultProps.getProperty("policyExample");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyExample);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
	
	System.out.println("delete03: Policy added");
	
	// Delete policy
	String existingPolicy = defaultProps.getProperty("existingPolicy");
	
	String url2 = swagger_url + "/policy/" + existingPolicy;
	HttpResponse response2 = Utils.sendDeleteRequest(url2);
	String responseBody2 = Utils.getResponseBody(response2);
	
	System.out.println("delete03 Response Code: " + response2.getStatusLine().getStatusCode());
	System.out.println("delete03 Response Message: " + response2.getStatusLine().getReasonPhrase());
	System.out.println("delete03 Response Body: " + responseBody2);
	
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "OK");
    }
    
    @Test(description = "Delete a existing policy with fragments")
    public void delete04() throws Exception {
	// Add fragment
	String fragmentExample = defaultProps.getProperty("fragmentExample");

	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, fragmentExample);
              
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
	
	System.out.println("delete04: Fragment added");
	
	// Add policy with fragment
	String policyExampleOneFragment = defaultProps.getProperty("policyExampleOneFragment");
	
	String url2 = swagger_url + "/policy";
	HttpResponse response2 = Utils.sendPostRequest(url2, policyExampleOneFragment);
		
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "Created");
	
	System.out.println("delete04: Policy added");
	
	// Delete policy with fragments
	String policyExampleOneFragmentName = defaultProps.getProperty("policyExampleOneFragmentName");
	
	String url3 = swagger_url + "/policy/" + policyExampleOneFragmentName;
	HttpResponse response3 = Utils.sendDeleteRequest(url3);
	String responseBody3 = Utils.getResponseBody(response3);
		
	System.out.println("delete04 Delete Response Code: " + response3.getStatusLine().getStatusCode());
	System.out.println("delete04 Delete Response Message: " + response3.getStatusLine().getReasonPhrase());
	System.out.println("delete04 Delete Response Body: " + responseBody3);
	
	Assert.assertEquals(response3.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response3.getStatusLine().getReasonPhrase(), "OK");
    }
    
    @Test(description = "Delete a policy with empty parameter")
    public void delete05() throws Exception {
	String url = swagger_url + "/policy/" + "";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("delete05 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("delete05 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("delete05 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 405);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Method Not Allowed");
	Assert.assertEquals(responseBody, "HTTP method not allowed, supported methods: GET");
    }
    
    @AfterTest
    public void cleanGetTest() throws Exception {
	Utils.cleanUp(swagger_url);
    }
}