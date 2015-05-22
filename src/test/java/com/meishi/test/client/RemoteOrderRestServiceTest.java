package com.meishi.test.client;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { RestClientApplication.class })
public class RemoteOrderRestServiceTest {
	
	@Autowired
	private RestTemplate restClient;
	
	@Test
	public void testCreateOrder(){
		String request = "{\"meishiList\" : \"Dish2\", \"clientLocation\" : \"\", \"clientId\" : \"123456789\"}";
		postRequestOK("http://localhost:8080/meishi/createOrder", request);
	}
	
	@Test
	public void testCookAcceptOrder(){
		postRequestOK("http://localhost:8080/meishi/cookAcceptOrder", null);
	}
	
	@Test
	public void testCookDoneOrder(){
		postRequestOK("http://localhost:8080/meishi/cookDoneOrder", null);
	}
	
	@Test
	public void testSenderAcceptOrder(){
		postRequestOK("http://localhost:8080/meishi/senderAcceptOrder", null);
	}
	
	@Test
	public void testSenderDoneOrder(){
		postRequestOK("http://localhost:8080/meishi/senderDoneOrder", null);
	}
	
	@Test
	public void orderChain(){
		testCreateOrder();
		testCookAcceptOrder();
		testCookDoneOrder();
		testSenderAcceptOrder();
		testSenderDoneOrder();
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
