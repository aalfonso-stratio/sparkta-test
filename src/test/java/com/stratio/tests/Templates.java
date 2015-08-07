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

public class Templates {
    Properties defaultProps;
    String swagger_url;
    
    @BeforeSuite
    public void prepareTemplatesTest() throws Exception {
	// Read properties file
	Properties swaggerProps = new Properties();
	swaggerProps.load(new FileInputStream("swagger.properties"));
	swagger_url = swaggerProps.getProperty("swagger_url");
	defaultProps = new Properties();
	defaultProps.load(new FileInputStream("templates.properties"));

    }
    
    @Test(description = "Get all templates with empty parameter")
    public void templates01() throws Exception {
	String url = swagger_url + "/template/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("templates01 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("templates01 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("templates01 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");	
    }
    
    @Test(description = "Get all templates with invalid type")
    public void templates02() throws Exception {
	String url = swagger_url + "/template/" + "invalid";
	HttpResponse response = Utils.sendGetRequest(url);
	
	System.out.println("templates02 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("templates02 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Get all input templates")
    public void templates03() throws Exception {
	String input_templates = defaultProps.getProperty("input_templates");
	
	String url = swagger_url + "/template/" + "input";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("templates03 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("templates03 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("templates03 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	JSONArray bodyJson  = new JSONArray(responseBody);
	Assert.assertEquals(bodyJson.length(), Integer.parseInt(input_templates));
    }
    
    @Test(description = "Get all outputs templates")
    public void templates04() throws Exception {
	String outputs_templates = defaultProps.getProperty("outputs_templates");
	
	String url = swagger_url + "/template/" + "output";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("templates04 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("templates04 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("templates04 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	JSONArray bodyJson  = new JSONArray(responseBody);
	Assert.assertEquals(bodyJson.length(), Integer.parseInt(outputs_templates));
    }
    
    @Test(description = "Get template with empty type")
    public void templates05() throws Exception {
	String existing_template = defaultProps.getProperty("existing_template");
	
	String url = swagger_url + "/template/" + "" + "/" + existing_template;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("templates05 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("templates05 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");
    }
    
    @Test(description = "Get template with empty name")
    public void templates06() throws Exception {
	String existing_type = defaultProps.getProperty("existing_type");
	
	String url = swagger_url + "/template/" + existing_type + "/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("templates06 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("templates06 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");
    }
    
    @Test(description = "Get template with empty name and type")
    public void templates07() throws Exception {
	String url = swagger_url + "/template/" + "" + "/" + "";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("templates07 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("templates07 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
	Assert.assertEquals(responseBody, "The requested resource could not be found.");
    }
    
    @Test(description = "Get template with invalid type")
    public void templates08() throws Exception {
	String existing_template = defaultProps.getProperty("existing_template");
	
	String url = swagger_url + "/template/" + "invalid" + "/" + existing_template;
	HttpResponse response = Utils.sendGetRequest(url);
	
	System.out.println("templates08 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("templates08 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Get template with non-existing name")
    public void templates09() throws Exception {
	String existing_type = defaultProps.getProperty("existing_type");
	
	String url = swagger_url + "/template/" + existing_type + "/" + "invalid";
	HttpResponse response = Utils.sendGetRequest(url);
	
	System.out.println("templates09 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("templates09 Response Message: " + response.getStatusLine().getReasonPhrase());
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");
    }
    
    @Test(description = "Get existing template")
    public void templates10() throws Exception {
	String existing_type = defaultProps.getProperty("existing_type");
	String existing_template = defaultProps.getProperty("existing_template");
	
	String url = swagger_url + "/template/" + existing_type + "/" + existing_template;
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	
	System.out.println("templates10 Response Code: " + response.getStatusLine().getStatusCode());
	System.out.println("templates10 Response Message: " + response.getStatusLine().getReasonPhrase());
	System.out.println("templates10 Response Body: " + responseBody);
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
	Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
    }
}