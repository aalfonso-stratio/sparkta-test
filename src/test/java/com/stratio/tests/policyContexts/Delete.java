package com.stratio.tests.policyContexts;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.http.HttpResponse;
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
	defaultProps.load(new FileInputStream("policyContexts.properties"));
	
	// Clean everything
	Utils.cleanUp(swagger_url);
    }
    
    @Test(description = "Delete policy context using empty parameter")
    public void delete01() throws Exception {
	String url = swagger_url + "/policyContext/" + "";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("delete01 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("delete01 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("delete01 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 405);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Method Not Allowed");
	Assert.assertEquals(responseBody, "HTTP method not allowed, supported methods: GET");
    }
    
    @Test(description = "Delete non-existing policy context when none available")
    public void delete02() throws Exception {
	String url = swagger_url + "/policyContext/" + "name";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("delete02 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("delete02 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("delete02 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	// TIMING OUT
	//Assert.assertEquals(responseBody, "Request entity expected but not supplied");
    }
    
    @Test(description = "Delete non-existing policy context")
    public void delete03() throws Exception {
	String url = swagger_url + "/policyContext/" + "name";
	HttpResponse response = Utils.sendDeleteRequest(url);
	String responseBody = Utils.getResponseBody(response);
		
	System.out.println("delete03 Delete Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("delete03 Delete Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("delete03 Delete Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	
	// TIMING OUT
    }
    
    @Test(description = "Delete policy context")
    public void delete04() throws Exception {
	// Need to add a policy context
	String policyExample = defaultProps.getProperty("policyExample");	
	
	String url = swagger_url + "/policyContext";
	HttpResponse response = Utils.sendPostRequest(url, policyExample);

	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	
	System.out.println("delete04: Policy Context added.");
	
	// Delete policy Context
	String policyExampleName = defaultProps.getProperty("policyExampleName");
	
	String url2 = swagger_url + "/policyContext/" + policyExampleName;
	HttpResponse response2 = Utils.sendDeleteRequest(url2);
	String responseBody2 = Utils.getResponseBody(response2);
		
	System.out.println("delete04 Delete Response Code: " + response2.getStatusLine().getStatusCode());
	System.out.println("delete04 Delete Response Message: " + response2.getStatusLine().getReasonPhrase());
	System.out.println("delete04 Delete Response Body: " + responseBody2);
	
	Assert.assertEquals(response2.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response2.getStatusLine().getReasonPhrase(), "OK");
	
	// TIMING OUT
    }
    
    @AfterTest
    public void cleanDeleteTest() throws Exception {
	Utils.cleanUp(swagger_url);
    }   
}