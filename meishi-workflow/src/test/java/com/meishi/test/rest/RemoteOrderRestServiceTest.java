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

import com.meishi.model.Dish;
import com.meishi.model.OrderRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { RestClientApplication.class })
public class RemoteOrderRestServiceTest {

	private static Integer waitTime = 10000;

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
	public void testGetDishes() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json",
				StandardCharsets.UTF_8));
		HttpEntity<String> request = new HttpEntity<String>(null,
				headers);
		
		String url = "http://localhost:8080/entity/getDishByCenterAndDistance?location=31.126856,121.445299&"
				+ "distance=3km";
		ResponseEntity<String> response = customerClient.exchange(url,
				HttpMethod.GET, request, String.class);
		
		System.out.println(response.getBody());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testCreateOrder() {
		OrderRequest request = new OrderRequest();
		request.setAddress("");
		request.setClientId("14700000000");
		request.setDishName("À±×Ó¼¦");
		postOrderRequestOK(customerClient,
				"http://localhost:8080/action/createOrder", request);
	}

	@Test
	public void testCookAcceptOrder() {
		postRequestOK(cookClient,
				"http://localhost:8080/action/cookAcceptOrder", null);
	}

	@Test
	public void testCookDoneOrder() {
		postRequestOK(cookClient, "http://localhost:8080/action/cookDoneOrder",
				null);
	}

	@Test
	public void testSenderAcceptOrder() {
		postRequestOK(senderClient,
				"http://localhost:8080/action/senderAcceptOrder", null);
	}

	@Test
	public void testSenderDoneOrder() {
		postRequestOK(senderClient,
				"http://localhost:8080/action/senderDoneOrder", null);
	}

	@Test
	public void testAdminEsclationOrderFalseCook() {
		String request = "{\"refund\" : false, \"restart\" : \"C\"}";
		postRequestOK(adminClient,
				"http://localhost:8080/action/adminEsclateOrder", request);
	}

	@Test
	public void testAdminEsclationOrderFalseSender() {
		String request = "{\"refund\" : false, \"restart\" : \"S\"}";
		postRequestOK(adminClient,
				"http://localhost:8080/action/adminEsclateOrder", request);
	}

	@Test
	public void testAdminEsclationOrderTrue() {
		String request = "{\"refund\" : true}";
		postRequestOK(adminClient,
				"http://localhost:8080/action/adminEsclateOrder", request);
	}

	@Test
	public void orderSuccessChain() {
		testCreateOrder();
		testCookAcceptOrder();
		testCookDoneOrder();
		testSenderAcceptOrder();
		testSenderDoneOrder();
	}

	@Test
	public void orderCookFailureChain() throws InterruptedException {
		testCreateOrder();
		Thread.sleep(waitTime);
		testAdminEsclationOrderTrue();
	}

	@Test
	public void orderSenderFailureChain() throws InterruptedException {
		testCreateOrder();
		testCookAcceptOrder();
		testCookDoneOrder();
		Thread.sleep(waitTime);
		testAdminEsclationOrderTrue();
	}

	@Test
	public void orderReCookSuccessChain() throws InterruptedException {
		testCreateOrder();
		testCookAcceptOrder();
		Thread.sleep(waitTime);
		testAdminEsclationOrderFalseCook();
		testCookAcceptOrder();
		testCookDoneOrder();
		testSenderAcceptOrder();
		testSenderDoneOrder();
	}

	@Test
	public void orderReSenderSuccessChain() throws InterruptedException {
		testCreateOrder();
		testCookAcceptOrder();
		testCookDoneOrder();
		Thread.sleep(waitTime);
		testAdminEsclationOrderFalseSender();
		testSenderAcceptOrder();
		testSenderDoneOrder();
	}

	private void postRequestOK(RestTemplate restClient, String url,
			String requestString) {
		HttpHeaders headers = new HttpHeaders();
		// need set UTF-8 here for chinese input. For sever side
		// both acitiviti and spring json handler already support utf-8.
		headers.setContentType(new MediaType("application", "json",
				StandardCharsets.UTF_8));
		HttpEntity<String> request = new HttpEntity<String>(requestString,
				headers);
		ResponseEntity<String> response = restClient.exchange(url,
				HttpMethod.POST, request, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	private void postOrderRequestOK(RestTemplate restClient, String url,
			OrderRequest order) {
		HttpHeaders headers = new HttpHeaders();
		// need set UTF-8 here for chinese input. For sever side
		// both acitiviti and spring json handler already support utf-8.
		headers.setContentType(new MediaType("application", "json",
				StandardCharsets.UTF_8));
		HttpEntity<OrderRequest> request = new HttpEntity<OrderRequest>(order,
				headers);
		ResponseEntity<String> response = restClient.exchange(url,
				HttpMethod.POST, request, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
