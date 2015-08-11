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
	defaultProps.load(new FileInputStream("fragments.properties"));
	
	// Clean everything
	Utils.cleanUp(swagger_url);
    }
    
    @Test(description = "Get all fragments with empty type")
    public void get01() throws Exception {
	String url = swagger_url + "/fragment/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get01 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get01 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get01 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");	
    }
    
    @Test(description = "Get all fragments with invalid type")
    public void get02() throws Exception {
	String url = swagger_url + "/fragment/" + "invalid";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get02 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get02 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get02 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");

	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "The fragment type must be input|output");	
    }
    
    @Test(description = "Get all input fragments with no fragments")
    public void get03() throws Exception {
	String url = swagger_url + "/fragment/" + "input";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get03 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get03 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get03 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "[]");	
    }
    
    @Test(description = "Get all output fragments with no fragments")
    public void get04() throws Exception {
	String url = swagger_url + "/fragment/" + "output";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get04 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get04 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get04 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, "[]");	
    }
    
    @Test(description = "Get fragments with empty type")
    public void get05() throws Exception {
	String url = swagger_url + "/fragment/" + "" + "/name";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get05 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get05 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get05 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");	
    }
    
    @Test(description = "Get fragments with empty name")
    public void get06() throws Exception {
	String url = swagger_url + "/fragment/" + "input" + "/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get06 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get06 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get06 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");	
    }
    
    @Test(description = "Get fragments with empty name and type")
    public void get07() throws Exception {
	String url = swagger_url + "/fragment/" + "/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get07 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get07 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get07 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");	
    }
    
    @Test(description = "Get fragments with invalid type")
    public void get08() throws Exception {
	String url = swagger_url + "/fragment/" + "invalid" + "/" + "name";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get08 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get08 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get08 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");

	JSONObject responseJSON = new JSONObject(responseBody);
	String message = responseJSON.get("message").toString();
	Assert.assertEquals(message, "The fragment type must be input|output");
    }
    
    @Test(description = "Get input fragments with non-existing name with empty list")
    public void get09() throws Exception {
	String url = swagger_url + "/fragment/" + "input" + "/" + "name";
	HttpResponse response = Utils.sendGetRequest(url);
	
	System.out.println("get09 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get09 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Get output fragments with non-existing name with empty list")
    public void get10() throws Exception {
	String url = swagger_url + "/fragment/" + "output" + "/" + "name";
	HttpResponse response = Utils.sendGetRequest(url);
	
	System.out.println("get10 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get10 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");	
    }
    
    @Test(description = "Get all input fragments")
    public void get11() throws Exception {
	// Add input fragment
	String validInputFragment = defaultProps.getProperty("validInputFragment");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, validInputFragment);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
	
	// Check input fragments
	String url2 = swagger_url + "/fragment/" + "input";
	HttpResponse response2 = Utils.sendGetRequest(url2);
	String responseBody2 = Utils.getResponseBody(response2);
	
	System.out.println("get11 Response Code: " + response2.getStatusLine().getStatusCode());
	System.out.println("get11 Response Message: " + response2.getStatusLine().getReasonPhrase());
	System.out.println("get11 Response Body: " + responseBody2);
	
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "OK");
	
	JSONArray bodyJson  = new JSONArray(responseBody2);
	Assert.assertEquals(bodyJson.length(), 1);	
    }
    
    @Test(description = "Get all output fragments")
    public void get12() throws Exception {
	// Add output fragment
	String validOutputFragment = defaultProps.getProperty("validOutputFragment");
	String url = swagger_url + "/fragment";
	HttpResponse response = Utils.sendPostRequest(url, validOutputFragment);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Created");
	
	// Check output fragments
	String url2 = swagger_url + "/fragment/" + "output";
	HttpResponse response2 = Utils.sendGetRequest(url2);
	String responseBody2 = Utils.getResponseBody(response2);
	
	System.out.println("get12 Response Code: " + response2.getStatusLine().getStatusCode());
	System.out.println("get12 Response Message: " + response2.getStatusLine().getReasonPhrase());
	System.out.println("get12 Response Body: " + responseBody2);
	
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "OK");
	
	JSONArray bodyJson  = new JSONArray(responseBody2);
	Assert.assertEquals(bodyJson.length(), 1);	
    }
    
    @Test(description = "Get existing input fragment")
    public void get13() throws Exception {
	String validInputFragmentName = defaultProps.getProperty("validInputFragmentName");
	String validInputFragment = defaultProps.getProperty("validInputFragment");
	
	String url = swagger_url + "/fragment/" + "input" + "/" + validInputFragmentName;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get13 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get13 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get13 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, validInputFragment);
    }
    
    @Test(description = "Get existing output fragment")
    public void get14() throws Exception {
	String validOutputFragmentName = defaultProps.getProperty("validOutputFragmentName");
	String validOutputFragment = defaultProps.getProperty("validOutputFragment");
	
	String url = swagger_url + "/fragment/" + "output" + "/" + validOutputFragmentName;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("get14 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("get14 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("get14 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	Assert.assertEquals(responseBody, validOutputFragment);
    }
        
    @AfterTest
    public void cleanGetTest() throws Exception {
	Utils.cleanUp(swagger_url);
    }    
}