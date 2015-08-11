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

public class Fragments {
    Properties defaultProps;
    String swagger_url;
    
    @BeforeSuite
    public void prepareFragmentsTest() throws Exception {
	// Read properties file
	Properties swaggerProps = new Properties();
	swaggerProps.load(new FileInputStream("swagger.properties"));
	swagger_url = swaggerProps.getProperty("swagger_url");
	defaultProps = new Properties();
	defaultProps.load(new FileInputStream("fragments.properties"));

    }
    
    @Test(description = "Add fragment with empty parameter")
    public void fragments01() throws Exception {
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, "");
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments01 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments01 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments01 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertEquals(responseBody, "Request entity expected but not supplied");	
    }
    
    @Test(description = "Add fragment with missing name")
    public void fragments02() throws Exception {
	String invalidFragmentNoName = defaultProps.getProperty("invalidFragmentNoName");
	
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentNoName);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments02 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments02 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments02 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("No usable value for name"));
    }
    
    @Test(description = "Add fragment with missing fragmentType")
    public void fragments03() throws Exception {
	String invalidFragmentNoType = defaultProps.getProperty("invalidFragmentNoType");
	
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentNoType);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments03 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments03 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments03 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("No usable value for fragmentType"));
    }
    
    @Test(description = "Add fragment with missing description")
    public void fragments04() throws Exception {
	String invalidFragmentNoDescription = defaultProps.getProperty("invalidFragmentNoDescription");
	
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentNoDescription);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments04 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments04 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments04 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("No usable value for description"));
    }
    
    @Test(description = "Add fragment with missing short description")
    public void fragments05() throws Exception {
	String invalidFragmentNoShortDescription = defaultProps.getProperty("invalidFragmentNoShortDescription");
	
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentNoShortDescription);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments05 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments05 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments05 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("No usable value for shortDescription"));
    }
    
    @Test(description = "Add fragment with missing icon")
    public void fragments06() throws Exception {
	String invalidFragmentNoIcon = defaultProps.getProperty("invalidFragmentNoIcon");
	
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentNoIcon);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments06 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments06 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments06 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("No usable value for icon"));
    }
    
    @Test(description = "Add fragment with missing element")
    public void fragments07() throws Exception {
	String invalidFragmentNoElement = defaultProps.getProperty("invalidFragmentNoElement");
	
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentNoElement);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments07 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments07 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments07 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("No usable value for element"));
    }
    
    @Test(description = "Add fragment with incorrect type")
    public void fragments08() throws Exception {
	String invalidFragmentIncorrectType = defaultProps.getProperty("invalidFragmentIncorrectType");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentIncorrectType);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments08 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments08 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments08 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "The fragment type must be input|output");	
    }
    
    @Test(description = "Get all fragments with empty type")
    public void fragments09() throws Exception {
	String url = swagger_url + "/fragment/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments09 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments09 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments09 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");	
    }
    
    @Test(description = "Get all fragments with invalid type")
    public void fragments10() throws Exception {
	String url = swagger_url + "/fragment/" + "invalid";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments10 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments10 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments10 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");

	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "The fragment type must be input|output");	
    }
    
    @Test(description = "Get all input fragments with no fragments")
    public void fragments11() throws Exception {
	String url = swagger_url + "/fragment/" + "input";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments11 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments11 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments11 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "[]");	
    }
    
    @Test(description = "Get all output fragments with no fragments")
    public void fragments12() throws Exception {
	String url = swagger_url + "/fragment/" + "output";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments12 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments12 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments12 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "[]");	
    }
    
    @Test(description = "Get fragments with empty type")
    public void fragments13() throws Exception {
	String url = swagger_url + "/fragment/" + "" + "/name";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments13 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments13 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments13 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");	
    }
    
    @Test(description = "Get fragments with empty name")
    public void fragments14() throws Exception {
	String url = swagger_url + "/fragment/" + "input" + "/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments14 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments14 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments14 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");	
    }
    
    @Test(description = "Get fragments with empty name and type")
    public void fragments15() throws Exception {
	String url = swagger_url + "/fragment/" + "/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments15 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments15 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments15 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");	
    }
    
    @Test(description = "Get fragments with invalid type")
    public void fragments16() throws Exception {
	String url = swagger_url + "/fragment/" + "invalid" + "/" + "name";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments16 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments16 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments16 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");

	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "The fragment type must be input|output");
    }
    
    @Test(description = "Get input fragments with non-existing name with empty list")
    public void fragments17() throws Exception {
	String url = swagger_url + "/fragment/" + "input" + "/" + "name";
	HttpResponse response = Utils.sendGetRequest(url);
	
	System.out.println("fragments17 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments17 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Get output fragments with non-existing name with empty list")
    public void fragments18() throws Exception {
	String url = swagger_url + "/fragment/" + "output" + "/" + "name";
	HttpResponse response = Utils.sendGetRequest(url);
	
	System.out.println("fragments18 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments18 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");	
    }
    
    @Test(description = "Delete a policy with empty type")
    public void fragments19() throws Exception {
	String url = swagger_url + "/fragment/" + "" + "/" + "name";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("fragments19 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments19 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments19 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 405);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Method Not Allowed");
	Assert.assertEquals(responseBody, "HTTP method not allowed, supported methods: GET");
    }
    
    @Test(description = "Delete a policy with empty name")
    public void fragments20() throws Exception {
	String url = swagger_url + "/fragment/" + "input" + "/" + "";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("fragments20 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments20 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments20 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 405);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Method Not Allowed");
	Assert.assertEquals(responseBody, "HTTP method not allowed, supported methods: GET");
    }
    
    @Test(description = "Delete a policy with empty type and name")
    public void fragments21() throws Exception {
	String url = swagger_url + "/fragment/" + "" + "/" + "";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("fragments21 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments21 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments21 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 405);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Method Not Allowed");
	Assert.assertEquals(responseBody, "HTTP method not allowed, supported methods: GET");
    }
    
    @Test(description = "Delete a policy with invalid type")
    public void fragments22() throws Exception {
	String url = swagger_url + "/fragment/" + "invalid" + "/" + "name";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments22 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments22 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments22 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");

	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "The fragment type must be input|output");
    }
    
    @Test(description = "Delete a fragment with non-existing name and empty list")
    public void fragments23() throws Exception {
	String url = swagger_url + "/fragment/" + "input" + "/" + "name";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("fragments23 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments23 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments23 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Add valid input fragment")
    public void fragments24() throws Exception {
	String validInputFragment = defaultProps.getProperty("validInputFragment");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, validInputFragment);
	
	System.out.println("fragments24 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments24 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
    }
    
    @Test(description = "Add valid output fragment")
    public void fragments25() throws Exception {
	String validOutputFragment = defaultProps.getProperty("validOutputFragment");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, validOutputFragment);
	
	System.out.println("fragments25 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments25 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
    }
    
    @Test(description = "Get all input fragments")
    public void fragments26() throws Exception {
	String url = swagger_url + "/fragment/" + "input";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments26 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments26 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments26 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	JSONArray bodyJson  = new JSONArray(responseBody);
	Assert.assertEquals(bodyJson.length(), 1);	
    }
    
    @Test(description = "Get all output fragments")
    public void fragments27() throws Exception {
	String url = swagger_url + "/fragment/" + "output";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments27 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments27 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments27 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	JSONArray bodyJson  = new JSONArray(responseBody);
	Assert.assertEquals(bodyJson.length(), 1);	
    }
    
    @Test(description = "Get existing input fragment")
    public void fragments28() throws Exception {
	String validInputFragmentName = defaultProps.getProperty("validInputFragmentName");
	String validInputFragment = defaultProps.getProperty("validInputFragment");
	
	String url = swagger_url + "/fragment/" + "input" + "/" + validInputFragmentName;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments28 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments28 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments28 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, validInputFragment);
    }
    
    @Test(description = "Get existing output fragment")
    public void fragments29() throws Exception {
	String validOutputFragmentName = defaultProps.getProperty("validOutputFragmentName");
	String validOutputFragment = defaultProps.getProperty("validOutputFragment");
	
	String url = swagger_url + "/fragment/" + "output" + "/" + validOutputFragmentName;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("fragments29 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments29 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments29 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, validOutputFragment);
    }
    
    @Test(description = "Delete a fragment with non-existing name")
    public void fragments30() throws Exception {
	String url = swagger_url + "/fragment/" + "input" + "/" + "name";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("fragments30 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments30 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments30 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Delete an input fragment")
    public void fragments31() throws Exception {
	String validInputFragmentName = defaultProps.getProperty("validInputFragmentName");
	
	String url = swagger_url + "/fragment/" + "input" + "/" + validInputFragmentName;
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("fragments31 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments31 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments31 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	// Check no input fragments available
	String url2 = swagger_url + "/fragment/" + "input";
	HttpResponse response2 = Utils.sendGetRequest(url2);
	String responseBody2 = Utils.getResponseBody(response2);
	
	JSONArray bodyJson  = new JSONArray(responseBody2);
	Assert.assertEquals(bodyJson.length(), 0);
	
    }
    
    @Test(description = "Delete an output fragment")
    public void fragments32() throws Exception {
	String validOutputFragmentName = defaultProps.getProperty("validOutputFragmentName");
	
	String url = swagger_url + "/fragment/" + "output" + "/" + validOutputFragmentName;
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("fragments32 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("fragments32 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("fragments32 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	// Check no output fragments available
	String url2 = swagger_url + "/fragment/" + "output";
	HttpResponse response2 = Utils.sendGetRequest(url2);
	String responseBody2 = Utils.getResponseBody(response2);
		
	JSONArray bodyJson  = new JSONArray(responseBody2);
	Assert.assertEquals(bodyJson.length(), 0);
    }
    
    @Test(description = "Delete input fragment referenced by policy")
    public void fragments33() throws Exception {
	// Add fragment
	String validInputFragment = defaultProps.getProperty("validInputFragment");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, validInputFragment);
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
	
	System.out.println("fragments33: Fragment created");
	
	// Add policy
	String policyInputFragment = defaultProps.getProperty("policyInputFragment");
	String url2 = swagger_url + "/policy";
	HttpResponse response2 = Utils.sendPostRequest(url2, policyInputFragment);
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "Created");
	
	System.out.println("fragments33: Policy created");
	
	// Delete fragment
	String validInputFragmentName = defaultProps.getProperty("validInputFragmentName");
	
	String url3 = swagger_url + "/fragment/" + "input" + "/" + validInputFragmentName;
	HttpResponse response3 = Utils.sendDeleteRequest(url3);
	String responseBody3 = Utils.getResponseBody(response3);
		
	System.out.println("fragments33 Response Code: " + response3.getStatusLine().getStatusCode());
	System.out.println("fragments33 Response Message: " + response3.getStatusLine().getReasonPhrase());
	System.out.println("fragments33 Response Body: " + responseBody3);
	
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
    public void fragments34() throws Exception {
	// Add fragment
	String validOutputFragment = defaultProps.getProperty("validOutputFragment");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, validOutputFragment);
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
	
	System.out.println("fragments34: Fragment created");
	
	// Add policy
	String policyOutputFragment = defaultProps.getProperty("policyOutputFragment");
	String url2 = swagger_url + "/policy";
	HttpResponse response2 = Utils.sendPostRequest(url2, policyOutputFragment);
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "Created");
	
	System.out.println("fragments34: Policy created");
	
	// Delete fragment
	String validOutputFragmentName = defaultProps.getProperty("validOutputFragmentName");
	
	String url3 = swagger_url + "/fragment/" + "output" + "/" + validOutputFragmentName;
	HttpResponse response3 = Utils.sendDeleteRequest(url3);
	String responseBody3 = Utils.getResponseBody(response3);
		
	System.out.println("fragments34 Response Code: " + response3.getStatusLine().getStatusCode());
	System.out.println("fragments34 Response Message: " + response3.getStatusLine().getReasonPhrase());
	System.out.println("fragments34 Response Body: " + responseBody3);
	
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
 
    @AfterSuite
    public void cleanPoliciesTest() throws Exception {
	Utils.cleanUp(swagger_url);
    }
}