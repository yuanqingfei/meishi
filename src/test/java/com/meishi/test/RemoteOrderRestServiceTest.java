package com.meishi.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.meishi.test.client.RestClientApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { RestClientApplication.class })
public class RemoteOrderRestServiceTest {
	
	@Autowired
	private RestTemplate restClient;
	
	@Test
	public void testCreateOrder(){
		String request = "{\"meishiList\" : \"AAAAAAAAAAAAAA\", \"clientLocation\" : \"VVVVVVVVVV\", \"clientId\" : \"9999999\"}";
		postRequestCreated("http://localhost:8080/meishi/createOrder", request);
	}
	
	
	@Test
	public void testCookAcceptOrder(){
		postRequestOK("http://localhost:8080/meishi/cookAcceptOrder", null);
	}
	
	private void postRequestCreated(String url, String requestString) {
		Assert.assertEquals(HttpStatus.CREATED, postRequest(url, requestString).getStatusCode());
	}
	
	private void postRequestOK(String url, String requestString) {
		Assert.assertEquals(HttpStatus.OK, postRequest(url, requestString).getStatusCode());
	}

	private ResponseEntity<String> postRequest(String url, String requestString) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(requestString, headers);
		ResponseEntity<String> response = restClient.exchange(url, HttpMethod.POST, request, String.class);
		return response; 
	}



}
