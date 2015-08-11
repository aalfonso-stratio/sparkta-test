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

public class PolicyContexts {
    String swagger_url;
    Properties defaultProps;
    
    @BeforeSuite
    public void preparePolicyContextsTest() throws Exception {
	// Read properties file
	Properties swaggerProps = new Properties();
	swaggerProps.load(new FileInputStream("swagger.properties"));
	swagger_url = swaggerProps.getProperty("swagger_url");
	defaultProps = new Properties();
	defaultProps.load(new FileInputStream("policyContexts.properties"));
	
	// Make sure everything is clean
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
    
    @Test(description = "Get all policy contexts when none available")
    public void policyContexts01() throws Exception {
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts01 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts01 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts01 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	// TIMING OUT
	//Assert.assertEquals(responseBody, "[]");
    }
    
    @Test(description = "Get policy context using empty parameter")
    public void policyContexts02() throws Exception {
	String url = swagger_url + "/policyContext/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts02 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts02 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts02 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");
    }
    
    @Test(description = "Get policy context when none available")
    public void policyContexts03() throws Exception {
	String url = swagger_url + "/policyContext/" + "name";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts03 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts03 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts03 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	// TIMING OUT
	//Assert.assertEquals(responseBody, "The requested resource could not be found.");
    }
    
    @Test(description = "Add a policy context using empty parameter")
    public void policyContexts04() throws Exception {
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, "");
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts04 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts04 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts04 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertEquals(responseBody, "Request entity expected but not supplied");
    }
    
    @Test(description = "Create policy context using policy with no name")
    public void policyContexts05() throws Exception {
	String policyNoName = defaultProps.getProperty("policyNoName");	
	
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, policyNoName);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts05 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts05 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts05 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertTrue(responseBody.contains("No usable value for name"));
    }
    
    @Test(description = "Create policy context using policy with no input")
    public void policyContexts06() throws Exception {
	String policyNoInput = defaultProps.getProperty("policyNoInput");	
	
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, policyNoInput);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts06 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts06 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts06 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "It is mandatory to define one input in the policy.");
    }
    
    @Test(description = "Create policy context using policy with no outputs")
    public void policyContexts07() throws Exception {
	String policyNoOutputs = defaultProps.getProperty("policyNoOutputs");	
	
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, policyNoOutputs);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts07 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts07 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts07 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "It is mandatory to define at least one output in the policy.");
    }
    
    @Test(description = "Create policy context using policy with no cubes")
    public void policyContexts08() throws Exception {
	String policyNoCubes = defaultProps.getProperty("policyNoCubes");	
	
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, policyNoCubes);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts08 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts08 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts08 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("error: array is too short: must have at least 1 elements but instance has 0 elements"));
    }
    
    @Test(description = "Delete policy context using empty parameter")
    public void policyContexts09() throws Exception {
	
	// PROBLEM: MISLEADING ERROR MESSAGE 405
	
	String url = swagger_url + "/policyContext/" + "";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("policyContexts09 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts09 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts09 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertEquals(responseBody, "Request entity expected but not supplied");
    }
    
    @Test(description = "Delete non-existing policy context when none available")
    public void policyContexts10() throws Exception {
	String url = swagger_url + "/policyContext/" + "name";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("policyContexts10 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts10 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts10 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	// TIMING OUT
	//Assert.assertEquals(responseBody, "Request entity expected but not supplied");
    }
    
    @Test(description = "Add policy context")
    public void policyContexts11() throws Exception {
	String policyExample = defaultProps.getProperty("policyExample");	
	
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, policyExample);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts11 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts11 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts11 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
    }
    
    @Test(description = "Get all policy contexts")
    public void policyContexts12() throws Exception {
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts12 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts12 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts12 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	JSONArray bodyJson  = new JSONArray(responseBody);
	Assert.assertEquals(bodyJson.length(), 1);
	
	// TIMING OUT
    }
    
    @Test(description = "Get non-existing policy context")
    public void policyContexts13() throws Exception {
	String url = swagger_url + "/policyContext/" + "name";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts13 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts13 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts13 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	// TIMING OUT
    }
    
    @Test(description = "Get policy context")
    public void policyContexts14() throws Exception {
	String policyExampleName = defaultProps.getProperty("policyExampleName");
	
	String url = swagger_url + "/policyContext/" + policyExampleName;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts14 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts14 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts14 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	// TIMING OUT
    }
    
    @Test(description = "Delete non-existing policy context")
    public void policyContexts15() throws Exception {
	String url = swagger_url + "/policyContext/" + "name";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("policyContexts15 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts15 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts15 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	// TIMING OUT
    }
    
    @Test(description = "Delete policy context")
    public void policyContexts16() throws Exception {
	String policyExampleName = defaultProps.getProperty("policyExampleName");
	
	String url = swagger_url + "/policyContext/" + policyExampleName;
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("policyContexts16 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts16 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts16 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	// TIMING OUT
    }
    
    @Test(description = "Add a policy with non-existing fragment")
    public void policyContexts17() throws Exception {
	String policyExampleNonExistingFragment = defaultProps.getProperty("policyExampleNonExistingFragment");
	
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, policyExampleNonExistingFragment);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts17: " + response.getStatusLine());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "KeeperErrorCode = NoNode for /stratio/sparkta/fragments/input/myFragment");
    }
    
    @Test(description = "Add a policy with 2 existing input fragments")
    public void policyContexts18() throws Exception {
	// It is possible to add a policy with two inputs	
	String policyExampleTwoFragments = defaultProps.getProperty("policyExampleTwoFragments");
	
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, policyExampleTwoFragments);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts18 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts18 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts18 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "Only one input is allowed in the policy.");
    }
    
    @Test(description = "Add a policy with existing fragment")
    public void policyContexts19() throws Exception {
	String policyExampleOneFragment = defaultProps.getProperty("policyExampleOneFragment");
	String policyExampleOneFragmentName = defaultProps.getProperty("policyExampleOneFragmentName");
		
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, policyExampleOneFragment);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts19: " + response.getStatusLine());
	System.out.println("policyContexts19 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts19 Response Body: " + responseBody);
		
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "Creating new context with name " + policyExampleOneFragmentName);
    }
    
    @Test(description = "Add a policy with 2 existing output fragments")
    public void policyContexts20() throws Exception {
	String policyExampleTwoOutputFragments = defaultProps.getProperty("policyExampleTwoOutputFragments");
	String policyTwoOutputFragmentsName = defaultProps.getProperty("policyTwoOutputFragmentsName");
	
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, policyExampleTwoOutputFragments);
	String responseBody = Utils.getResponseBody(response);

	System.out.println("policyContexts20: " + response.getStatusLine());
	System.out.println("policyContexts20 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts20 Response Body: " + responseBody);
		
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "Creating new context with name " + policyTwoOutputFragmentsName);
    }
    
    @Test(description = "Add a policy with input and one input fragment")
    public void policyContexts21() throws Exception {
	String policyOneInputOneFragment = defaultProps.getProperty("policyOneInputOneFragment");
		
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, policyOneInputOneFragment);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts21 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts21 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts21 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "Only one input is allowed in the policy.");
    }
    
    @AfterSuite
    public void cleanPoliciesTest() throws Exception {
	Utils.cleanUp(swagger_url);
    }    
}