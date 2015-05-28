package com.meishi;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

//import com.meishi.rest.MyStringHttpMessageConverter;
import com.meishi.test.repository.app.MeishiRepositoryApplicationForTest;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import(MeishiRepositoryApplicationForTest.class)
public class MeishiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeishiApplication.class, args);
	}
	
//	@Bean
//	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
//	    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
////	    builder.serializationInclusion(JsonInclude.Include.NON_NULL);
//	    return builder;
//	}
	
//	@Bean
//	public MappingJackson2HttpMessageConverter jsonConverter(){
//		MappingJackson2HttpMessageConverter result = new MappingJackson2HttpMessageConverter();
//		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
//		supportedMediaTypes.add(new MediaType("applicaiton", "json", StandardCharsets.UTF_8));
//		supportedMediaTypes.add(new MediaType("text", "html", StandardCharsets.UTF_8));
//		result.setSupportedMediaTypes(supportedMediaTypes);
//		return result;
//		
//	}
	
//	@Bean
//	public StringHttpMessageConverter stringConverter(){
//		return new StringHttpMessageConverter(StandardCharsets.UTF_8);
//	}
	
//	@Bean
//	public RequestMappingHandlerAdapter requestAdapter(){
//		RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
////		adapter.getMessageConverters().add(stringConverter());
//		adapter.getMessageConverters().add(jsonConverter());
//		adapter.getMessageConverters().add(new MyStringHttpMessageConverter());
//		return adapter;
//	}

}
