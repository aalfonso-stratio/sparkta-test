package com.stratio.tests.fragments;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.stratio.tests.utils.Utils;

public class Delete {
    Properties defaultProps;
    String swagger_url;
    
    @BeforeTest
    public void prepareDeleteTest() throws Exception {
	// Read properties file
	Properties swaggerProps = new Properties();
	swaggerProps.load(new FileInputStream("swagger.properties"));
	swagger_url = swaggerProps.getProperty("swagger_url");
	defaultProps = new Properties();
	defaultProps.load(new FileInputStream("fragments.properties"));
	
	// Clean everything
	Utils.cleanUp(swagger_url);
    }
    
    @Test(description = "Delete a fragment with empty type")
    public void delete01() throws Exception {
	String url = swagger_url + "/fragment/" + "" + "/" + "name";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("delete01 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("delete01 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("delete01 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 405);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Method Not Allowed");
	Assert.assertEquals(responseBody, "HTTP method not allowed, supported methods: GET");
    }
    
    @Test(description = "Delete a fragment with empty name")
    public void delete02() throws Exception {
	String url = swagger_url + "/fragment/" + "input" + "/" + "";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("delete02 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("delete02 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("delete02 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 405);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Method Not Allowed");
	Assert.assertEquals(responseBody, "HTTP method not allowed, supported methods: GET");
    }
    
    @Test(description = "Delete a fragment with empty type and name")
    public void delete03() throws Exception {
	String url = swagger_url + "/fragment/" + "" + "/" + "";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("delete03 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("delete03 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("delete03 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 405);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Method Not Allowed");
	Assert.assertEquals(responseBody, "HTTP method not allowed, supported methods: GET");
    }
    
    @Test(description = "Delete a fragment with invalid type")
    public void delete04() throws Exception {
	String url = swagger_url + "/fragment/" + "invalid" + "/" + "name";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("delete04 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("delete04 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("delete04 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");

	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "The fragment type must be input|output");
    }
    
    @Test(description = "Delete a fragment with non-existing name and empty list")
    public void delete05() throws Exception {
	String url = swagger_url + "/fragment/" + "input" + "/" + "name";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("delete05 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("delete05 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("delete05 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Delete a fragment with non-existing name")
    public void delete06() throws Exception {
	// Add input fragment
	String validInputFragment = defaultProps.getProperty("validInputFragment");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, validInputFragment);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");

	// Delete non-existing fragment
	String url2 = swagger_url + "/fragment/" + "input" + "/" + "name";
	HttpResponse response2 = Utils.sendDeleteRequest(url2);
	String responseBody2 = Utils.getResponseBody(response2);
		
	System.out.println("delete06 Delete Response Code: " + response2.getStatusLine().getStatusCode());
	System.out.println("delete06 Delete Response Message: " + response2.getStatusLine().getReasonPhrase());
	System.out.println("delete06 Delete Response Body: " + responseBody2);
	
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Delete an input fragment")
    public void delete07() throws Exception {
	// It has been added by previous test case
	// Delete input fragment
	String validInputFragmentName = defaultProps.getProperty("validInputFragmentName");
	
	String url2 = swagger_url + "/fragment/" + "input" + "/" + validInputFragmentName;
	HttpResponse response2 = Utils.sendDeleteRequest(url2);
	String responseBody2 = Utils.getResponseBody(response2);
		
	System.out.println("delete07 Delete Response Code: " + response2.getStatusLine().getStatusCode());
	System.out.println("delete07 Delete Response Message: " + response2.getStatusLine().getReasonPhrase());
	System.out.println("delete07 Delete Response Body: " + responseBody2);
	
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "OK");
	
	// Check no input fragments available
	String url3 = swagger_url + "/fragment/" + "input";
	HttpResponse response3 = Utils.sendGetRequest(url3);
	String responseBody3 = Utils.getResponseBody(response3);
	
	JSONArray bodyJson  = new JSONArray(responseBody3);
	Assert.assertEquals(bodyJson.length(), 0);
	
    }
    
    @Test(description = "Delete an output fragment")
    public void delete08() throws Exception {
	// Add output fragment
	String validOutputFragment = defaultProps.getProperty("validOutputFragment");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, validOutputFragment);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
	
	// Delete output fragment
	String validOutputFragmentName = defaultProps.getProperty("validOutputFragmentName");
	
	String url2 = swagger_url + "/fragment/" + "output" + "/" + validOutputFragmentName;
	HttpResponse response2 = Utils.sendDeleteRequest(url2);
	String responseBody2 = Utils.getResponseBody(response2);
		
	System.out.println("delete08 Delete Response Code: " + response2.getStatusLine().getStatusCode());
	System.out.println("delete08 Delete Response Message: " + response2.getStatusLine().getReasonPhrase());
	System.out.println("delete08 Delete Response Body: " + responseBody2);
	
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "OK");
	
	// Check no output fragments available
	String url3 = swagger_url + "/fragment/" + "output";
	HttpResponse response3 = Utils.sendGetRequest(url3);
	String responseBody3 = Utils.getResponseBody(response3);
		
	JSONArray bodyJson  = new JSONArray(responseBody3);
	Assert.assertEquals(bodyJson.length(), 0);
    }
    
    @Test(description = "Delete input fragment referenced by policy")
    public void delete09() throws Exception {
	// Add fragment
	String validInputFragment = defaultProps.getProperty("validInputFragment");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, validInputFragment);
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
	
	System.out.println("delete09: Fragment created");
	
	// Add policy
	String policyInputFragment = defaultProps.getProperty("policyInputFragment");
	String url2 = swagger_url + "/policy";
	HttpResponse response2 = Utils.sendPostRequest(url2, policyInputFragment);
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "Created");
	
	System.out.println("delete09: Policy created");
	
	// Delete fragment
	String validInputFragmentName = defaultProps.getProperty("validInputFragmentName");
	
	String url3 = swagger_url + "/fragment/" + "input" + "/" + validInputFragmentName;
	HttpResponse response3 = Utils.sendDeleteRequest(url3);
	String responseBody3 = Utils.getResponseBody(response3);
		
	System.out.println("delete09 Response Code: " + response3.getStatusLine().getStatusCode());
	System.out.println("delete09 Response Message: " + response3.getStatusLine().getReasonPhrase());
	System.out.println("delete09 Response Body: " + responseBody3);
	
	Assert.assertEquals(response3.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response3.getStatusLine().getReasonPhrase(), "OK");
	
	// Check no policies available
	String url4 = swagger_url + "/policy/all";
	HttpResponse response4 = Utils.sendGetRequest(url4);
	String responseBody4 = Utils.getResponseBody(response4);
	
	Assert.assertEquals(response4.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response4.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody4, "[]");
    }
    
