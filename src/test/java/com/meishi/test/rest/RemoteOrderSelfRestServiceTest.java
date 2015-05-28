package com.meishi.test.rest;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Ignore;
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

/**
 * This test try to call Acitiviti Rest Service for order creating with
 * RestTemplate
 * 
 * @author Aaron
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { RestClientApplication.class })
public class RemoteOrderSelfRestServiceTest {

	private Logger logger = Logger.getLogger(this.getClass());

	private static final String TASK_URL = "http://localhost:8080/runtime/tasks";

	private static final String INSTANCE_URL = "http://localhost:8080/runtime/process-instances";

	private static final String COMPLETE_REQ = "{\"action\" : \"complete\", \"variables\" : []}";

	@Autowired
	private RestTemplate restTemplate;

	// @Test
	// public void testClaimOrderForCustomer() {
	// claimOrderFor("client1");
	// }

	public void testStartProcessInstance() {
		String startProcessInstanceReq = "{\"processDefinitionKey\":\"orderProcess\"}";
		postRequestCreated(INSTANCE_URL, startProcessInstanceReq);
	}

	public void testCreateOrder() {
		// testClaimOrderForCustomer();

		String completeRequest = "{\"action\" : \"complete\", \"variables\" : "
				+ "[{\"name\" : \"meishiList\", \"value\" : \"LaZiJi\"}, "
				+ "{\"name\" : \"clientLocation\", \"value\" : \"XuJiaHui\"}, "
				+ "{\"name\" : \"clientId\", \"value\" : \"111222333\"}" + "]}";
		// postRequest(getTaskUrlForAssignee("client1"), completeRequest);
		postRequestOK(getDetailsTaskUrl(TASK_URL), completeRequest);
	}

	// @Test
	// public void testClaimOrderForCook() {
	// testCreateOrder();
	// claimOrderFor("cook1");
	// }

	public void testCookAcceptOrder() {
		// postRequest(getTaskUrlForAssignee("client1"), completeRequest);
		postRequestOK(getDetailsTaskUrl(TASK_URL), COMPLETE_REQ);
	}

	public void testCookDoneOrder() {
		postRequestOK(getDetailsTaskUrl(TASK_URL), COMPLETE_REQ);
	}

	public void testSenderAcceptOrder() {
		postRequestOK(getDetailsTaskUrl(TASK_URL), COMPLETE_REQ);
	}

	public void testSenderDoneOrder() {
		postRequestOK(getDetailsTaskUrl(TASK_URL), COMPLETE_REQ);
	}

	public void testAdminDealOrder() {
		postRequestOK(getDetailsTaskUrl(TASK_URL), COMPLETE_REQ);
	}

	@Test
	public void testSuccessOrder() {
		testStartProcessInstance();
		testCreateOrder();
		testCookAcceptOrder();
		testCookDoneOrder();
		testSenderAcceptOrder();
		testSenderDoneOrder();
	}

	@Test
	@Ignore
	public void testFailureOrder() throws InterruptedException {
		testStartProcessInstance();
		testCreateOrder();
		testCookAcceptOrder();
		testCookDoneOrder();
		testSenderAcceptOrder();
		Thread.sleep(65000);
		Assert.assertEquals("Escalation to Admin Info Admin and Refund", getTaskName(TASK_URL));
		testAdminDealOrder();
	}

	private JSONObject getTask(String url) {
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		JSONArray jsonArray = (JSONArray) new JSONObject(response.getBody()).get("data");
		JSONObject task = (JSONObject) jsonArray.get(0);
		return task;
	}

	private String getDetailsTaskUrl(String url) {
		JSONObject task = getTask(url);
		Assert.assertNotNull(task);
		String taskUrl = task.getString("url");
		logger.info("TASK URL: " + taskUrl);
		return taskUrl;
	}

	private String getTaskName(String url) {
		JSONObject task = getTask(url);
		Assert.assertNotNull(task);
		return task.getString("name");
	}

	private void postRequestCreated(String url, String requestString) {
		ResponseEntity<String> claimResponse = postRequest(url, requestString);
		Assert.assertEquals(HttpStatus.CREATED, claimResponse.getStatusCode());
	}

	private ResponseEntity<String> postRequest(String url, String requestString) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(requestString, headers);
		ResponseEntity<String> claimResponse = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		return claimResponse; 
	}

	private void postRequestOK(String url, String requestString) {
		ResponseEntity<String> claimResponse = postRequest(url, requestString);
		Assert.assertEquals(HttpStatus.OK, claimResponse.getStatusCode());
	}

	private String getTaskUrlForAssignee(String assignee) {
		return getDetailsTaskUrl(TASK_URL + "?assignee=" + assignee);

	}

	private void claimOrderFor(String user) {
		postRequestOK(getDetailsTaskUrl(TASK_URL), "{\"action\" : \"claim\", \"assignee\" : \"" + user + "\"}");
	}
}
