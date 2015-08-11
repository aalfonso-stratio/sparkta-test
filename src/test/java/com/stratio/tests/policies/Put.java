package com.stratio.tests.policies;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.stratio.tests.utils.Utils;

public class Put {
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
    
    @Test(description = "Update a policy using empty parameter")
    public void put01() throws Exception{
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPutRequest(url, "");
	String responseBody = Utils.getResponseBody(response);	
	
	System.out.println("put01 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("put01 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertEquals(responseBody, "Request entity expected but not supplied" );
    }
    
    @Test(description = "Update a policy when no policies available")
    public void put02() throws Exception{
	String policyExample = defaultProps.getProperty("policyExample");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPutRequest(url, policyExample);
	
	System.out.println("put02 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("put02 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Update a non-existing policy")
    public void put03() throws Exception{
	String policyExample2 = defaultProps.getProperty("policyExample2");
	System.out.println("policy: " + policyExample2);
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPutRequest(url, policyExample2);
	
	System.out.println("put03 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("put03 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
  
    @Test(description = "Update a existing policy with invalid info: no input")
    public void put04() throws Exception{
	// Add policy
	String policyExample = defaultProps.getProperty("policyExample");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyExample);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
	
	// Update policy
	String policyNoInput = defaultProps.getProperty("policyNoInput");
	
	String url2 = swagger_url + "/policy";
	HttpResponse response2 = Utils.sendPutRequest(url2, policyNoInput);
	String responseBody2 = Utils.getResponseBody(response2);
	
	System.out.println("put04 Response Code: " + response2.getStatusLine().getStatusCode());
	System.out.println("put04 Response Message: " + response2.getStatusLine().getReasonPhrase());
	System.out.println("put04 Response Body: " + responseBody2);
	
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody2);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "It is mandatory to define one input in the policy.");
    }
    
    @Test(description = "Update a existing policy with invalid info: no outputs")
    public void put05() throws Exception{
	String policyNoOutputs = defaultProps.getProperty("policyNoOutputs");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPutRequest(url, policyNoOutputs);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("put05 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("put05 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("put05 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "It is mandatory to define at least one output in the policy.");
    }
    
    @Test(description = "Update a existing policy with invalid info: no cubes")
    public void put06() throws Exception{
	String policyNoCubes = defaultProps.getProperty("policyNoCubes");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPutRequest(url, policyNoCubes);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("put06 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("put06 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("put06 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "It is mandatory to define one cube in the policy.");
    }
    
    @Test(description = "Update a existing policy with invalid info: one input and one input fragment")
    public void put07() throws Exception{
	String policyOneInputOneFragment = defaultProps.getProperty("policyOneInputOneFragment");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPutRequest(url, policyOneInputOneFragment);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("put07 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("put07 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("put07 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "Only one input is allowed in the policy.");
    }
    
    @Test(description = "Update a existing policy")
    public void put08() throws Exception{
	String policyExampleUpdated = defaultProps.getProperty("policyExampleUpdated");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPutRequest(url, policyExampleUpdated);
	
	System.out.println("put08 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("put08 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
    }
    
    @AfterTest
    public void cleanGetTest() throws Exception {
	Utils.cleanUp(swagger_url);
    }
}