package br.com.becommerce.commons.util;

import br.com.becommerce.commons.to.Product;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RequestUtil {

	public static String generateRequestUUID() {
		return UUID.randomUUID().toString();
	}

	public static ResponseEntity<List> doGetList(String url, Map<String, String> headerParams) {

		RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headerParams.forEach((key,value) -> {
					if(key != null && value != null)
						headers.set(key,value);
				}
		);

		final HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<List> response =
				restTemplate.exchange(url, HttpMethod.GET, entity, List.class);

		return response;
	}

	public static ResponseEntity<String> doPost(String url, Map<String, String> headerParams, Product product) {

		RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headerParams.forEach((key,value) -> {
					if(key != null && value != null)
						headers.set(key,value);
				}
		);
		final HttpEntity<Product> entity = new HttpEntity<>(product, headers);

		return restTemplate.postForEntity(url, entity, String.class);
	}


}
