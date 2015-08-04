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
    
}