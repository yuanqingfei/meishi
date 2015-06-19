package com.meishi.test.rest;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientApplication {

	@Bean(name = "customerClient")
	public RestTemplate customerClient() {
		return createRestTemplate(user("14700000000", "111111"));
	}

	@Bean(name = "cookClient")
	public RestTemplate cookClient() {
		return createRestTemplate(user("1234567", "111"));
	}

	@Bean(name = "senderClient")
	public RestTemplate senderClient() {
		return createRestTemplate(user("12345678", "111"));
	}

	@Bean(name = "adminClient")
	public RestTemplate adminClient() {
		return createRestTemplate(user("123456", "111"));
	}
	
//	@Bean
//	public StringHttpMessageConverter stringConverter(){
//		return new StringHttpMessageConverter(StandardCharsets.UTF_8);
//	}

	private RestTemplate createRestTemplate(CredentialsProvider provider) {
		HttpHost host = new HttpHost("localhost", 8080, "http");
		CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider)
				.useSystemProperties().build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactoryDigestAuth(
				host, client);
		// SimpleClientHttpRequestFactory factory = new
		// SimpleClientHttpRequestFactory();
		// requestFactory.setConnectTimeout(30*1000);
		// requestFactory.setReadTimeout(30*1000);

		// add support to UTF-8 for String Convent
		RestTemplate template = new RestTemplate(requestFactory);
//		List<HttpMessageConverter<?>> converterList = template.getMessageConverters();
//		HttpMessageConverter<?> converterTarget = null;
//		for (HttpMessageConverter<?> item : converterList) {
//			if (item.getClass() == StringHttpMessageConverter.class) {
//				converterTarget = item;
//				break;
//			}
//		}
//
//		if (converterTarget != null) {
//			converterList.remove(converterTarget);
//		}
//		HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
//		converterList.add(converter);
		return template;
	}

	private CredentialsProvider user(String userName, String passWord) {
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(userName, passWord);
		provider.setCredentials(AuthScope.ANY, credentials);
		return provider;
	}
}
