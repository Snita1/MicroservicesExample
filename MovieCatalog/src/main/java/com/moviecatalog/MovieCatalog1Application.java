package com.moviecatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalog1Application {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatalog1Application.class, args);
	}

	@Bean // 1 instance
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		HttpComponentsClientHttpRequestFactory  clientHttpRequestFactory =new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(3000);

		return new RestTemplate(clientHttpRequestFactory);
	}
	/*public RestTemplate getRestTemplate() {

		return new RestTemplate();
	}*/
	/*@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}*/
	
	/*@Bean // 1 instance
	public WebClient.Builder getWebClientBuilder) {

		return new WebClient.Builder();
	}*/

}
