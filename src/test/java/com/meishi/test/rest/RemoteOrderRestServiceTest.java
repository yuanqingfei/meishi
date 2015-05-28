package com.meishi.test.rest;

import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Qualifier("customerClient")
	private RestTemplate customerClient;

	@Autowired
	@Qualifier("adminClient")
	private RestTemplate adminClient;

	@Autowired
	@Qualifier("cookClient")
	private RestTemplate cookClient;

	@Autowired
	@Qualifier("senderClient")
	private RestTemplate senderClient;

	@Test
	public void testCreateOrder() {
		String request = "{\"meishiList\" : \"À±×Ó¼¦\", \"clientLocation\" : \"\", \"clientId\" : \"123456789\"}";
		postRequestOK(customerClient, "http://localhost:8080/meishi/createOrder", request);
	}

	@Test
	public void testCookAcceptOrder() {
		postRequestOK(cookClient, "http://localhost:8080/meishi/cookAcceptOrder", null);
	}

	@Test
	public void testCookDoneOrder() {
		postRequestOK(cookClient, "http://localhost:8080/meishi/cookDoneOrder", null);
	}

	@Test
	public void testSenderAcceptOrder() {
		postRequestOK(senderClient, "http://localhost:8080/meishi/senderAcceptOrder", null);
	}

	@Test
	public void testSenderDoneOrder() {
		postRequestOK(senderClient, "http://localhost:8080/meishi/senderDoneOrder", null);
	}

	@Test
	public void orderChain() {
//		testCreateOrder();
		testCookAcceptOrder();
		testCookDoneOrder();
		testSenderAcceptOrder();
		testSenderDoneOrder();
	}

	private void postRequestOK(RestTemplate restClient, String url, String requestString) {
		HttpHeaders headers = new HttpHeaders();
		// need set UTF-8 here for chinese input. For sever side
		// both acitiviti and spring json handler already support utf-8.
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
		HttpEntity<String> request = new HttpEntity<String>(requestString, headers);
		ResponseEntity<String> response = restClient.exchange(url, HttpMethod.POST, request, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
