package com.stratio.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.stratio.tests.utils.Utils;

public class PolicyContexts {
    String swagger_url;
    Properties defaultProps;
    
    @BeforeSuite
    public void preparePoliciesTest() throws Exception {
	// Read properties file
	Properties swaggerProps = new Properties();
	swaggerProps.load(new FileInputStream("swagger.properties"));
	swagger_url = swaggerProps.getProperty("swagger_url");
	defaultProps = new Properties();
	defaultProps.load(new FileInputStream("policyContexts.properties"));
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
    
    @Test(description = "Create policy context using invalid policy")
    public void policyContexts05() throws Exception {
	String incorrectPolicyExample = defaultProps.getProperty("incorrectPolicyExample");	
	
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, incorrectPolicyExample);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts05 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts05 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts05 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("No usable value for name"));
    }
    
    @Test(description = "Delete policy context using empty parameter")
    public void policyContexts06() throws Exception {
	
	// PROBLEM: MISLEADING ERROR MESSAGE 405
	
	String url = swagger_url + "/policyContext/" + "";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("policyContexts06 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts06 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts06 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertEquals(responseBody, "Request entity expected but not supplied");
    }
    
    @Test(description = "Delete non-existing policy context when none available")
    public void policyContexts07() throws Exception {
	String url = swagger_url + "/policyContext/" + "name";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("policyContexts07 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts07 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts07 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	// TIMING OUT
	//Assert.assertEquals(responseBody, "Request entity expected but not supplied");
    }
    
    @Test(description = "Add policy context")
    public void policyContexts08() throws Exception {
	String policyExample = defaultProps.getProperty("policyExample");	
	
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, policyExample);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts08 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts08 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts08 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
    }
    
    @Test(description = "Get all policy contexts")
    public void policyContexts09() throws Exception {
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts09 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts09 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts09 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	JSONArray bodyJson  = new JSONArray(responseBody);
	Assert.assertEquals(bodyJson.length(), 1);
	
	// TIMING OUT
    }
    
    @Test(description = "Get non-existing policy context")
    public void policyContexts10() throws Exception {
	String url = swagger_url + "/policyContext/" + "name";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts10 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts10 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts10 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	// TIMING OUT
    }
    
    @Test(description = "Get policy context")
    public void policyContexts11() throws Exception {
	String policyExampleName = defaultProps.getProperty("policyExampleName");
	
	String url = swagger_url + "/policyContext/" + policyExampleName;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("policyContexts11 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts11 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts11 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	// TIMING OUT
    }
    
    @Test(description = "Delete non-existing policy context")
    public void policyContexts12() throws Exception {
	String url = swagger_url + "/policyContext/" + "name";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("policyContexts12 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts12 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts12 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	// TIMING OUT
    }
    
    @Test(description = "Delete policy context")
    public void policyContexts13() throws Exception {
	String policyExampleName = defaultProps.getProperty("policyExampleName");
	
	String url = swagger_url + "/policyContext/" + policyExampleName;
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("policyContexts13 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("policyContexts13 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("policyContexts13 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	// TIMING OUT
    }
    
    @AfterSuite
    public void cleanPoliciesTest() throws Exception {
	Utils.cleanUp(swagger_url);
    }    
}