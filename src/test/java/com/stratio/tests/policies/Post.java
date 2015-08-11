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

public class Post {
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
		
	String fragmentExample = defaultProps.getProperty("fragmentExample");
	String fragmentExample2 = defaultProps.getProperty("fragmentExample2");
			
	// Create fragment
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, fragmentExample);
	        
	System.out.println("preparePoliciesTest: " + response.getStatusLine());
	        
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
		
	// Create second fragment
	HttpResponse response2 = Utils.sendPostRequest(url, fragmentExample2);
	        
	System.out.println("preparePoliciesTest: " + response2.getStatusLine());
	        
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "Created");
		
	// Create output fragments
	String fragmentOutputExample = defaultProps.getProperty("fragmentOutputExample");
	String fragmentOutputExample2 = defaultProps.getProperty("fragmentOutputExample2");
		
	String url3 = swagger_url + "/fragment";
	HttpResponse response3 = Utils.sendPostRequest(url3, fragmentOutputExample);
	        
	System.out.println("preparePoliciesTest: " + response3.getStatusLine());
	        
	Assert.assertEquals(response3.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response3.getStatusLine().getReasonPhrase(), "Created");
		
	String url4 = swagger_url + "/fragment";
	HttpResponse response4 = Utils.sendPostRequest(url4, fragmentOutputExample2);
	        
	System.out.println("preparePoliciesTest: " + response4.getStatusLine());
	        
	Assert.assertEquals(response4.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response4.getStatusLine().getReasonPhrase(), "Created");
    }
    
    @Test(description = "Add a policy with empty data")
    public void post01() throws Exception {
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, "");
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post01 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post01 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post01 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertEquals(responseBody, "Request entity expected but not supplied");
    }
    
    @Test(description = "Add a policy with missing name")
    public void post02() throws Exception {
	// This test will fail (policy should be checked)
	// If no name, policy is created with name = "default" **WRONG**
	String incorrectPolicyExample = defaultProps.getProperty("incorrectPolicyExample");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, incorrectPolicyExample);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post02 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post02 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post02 Response Body: " + responseBody);
	
	// BEGIN: Need to delete policy incorrectly created
	String urlDelete = swagger_url + "/policy/default";
	HttpResponse responseDelete = Utils.sendDeleteRequest(urlDelete);
	String responseBodyDelete = Utils.getResponseBody(responseDelete);
		
	System.out.println("post02 Delete Response Code: " + responseDelete.getStatusLine().getStatusCode());
	System.out.println("post02 Delete Response Message: " + responseDelete.getStatusLine().getReasonPhrase());
	System.out.println("post02 Delete Response Body: " + responseBodyDelete);
	// END: Need to delete policy incorrectly created
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
    }
    
    @Test(description = "Add a valid policy")
    public void post03() throws Exception {
	String policyExample = defaultProps.getProperty("policyExample");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyExample);
	
	System.out.println("post03: " + response.getStatusLine());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
    }
    
    @Test(description = "Add the same valid policy")
    public void post04() throws Exception {
	String policyExample = defaultProps.getProperty("policyExample");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyExample);
	
	System.out.println("post04: " + response.getStatusLine());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Add a policy with non-existing fragment")
    public void post05() throws Exception {
	String policyExampleNonExistingFragment = defaultProps.getProperty("policyExampleNonExistingFragment");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyExampleNonExistingFragment);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post05: " + response.getStatusLine());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "KeeperErrorCode = NoNode for /stratio/sparkta/fragments/input/myFragment");
    }
    
    @Test(description = "Add a policy with 2 existing input fragments")
    public void post06() throws Exception {
	// It is possible to add a policy with two inputs	
	String policyExampleTwoFragments = defaultProps.getProperty("policyExampleTwoFragments");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyExampleTwoFragments);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post06 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post06 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post06 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "Only one input is allowed in the policy.");
    }

    @Test(description = "Add a policy with existing fragment")
    public void post07() throws Exception {
	String policyExampleOneFragment = defaultProps.getProperty("policyExampleOneFragment");
		
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyExampleOneFragment);

	System.out.println("post07: " + response.getStatusLine());
		
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
    }
    
    @Test(description = "Add a policy with 2 existing output fragments")
    public void post08() throws Exception {
	String policyExampleTwoOutputFragments = defaultProps.getProperty("policyExampleTwoOutputFragments");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyExampleTwoOutputFragments);

	System.out.println("post08: " + response.getStatusLine());
		
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
    }
    
    @Test(description = "Add a policy with input and one input fragment")
    public void post09() throws Exception {
	String policyOneInputOneFragment = defaultProps.getProperty("policyOneInputOneFragment");
		
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyOneInputOneFragment);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post09 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post09 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post09 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "Only one input is allowed in the policy.");
    }
    
    @Test(description = "Add a policy with missing input")
    public void post10() throws Exception {
	String policyNoInput = defaultProps.getProperty("policyNoInput");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyNoInput);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post10 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post10 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post10 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "It is mandatory to define one input in the policy.");
    }
    
    @Test(description = "Add a policy with missing outputs")
    public void post11() throws Exception {
	String policyNoOutputs = defaultProps.getProperty("policyNoOutputs");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyNoOutputs);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post11 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post11 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post11 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "It is mandatory to define at least one output in the policy.");
    }
    
    @Test(description = "Add a policy with missing cubes")
    public void post12() throws Exception {
	// It should not be possible to add a policy with no cubes defined
	// This test will fail, as at the moment there is no validation
	String policyNoCubes = defaultProps.getProperty("policyNoCubes");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyNoCubes);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("post12 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post12 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post12 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");

	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "It is mandatory to define one cube in the policy.");
    }
    
    @AfterTest
    public void cleanGetTest() throws Exception {
	Utils.cleanUp(swagger_url);
    }
}