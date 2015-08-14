package com.stratio.tests.policies;

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
	defaultProps.load(new FileInputStream("policies.properties"));
	
	// Clean everything
	Utils.cleanUp(swagger_url);
    }
    
    @Test(description = "Get all policies when no policies available")
    public void get01() throws Exception {
	String url = swagger_url + "/policy/all";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get01 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get01 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get01 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "[]");	
    }
    
    @Test(description = "Get a policy when no policies available")
    public void get02() throws Exception {
	String nonExistingPolicy = defaultProps.getProperty("nonExistingPolicy");
	
	String url = swagger_url + "/policy/find/" + nonExistingPolicy;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get02 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get02 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get02 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Get all policies with a particular fragment when no policies available")
    public void get03() throws Exception {
	String fragmentType = defaultProps.getProperty("fragmentType");
	String fragmentName = defaultProps.getProperty("fragmentName");
	
	String url = swagger_url + "/policy/fragment/" + fragmentType + "/" + fragmentName;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);	
	
	System.out.println("get03 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get03 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get03 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "[]");
    }
    
    @Test(description = "Run a policy when no policies available")
    public void get04() throws Exception {
	String nonExistingPolicy = defaultProps.getProperty("nonExistingPolicy");
	
	String url = swagger_url + "/policy/run/" + nonExistingPolicy;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get04 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get04 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get04 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Get a non-exisiting policy")
    public void get05() throws Exception {
	// Add policy
	String policyExample = defaultProps.getProperty("policyExample");
	
	String url = swagger_url + "/policy";
	HttpResponse response = Utils.sendPostRequest(url, policyExample);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");

	// Get non-existing policy
	String nonExistingPolicy = defaultProps.getProperty("nonExistingPolicy");
	
	String url = swagger_url + "/policy/find/" + nonExistingPolicy;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("get05 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get05 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get05 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");	
    }
    
    @Test(description = "Get a existing policy")
    public void get06() throws Exception {
	// Get policy
	String existingPolicy = defaultProps.getProperty("existingPolicy");
	String policyExampleReturned = defaultProps.getProperty("policyExampleReturned");
	
	String url2 = swagger_url + "/policy/find/" + existingPolicy;
	HttpResponse response2 = Utils.sendGetRequest(url2);
	String responseBody2 = Utils.getResponseBody(response2);
		
	System.out.println("get06 Response Code: " + response2.getStatusLine().getStatusCode());
	System.out.println("get06 Response Message: " + response2.getStatusLine().getReasonPhrase());
	System.out.println("get06 Response Body: " + responseBody2);
	
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody2, policyExampleReturned);
    }
    
    @Test(description = "Run a non-existing policy")
    public void get07() throws Exception {
	String nonExistingPolicy = defaultProps.getProperty("nonExistingPolicy");
		
	String url = swagger_url + "/policy/run/" + nonExistingPolicy;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get07 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get07 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get07 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Run a existing policy")
    public void get08() throws Exception {
	String existingPolicy = defaultProps.getProperty("existingPolicy");
	
	String url = swagger_url + "/policy/run/" + existingPolicy;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get08 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get08 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get08 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "{\"message\":\"Creating new context with name " + existingPolicy + "\"}");
	
	Thread.sleep(10000);
	
	// Check there is a new policy context
	String urlContext = swagger_url + "/policyContext";
	HttpResponse responseContext = Utils.sendGetRequest(urlContext);
	String responseBodyContext = Utils.getResponseBody(responseContext);
	
	System.out.println("get08 Context Response Code: " + responseContext.getStatusLine().getStatusCode());
	System.out.println("get08 Context Response Message: " + responseContext.getStatusLine().getReasonPhrase());
	System.out.println("get08 Context Response Body: " + responseBodyContext);
	
	Assert.assertEquals(responseContext.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(responseContext.getStatusLine().getReasonPhrase(), "OK");
	
	JSONArray bodyJson  = new JSONArray(responseBodyContext);
	Assert.assertEquals(bodyJson.length(), 1);
	if (bodyJson.length() == 1) {
	    String context = bodyJson.get(0).toString();
	    JSONObject jsonContext = new JSONObject(context);
	    Assert.assertEquals(jsonContext.get("status"),"Initializing");
	}
    }
    
    @Test(description = "Get all policies with fragment with incorrect fragment type")
    public void get09() throws Exception {
	String fragmentTypeIncorrect = defaultProps.getProperty("fragmentTypeIncorrect");
	String fragmentName = defaultProps.getProperty("fragmentName");
	
	String url = swagger_url + "/policy/fragment/" + fragmentTypeIncorrect  + "/" + fragmentName;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get09 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get09 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get09 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "[]");
    }
    
    @Test(description = "Get all policies with non-existing fragment")
    public void get10() throws Exception {
	String nonExistingFragment = defaultProps.getProperty("nonExistingFragment");
	
	String url = swagger_url + "/policy/fragment/input/" + nonExistingFragment;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("get10 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get10 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get10 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "[]");
    }
    
    @Test(description = "Get all policies with existing fragment")
    public void get11() throws Exception {
	// Add a fragment
	String fragmentExample = defaultProps.getProperty("fragmentExample");

	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, fragmentExample);
		        
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
	
	System.out.println("get11: Fragment created");
	
	// Add policy
	String policyExampleOneFragment = defaultProps.getProperty("policyExampleOneFragment");
	
	String url2 = swagger_url + "/policy";
	HttpResponse response2 = Utils.sendPostRequest(url2, policyExampleOneFragment);

	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "Created");
	
	System.out.println("get11: Policy created");
	
	// Get policy
	String fragmentType = defaultProps.getProperty("fragmentType");
	String fragmentName = defaultProps.getProperty("fragmentName");
		
	String url3 = swagger_url + "/policy/fragment/" + fragmentType + "/" + fragmentName;
	HttpResponse response3 = Utils.sendGetRequest(url3);
	String responseBody3 = Utils.getResponseBody(response3);	
		
	System.out.println("get11 Response Code: " + response3.getStatusLine().getStatusCode());
	System.out.println("get11 Response Message: " + response3.getStatusLine().getReasonPhrase());
	System.out.println("get11 Response Body: " + responseBody3);
		
	Assert.assertEquals(response3.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response3.getStatusLine().getReasonPhrase(), "OK");
	
	JSONArray bodyJson  = new JSONArray(responseBody3);
	Assert.assertEquals(bodyJson.length(), 1);	
    }
 
    @Test(description = "Get all policies with policies available")
    public void get12() throws Exception {
	String url = swagger_url + "/policy/all";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get12 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get12 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get12 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	JSONArray bodyJson  = new JSONArray(responseBody);
	Assert.assertEquals(bodyJson.length(), 2);
    }
    
    @Test(description = "Run a policy with 2 existing output fragments")
    public void get13() throws Exception {
	// Create output fragments
	String fragmentOutputExample = defaultProps.getProperty("fragmentOutputExample");
	String fragmentOutputExample2 = defaultProps.getProperty("fragmentOutputExample2");
			
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, fragmentOutputExample);
		        
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
			
	String url2 = swagger_url + "/fragment";
	HttpResponse response2 = Utils.sendPostRequest(url2, fragmentOutputExample2);
	
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "Created");
	
	// Add policy with 2 output fragments
	String policyExampleTwoOutputFragments = defaultProps.getProperty("policyExampleTwoOutputFragments");
	
	String url3 = swagger_url + "/policy";
	HttpResponse response3 = Utils.sendPostRequest(url3, policyExampleTwoOutputFragments);

	Assert.assertEquals(response3.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response3.getStatusLine().getReasonPhrase(), "Created");
	
	// It is possible to run a policy with 2 output fragments	
	String policyTwoOutputFragmentsName = defaultProps.getProperty("policyTwoOutputFragmentsName");
	
	String url4 = swagger_url + "/policy/run/" + policyTwoOutputFragmentsName;
	HttpResponse response4 = Utils.sendGetRequest(url4);
	String responseBody4 = Utils.getResponseBody(response4);
	
	System.out.println("get13 Response Code: " + response4.getStatusLine().getStatusCode());
	System.out.println("get13 Response Message: " + response4.getStatusLine().getReasonPhrase());
	System.out.println("get13 Response Body: " + responseBody4);
	
	Assert.assertEquals(response4.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response4.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody4, "{\"message\":\"Creating new context with name " + policyTwoOutputFragmentsName + "\"}");
	
	Thread.sleep(10000);
	
	// Check there is a new policy context
	String urlContext = swagger_url + "/policyContext";
	HttpResponse responseContext = Utils.sendGetRequest(urlContext);
	String responseBodyContext = Utils.getResponseBody(responseContext);
	
	System.out.println("get13 Context Response Code: " + responseContext.getStatusLine().getStatusCode());
	System.out.println("get13 Context Response Message: " + responseContext.getStatusLine().getReasonPhrase());
	System.out.println("get13 Context Response Body: " + responseBodyContext);
	
	Assert.assertEquals(responseContext.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(responseContext.getStatusLine().getReasonPhrase(), "OK");
	
	JSONArray bodyJson  = new JSONArray(responseBodyContext);
	for (int i = 0; i < bodyJson.length(); i++) {
	    String context = bodyJson.get(i).toString();
	    JSONObject jsonContext = new JSONObject(context);
	    if (jsonContext.get("name") == policyTwoOutputFragmentsName) {
		Assert.assertEquals(jsonContext.get("status"),"Initializing");
	    }
	}
    }
    
    @Test(description = "Get a policy with empty parameter")
    public void get14() throws Exception {
	String url = swagger_url + "/policy/find/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get14 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get14 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get14 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");
    }
    
    @Test(description = "Get all policies that contains a fragment with empty type")
    public void get15() throws Exception {
	String url = swagger_url + "/policy/fragment/" + "" + "/" + "name";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get15 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get15 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get15 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");
    }
    
    @Test(description = "Get all policies that contains a fragment with empty name")
    public void get16() throws Exception {
	String url = swagger_url + "/policy/fragment/" + "input" + "/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get16 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get16 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get16 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");
    }
    
    @Test(description = "Get all policies that contains a fragment with empty type and name")
    public void get17() throws Exception {
	String url = swagger_url + "/policy/fragment/" + "" + "/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get17 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get17 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get17 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");
    }
    
    @Test(description = "Run an empty policy")
    public void get18() throws Exception {
	String url = swagger_url + "/policy/run/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get18 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get18 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get18 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");
    }
    
    @AfterTest
    public void cleanGetTest() throws Exception {
	Utils.cleanUp(swagger_url);
    }
}
