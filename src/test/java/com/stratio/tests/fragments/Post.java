package com.stratio.tests.fragments;

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
    public void preparePostTest() throws Exception {
	// Read properties file
	Properties swaggerProps = new Properties();
	swaggerProps.load(new FileInputStream("swagger.properties"));
	swagger_url = swaggerProps.getProperty("swagger_url");
	defaultProps = new Properties();
	defaultProps.load(new FileInputStream("fragments.properties"));
	
	// Clean everything
	Utils.cleanUp(swagger_url);
    }
    
    @Test(description = "Add fragment with empty parameter")
    public void post01() throws Exception {
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, "");
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post01 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post01 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post01 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertEquals(responseBody, "Request entity expected but not supplied");	
    }
    
    @Test(description = "Add fragment with missing name")
    public void post02() throws Exception {
	String invalidFragmentNoName = defaultProps.getProperty("invalidFragmentNoName");
	
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentNoName);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post02 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post02 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post02 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("No usable value for name"));
    }
    
    @Test(description = "Add fragment with missing fragmentType")
    public void post03() throws Exception {
	String invalidFragmentNoType = defaultProps.getProperty("invalidFragmentNoType");
	
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentNoType);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post03 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post03 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post03 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("No usable value for fragmentType"));
    }
    
    @Test(description = "Add fragment with missing description")
    public void post04() throws Exception {
	String invalidFragmentNoDescription = defaultProps.getProperty("invalidFragmentNoDescription");
	
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentNoDescription);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post04 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post04 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post04 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("No usable value for description"));
    }
    
    @Test(description = "Add fragment with missing short description")
    public void post05() throws Exception {
	String invalidFragmentNoShortDescription = defaultProps.getProperty("invalidFragmentNoShortDescription");
	
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentNoShortDescription);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post05 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post05 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post05 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("No usable value for shortDescription"));
    }
    
    @Test(description = "Add fragment with missing icon")
    public void post06() throws Exception {
	String invalidFragmentNoIcon = defaultProps.getProperty("invalidFragmentNoIcon");
	
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentNoIcon);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post06 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post06 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post06 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("No usable value for icon"));
    }
    
    @Test(description = "Add fragment with missing element")
    public void post07() throws Exception {
	String invalidFragmentNoElement = defaultProps.getProperty("invalidFragmentNoElement");
	
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentNoElement);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post07 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post07 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post07 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 400);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Bad Request");
	Assert.assertTrue(responseBody.contains("No usable value for element"));
    }
    
    @Test(description = "Add fragment with incorrect type")
    public void post08() throws Exception {
	String invalidFragmentIncorrectType = defaultProps.getProperty("invalidFragmentIncorrectType");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, invalidFragmentIncorrectType);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("post08 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post08 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("post08 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "The fragment type must be input|output");	
    }
    
    @Test(description = "Add valid input fragment")
    public void post09() throws Exception {
	String validInputFragment = defaultProps.getProperty("validInputFragment");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, validInputFragment);
	
	System.out.println("post09 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post09 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
    }
    
    @Test(description = "Add valid output fragment")
    public void post10() throws Exception {
	String validOutputFragment = defaultProps.getProperty("validOutputFragment");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, validOutputFragment);
	
	System.out.println("post10 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("post10 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
    }
        
    @AfterTest
    public void cleanPostTest() throws Exception {
	Utils.cleanUp(swagger_url);
    }   
    
}