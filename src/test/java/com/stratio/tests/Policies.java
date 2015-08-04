package com.stratio.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.stratio.tests.utils.Utils;

public class Policies {
    Properties defaultProps;
    
    @BeforeSuite
    public void preparePoliciesTest() throws Exception {
	// Read properties file
	defaultProps = new Properties();
	defaultProps.load(new FileInputStream("tests.properties"));
	
	String fragmentExample = defaultProps.getProperty("fragmentExample");
	String fragmentExample2 = defaultProps.getProperty("fragmentExample2");
	
	// Create fragment
	String url = "http://localhost:9090/fragment";
	HttpResponse response = Utils.sendPostRequest(url, fragmentExample);
        
        System.out.println("preparePoliciesTest: " + response.getStatusLine());
        
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
	
	// Create second fragment
	HttpResponse response2 = Utils.sendPostRequest(url, fragmentExample2);
        
        System.out.println("preparePoliciesTest: " + response2.getStatusLine());
        
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "Created");	
    }

    @Test(description = "Get all policies when no policies available")
    public void policies01() throws Exception {
	String url = "http://localhost:9090/policy/all";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policies01 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies01 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies01 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "[]");	
    }
    
    @Test(description = "Get a policy when no policies available")
    public void policies02() throws Exception {
	String nonExistingPolicy = defaultProps.getProperty("nonExistingPolicy");
	
	String url = "http://localhost:9090/policy/find/" + nonExistingPolicy;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policies02 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies02 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies02 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Get all policies with a particular fragment when no policies available")
    public void policies03() throws Exception {
	// We need to add a fragment
	String fragmentType = defaultProps.getProperty("fragmentType");
	String fragmentName = defaultProps.getProperty("fragmentName");
	
	String url = "http://localhost:9090/policy/fragment/" + fragmentType + "/" + fragmentName;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);	
	
	System.out.println("policies03 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies03 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies03 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "[]");
    }
    
    @Test(description = "Update a policy using empty parameter")
    public void policies04() throws Exception{
	String url = "http://localhost:9090/policy";
	HttpResponse response = Utils.sendPutRequest(url, "");
	
	System.out.println("policies04 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies04 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Request entity expected but not supplied");
    }
    
    @Test(description = "Update a policy when no policies available")
    public void policies05() throws Exception{
	String policyExample = defaultProps.getProperty("policyExample");
	
	String url = "http://localhost:9090/policy";
	HttpResponse response = Utils.sendPutRequest(url, policyExample);
	
	System.out.println("policies05 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies05 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Run a policy when no policies available")
    public void policies06() throws Exception {
	String nonExistingPolicy = defaultProps.getProperty("nonExistingPolicy");
	
	String url = "http://localhost:9090/policy/run/" + nonExistingPolicy;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policies06 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies06 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies06 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Delete a policy when no policies available")
    public void policies07() throws Exception {
	String nonExistingPolicy = defaultProps.getProperty("nonExistingPolicy");
	
	String url = "http://localhost:9090/policy/run/" + nonExistingPolicy;
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("policies07 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies07 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies07 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Add a policy with empty data")
    public void policies08() throws Exception {
	String url = "http://localhost:9090/policy";
	HttpResponse response = Utils.sendPostRequest(url, "");
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policies08 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies08 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies08 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertEquals(responseBody, "Request entity expected but not supplied");
    }
    
    @Test(description = "Add a policy with missing information")
    public void policies09() throws Exception {
	// This test will fail (policy should be checked)
	// If no name, policy is created with name = "default" **WRONG**
	// If no inputs, policy is created with inputs = {} **WRONG**
	String incorrectPolicyExample = defaultProps.getProperty("incorrectPolicyExample");
	
	String url = "http://localhost:9090/policy";
	HttpResponse response = Utils.sendPostRequest(url, incorrectPolicyExample);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policies09 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies09 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies09 Response Body: " + responseBody);
	
	// BEGIN: Need to delete policy incorrectly created
	String urlDelete = "http://localhost:9090/policy/default";
	HttpResponse responseDelete = Utils.sendDeleteRequest(urlDelete);
	String responseBodyDelete = Utils.getResponseBody(responseDelete);
		
	System.out.println("policies09 Delete Response Code: " + responseDelete.getStatusLine().getStatusCode());
	System.out.println("policies09 Delete Response Message: " + responseDelete.getStatusLine().getReasonPhrase());
	System.out.println("policies09 Delete Response Body: " + responseBodyDelete);
	// END: Need to delete policy incorrectly created
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
    }
    
    @Test(description = "Add a valid policy")
    public void policies10() throws Exception {
	String policyExample = defaultProps.getProperty("policyExample");
	
	String url = "http://localhost:9090/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyExample);
	
	System.out.println("policies10: " + response.getStatusLine());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
    }
    
    @Test(description = "Add the same valid policy")
    public void policies11() throws Exception {
	String policyExample = defaultProps.getProperty("policyExample");
	
	String url = "http://localhost:9090/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyExample);
	
	System.out.println("policies11: " + response.getStatusLine());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }

    @Test(description = "Get a non-exisiting policy")
    public void policies12() throws Exception {
	String nonExistingPolicy = defaultProps.getProperty("nonExistingPolicy");
	
	String url = "http://localhost:9090/policy/find/" + nonExistingPolicy;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("policies12 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies12 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies12 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");	
    }
    
    @Test(description = "Get a existing policy")
    public void policies13() throws Exception {
	String existingPolicy = defaultProps.getProperty("existingPolicy");
	String policyExampleReturned = defaultProps.getProperty("policyExampleReturned");
	
	String url = "http://localhost:9090/policy/find/" + existingPolicy;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("policies13 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies13 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies13 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, policyExampleReturned);
    }
    
    @Test(description = "Update a non-existing policy")
    public void policies14() throws Exception{
	String policyExample2 = defaultProps.getProperty("policyExample2");
	System.out.println("policy: " + policyExample2);
	
	String url = "http://localhost:9090/policy";
	HttpResponse response = Utils.sendPutRequest(url, policyExample2);
	
	System.out.println("policies14 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies14 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
    }
  
    @Test(description = "Update a existing policy")
    public void policies15() throws Exception{
	String policyExampleUpdated = defaultProps.getProperty("policyExampleUpdated");
	System.out.println("policy: " + policyExampleUpdated);
	
	String url = "http://localhost:9090/policy";
	HttpResponse response = Utils.sendPutRequest(url, policyExampleUpdated);
	
	System.out.println("policies15 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies15 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
    }
    
    @Test(description = "Run a non-existing policy")
    public void policies16() throws Exception {
	String nonExistingPolicy = defaultProps.getProperty("nonExistingPolicy");
		
	String url = "http://localhost:9090/policy/run/" + nonExistingPolicy;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policies16 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies16 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies16 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
//    @Test(description = "Run an empty policy")
//    public void policiesXX() throws Exception {
//	String url = "http://localhost:9090/policy/run/" + "";
//	HttpResponse response = Utils.sendGetRequest(url);
//	String responseBody = Utils.getResponseBody(response);
//	
//	System.out.println("policiesXX Response Code: " + response.getStatusLine().getStatusCode());
//	System.out.println("policiesXX Response Message: " + response.getStatusLine().getReasonPhrase());
//	System.out.println("policiesXX Response Body: " + responseBody);
//	
//	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
//	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
//    }
    
    @Test(description = "Run a existing policy")
    public void policies17() throws Exception {
	String existingPolicy = defaultProps.getProperty("existingPolicy");
	
	String url = "http://localhost:9090/policy/run/" + existingPolicy;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policies17 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies17 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies17 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "{\"message\":\"Creating new context with name " + existingPolicy + "\"}");
	
	Thread.sleep(10000);
	
	// Check there is a new policy context
	String urlContext = "http://localhost:9090/policyContext";
	HttpResponse responseContext = Utils.sendGetRequest(urlContext);
	String responseBodyContext = Utils.getResponseBody(responseContext);
	
	System.out.println("policies17 Context Response Code: " + responseContext.getStatusLine().getStatusCode());
	System.out.println("policies17 Context Response Message: " + responseContext.getStatusLine().getReasonPhrase());
	System.out.println("policies17 Context Response Body: " + responseBodyContext);
	
	Assert.assertEquals(responseContext.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(responseContext.getStatusLine().getReasonPhrase(), "OK");
	
	JSONArray bodyJson  = new JSONArray(responseBodyContext);
	Assert.assertEquals(bodyJson.length(), 1);
	if (bodyJson.length() == 1) {
	    String context = bodyJson.get(0).toString();
	    JSONObject jsonContext = new JSONObject(context);
	    Assert.assertEquals(jsonContext.get("status"),"Initialized");
	}
    }
    
//    @Test(description = "Run a existing policy already running")
//    public void policiesXX() throws Exception {
//	String existingPolicy = defaultProps.getProperty("existingPolicy");
//	
//	String url = "http://localhost:9090/policy/run/" + existingPolicy;
//	HttpResponse response = Utils.sendGetRequest(url);
//	String responseBody = Utils.getResponseBody(response);
//	
//	System.out.println("policiesXX Response Code: " + response.getStatusLine().getStatusCode());
//	System.out.println("policiesXX Response Message: " + response.getStatusLine().getReasonPhrase());
//	System.out.println("policiesXX Response Body: " + responseBody);
//	
//	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
//	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
//	Assert.assertEquals(responseBody, "Creating new context with name " + existingPolicy);
//    }
    
    @Test(description = "Delete a non-existing policy")
    public void policies18() throws Exception {
	String nonExistingPolicy = defaultProps.getProperty("nonExistingPolicy");
	
	String url = "http://localhost:9090/policy/" + nonExistingPolicy;
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policies18 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies18 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies18 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Delete a existing policy")
    public void policies19() throws Exception {
	String existingPolicy = defaultProps.getProperty("existingPolicy");
	
	String url = "http://localhost:9090/policy/" + existingPolicy;
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policies19 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies19 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies19 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
    }

    @Test(description = "Get all policies with fragment with incorrect fragment type")
    public void policies20() throws Exception {
	String fragmentTypeIncorrect = defaultProps.getProperty("fragmentTypeIncorrect");
	String fragmentName = defaultProps.getProperty("fragmentName");
	
	// Add fragment name
	String url = "http://localhost:9090/policy/fragment/" + fragmentTypeIncorrect  + "/" + fragmentName;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policies20 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies20 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies20 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "[]");
    }
    
    @Test(description = "Get all policies with non-existing fragment")
    public void policies21() throws Exception {
	String nonExistingFragment = defaultProps.getProperty("nonExistingFragment");
	
	String url = "http://localhost:9090/policy/fragment/input/" + nonExistingFragment;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("policies21 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies21 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies21 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "[]");
    }

    @Test(description = "Add a policy with non-existing fragment")
    public void policies22() throws Exception {
	String policyExampleNonExistingFragment = defaultProps.getProperty("policyExampleNonExistingFragment");
	
	String url = "http://localhost:9090/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyExampleNonExistingFragment);
	String responseBody = Utils.getResponseBody(response);
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	
	System.out.println("policies22: " + response.getStatusLine());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(message, "KeeperErrorCode = NoNode for /stratio/sparkta/fragments/input/myFragment");
    }
    
    @Test(description = "Add a policy with 2 existing fragments")
    public void policies23() throws Exception {
	// It is possible to add a policy with two inputs	
	String policyExampleTwoFragments = defaultProps.getProperty("policyExampleTwoFragments");
	
	String url = "http://localhost:9090/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyExampleTwoFragments);

	System.out.println("policies23: " + response.getStatusLine());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
    }
    
    @Test(description = "Run a policy with 2 existing fragments")
    public void policies24() throws Exception {
	// It is not possible to run a policy with 2 input fragments	
	String policyTwoFragmentsName = defaultProps.getProperty("policyTwoFragmentsName");
	
	String url = "http://localhost:9090/policy/run/" + policyTwoFragmentsName;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policies24 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policies24 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policies24 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("error: array must not contain duplicate elements"));
    }
    
    @Test(description = "Add a policy with existing fragment")
    public void policies25() {
	Assert.assertTrue(true);
    }
    
    @Test(description = "Get all policies with existing fragment")
    public void policies26() {
	Assert.assertTrue(true);
    }
 
    @Test(description = "Get all policies with policies available")
    public void policies27() {
	Assert.assertTrue(true);
    }
    
    @Test(description = "Delete a existing policy with fragments")
    public void policies28() {
	Assert.assertTrue(true);
    }
    
    @AfterSuite
    public void cleanPoliciesTest() throws Exception {
	String fragmentType = defaultProps.getProperty("fragmentType");
	String fragmentName = defaultProps.getProperty("fragmentName");
	String fragmentName2 = defaultProps.getProperty("fragmentName2");
	
	// Remove fragment
	String url = "http://localhost:9090/fragment/" + fragmentType + "/" + fragmentName;
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("cleanPoliciesTest Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("cleanPoliciesTest Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("cleanPoliciesTest Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	String url2 = "http://localhost:9090/fragment/" + fragmentType + "/" + fragmentName2;
	HttpResponse response2 = Utils.sendDeleteRequest(url2);
	String responseBody2 = Utils.getResponseBody(response2);
	
	System.out.println("cleanPoliciesTest Response Code: " + response2.getStatusLine().getStatusCode());
	System.out.println("cleanPoliciesTest Response Message: " + response2.getStatusLine().getReasonPhrase());
	System.out.println("cleanPoliciesTest Response Body: " + responseBody2);
	
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "OK");
	
	// Remove policies
	
    }
}