    @Test(description = "Delete output fragment referenced by policy")
    public void delete10() throws Exception {
	// Add fragment
	String validOutputFragment = defaultProps.getProperty("validOutputFragment");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, validOutputFragment);
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
	
	System.out.println("delete10: Fragment created");
	
	// Add policy
	String policyOutputFragment = defaultProps.getProperty("policyOutputFragment");
	String url2 = swagger_url + "/policy";
	HttpResponse response2 = Utils.sendPostRequest(url2, policyOutputFragment);
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "Created");
	
	System.out.println("delete10: Policy created");
	
	// Delete fragment
	String validOutputFragmentName = defaultProps.getProperty("validOutputFragmentName");
	
	String url3 = swagger_url + "/fragment/" + "output" + "/" + validOutputFragmentName;
	HttpResponse response3 = Utils.sendDeleteRequest(url3);
	String responseBody3 = Utils.getResponseBody(response3);
		
	System.out.println("delete10 Response Code: " + response3.getStatusLine().getStatusCode());
	System.out.println("delete10 Response Message: " + response3.getStatusLine().getReasonPhrase());
	System.out.println("delete10 Response Body: " + responseBody3);
	
	Assert.assertEquals(response3.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response3.getStatusLine().getReasonPhrase(), "OK");
	
	// Check no policies available
	String url4 = swagger_url + "/policy/all";
	HttpResponse response4 = Utils.sendGetRequest(url4);
	String responseBody4 = Utils.getResponseBody(response4);
	
	Assert.assertEquals(response4.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response4.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody4, "[]");
    }
    
    @AfterTest
    public void cleanDeleteTest() throws Exception {
	Utils.cleanUp(swagger_url);
    }   
    
}
