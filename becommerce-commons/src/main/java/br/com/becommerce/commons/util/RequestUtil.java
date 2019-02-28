package br.com.becommerce.commons.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

public class RequestUtil {

	public static String generateRequestUUID() {
		return UUID.randomUUID().toString();
	}

	public static ResponseEntity<List> doGetList(String url, String apiKey) {

		RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headers.set("api_key", apiKey);
		final HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<List> response =
				restTemplate.exchange(url, HttpMethod.GET, entity, List.class);

		return response;
	}

}
