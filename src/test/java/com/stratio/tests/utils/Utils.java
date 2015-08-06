package com.stratio.tests.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

public class Utils {
    
    public static HttpResponse sendGetRequest(String url) throws Exception {
	HttpClient client = HttpClientBuilder.create().build();        
	HttpGet get = new HttpGet(url);        

	HttpResponse response = client.execute(get);
	
	return response;
    }
    
    public static HttpResponse sendPostRequest(String url, String data) throws Exception {
	HttpClient client = HttpClientBuilder.create().build();        
	HttpPost post = new HttpPost(url);        

	post.setEntity(new StringEntity(data, ContentType.create("application/json")));

	HttpResponse response = client.execute(post);
	
	return response;
    }
    
    public static HttpResponse sendDeleteRequest(String url) throws Exception {
	HttpClient client = HttpClientBuilder.create().build();        
	HttpDelete delete = new HttpDelete(url); 
	
	HttpResponse response = client.execute(delete);
	
	return response;
    }
    
    public static HttpResponse sendPutRequest(String url, String data) throws Exception {
	HttpClient client = HttpClientBuilder.create().build();        
	HttpPut put = new HttpPut(url); 
	
	put.setEntity(new StringEntity(data, ContentType.create("application/json")));
	
	HttpResponse response = client.execute(put);
	
	return response;
    }
    
    public static String getResponseBody(HttpResponse response) throws Exception {
	HttpEntity responseEntity = response.getEntity();
	String responseBody = EntityUtils.toString(responseEntity);
	
	return responseBody;
    }
    
    public static void cleanUp(String swagger_url) throws Exception {
	// Check all available input fragments and delete them
	String url = swagger_url + "/fragment/" + "input";
	HttpResponse response = Utils.sendGetRequest(url);
	String responseBody = Utils.getResponseBody(response);
	JSONArray bodyJson  = new JSONArray(responseBody);
		
	String fragmentName = "";
	JSONObject fragment;
	if (bodyJson.length() > 0) {
	    for (int i = 0; i < bodyJson.length(); i++) {
		fragment = (JSONObject) bodyJson.get(i);
		fragmentName = fragment.getString("name");
		url = swagger_url + "/fragment/" + "input" + "/" + fragmentName;
		response = Utils.sendDeleteRequest(url);
			
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	    }
	}
	
	// Check all available output fragments and delete them
	url = swagger_url + "/fragment/" + "output";
	response = Utils.sendGetRequest(url);
	responseBody = Utils.getResponseBody(response);
	bodyJson  = new JSONArray(responseBody);
		
	if (bodyJson.length() > 0) {
	    for (int i = 0; i < bodyJson.length(); i++) {
		fragment = (JSONObject) bodyJson.get(i);
		fragmentName = fragment.getString("name");
		url = swagger_url + "/fragment/" + "output" + "/" + fragmentName;
		response = Utils.sendDeleteRequest(url);
	
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	    }
	}
		
	// Check all available policies and delete them
	url = swagger_url + "/policy/all";
	response = Utils.sendGetRequest(url);
	responseBody = Utils.getResponseBody(response);
	bodyJson  = new JSONArray(responseBody);
		
	String policyName = "";
	JSONObject policy;	
	if (bodyJson.length() > 0) {
	    for (int i = 0; i < bodyJson.length(); i++) {
		policy = (JSONObject) bodyJson.get(i);
		policyName = policy.getString("name");
		url = swagger_url + "/policy/" + policyName;
		response = Utils.sendDeleteRequest(url);
		
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
	    }
	}
    }
}