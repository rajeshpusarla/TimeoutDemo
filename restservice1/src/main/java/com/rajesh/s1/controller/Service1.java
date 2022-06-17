package com.rajesh.s1.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/s1")
public class Service1 {
	private RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
	ResponseEntity<String> response;

	@PostMapping(path = "/send")
	public String sendMessage(@RequestBody String msg) {

		/*
		 * try { HttpEntity<String> request = new HttpEntity<>(msg); response =
		 * restTemplate.exchange("http://localhost:8082/s2/ping", HttpMethod.GET,
		 * request, String.class); if (response.getStatusCode().equals(HttpStatus.OK)) {
		 * System.out.println("message sent successfully" ); return response.getBody();
		 * } }catch (Exception e) { System.out.println("message not sent successfully"
		 * ); }
		 */
		
		try {
			HttpEntity<String> request = new HttpEntity<>(msg);
			response = restTemplate.exchange("http://localhost:8082/s2/receive", HttpMethod.POST, request, String.class);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				return "message sent successfully" ;
			} 	
		}catch (Exception e) {
			return  "message not sent successfully" + HttpStatus.BAD_REQUEST;
		}
		return null;

	}

	// Override timeouts in request factory
	private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		// Connect timeout
		clientHttpRequestFactory.setConnectTimeout(10_000);

		// Read timeout
		clientHttpRequestFactory.setReadTimeout(10_000);
		return clientHttpRequestFactory;
	}

	@GetMapping("/ping")
	public String test() {
		return "s1 ready ...";
	}
}
