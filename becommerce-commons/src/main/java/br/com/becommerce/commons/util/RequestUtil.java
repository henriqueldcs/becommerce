package br.com.becommerce.commons.util;

import br.com.becommerce.commons.to.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RequestUtil {

	public static String generateRequestUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Chama via GET a url passada como parâmetro e retorna uma lista genérica.
	 * @param url url para executar get.
	 * @param headerParams map com chave e valor de dados para o cabeçalho.
	 * @return {@link ResponseEntity} com a lista de itens de retorno.
	 */
	public static ResponseEntity<List> doGetList(String url, Map<String, String> headerParams) {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List> response =
				restTemplate.exchange(url, HttpMethod.GET, createHttpEntityWithHeader(headerParams), List.class);

		return response;
	}

	/**
	 * Chama API e retorna lista de produtos.
	 * @param url url para executar get.
	 * @param headerParams map com chave e valor de dados para o cabeçalho.
	 * @return {@link ResponseEntity<List<Product>>} com a lista de produtos de retorno.
	 */
	public static ResponseEntity<List<Product>> doGetListProduct(String url, Map<String, String> headerParams) {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Product>> response =
				restTemplate.exchange(url, HttpMethod.GET,
						createHttpEntityWithHeader(headerParams), new ParameterizedTypeReference<List<Product>>(){});

		return response;
	}

	/**
	 * Cria {@link HttpEntity} com parametros enviados para o header.
	 * @param headerParams map com chave e valor de dados para o cabeçalho.
	 * @return {@link HttpEntity<String>} criado
	 */
	private static HttpEntity<String> createHttpEntityWithHeader(Map<String, String> headerParams) {

		final HttpHeaders headers = new HttpHeaders();
		headerParams.forEach((key,value) -> {
					if(key != null && value != null)
						headers.set(key,value);
				}
		);
		return new HttpEntity<>(headers);
	}

	/**
	 * Executa post para url passada como parâmetro.
	 * @param url url para executar post.
	 * @param headerParams map com chave e valor de dados para o cabeçalho.
	 * @param product Produto a ser enviado.
	 * @return {@link ResponseEntity<String>} com retorno da requisição.
	 */
	public static ResponseEntity<String> doPost(String url, Map<String, String> headerParams, Object product) {

		RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headerParams.forEach((key,value) -> {
					if(key != null && value != null)
						headers.set(key,value);
				}
		);
		final HttpEntity<Object> entity = new HttpEntity<>(product, headers);

		return restTemplate.postForEntity(url, entity, String.class);
	}

	/**
	 * Executa put para url passada como parâmetro.
	 * @param url url para executar put.
	 * @param headerParams map com chave e valor de dados para o cabeçalho.
	 * @param product Produto a ser enviado.
	 * @return {@link ResponseEntity<String>} com retorno da requisição.
	 */
	public static ResponseEntity<String> doPut(String url, Map<String, String> headerParams, Object product) {

		RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headerParams.forEach((key,value) -> {
					if(key != null && value != null)
						headers.set(key,value);
				}
		);
		final HttpEntity<Object> entity = new HttpEntity<>(product, headers);

		return restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
	}


	/**
	 * Executa delete para url passada como parâmetro.
	 * @param url url para executar delete.
	 * @param headerParams map com chave e valor de dados para o cabeçalho.
	 * @return {@link ResponseEntity<String>} com retorno da requisição.
	 */
	public static ResponseEntity<String> doDelete(String url, Map<String, String> headerParams) {

		RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headerParams.forEach((key,value) -> {
					if(key != null && value != null)
						headers.set(key,value);
				}
		);
		final HttpEntity<Object> entity = new HttpEntity<>(headers);
		return restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
	}
}
