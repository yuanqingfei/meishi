package com.meishi.test.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientApplication {

	@Bean
	public RestTemplate restTemplate() {
		HttpHost host = new HttpHost("localhost", 8080, "http");
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider())
				.useSystemProperties().build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactoryDigestAuth(
				host, client);
		// requestFactory.setConnectTimeout(30*1000);
		// requestFactory.setReadTimeout(30*1000);

		return new RestTemplate(requestFactory);
	}

	private CredentialsProvider provider() {
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("123456789", "111");
		provider.setCredentials(AuthScope.ANY, credentials);
		return provider;
	}
}
