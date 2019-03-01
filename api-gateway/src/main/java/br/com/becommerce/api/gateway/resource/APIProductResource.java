package br.com.becommerce.api.gateway.resource;

import br.com.becommerce.api.gateway.util.RequestURL;
import br.com.becommerce.commons.annotation.TokenValidation;
import br.com.becommerce.commons.to.Product;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static br.com.becommerce.commons.util.RequestUtil.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/products")
@Slf4j
public class APIProductResource {

	private static final String API_KEY = "api_key";
	private static final String REFERENCE_CODE = "referenceCode";
	private static final String PAGE = "page";
	private static final String SIZE = "size";
	private static final String REQUEST_UUID = "requestUUID";


	@TokenValidation
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List> listProducts(@RequestHeader(value = API_KEY) final String apiKey,
													  @RequestParam(value = REFERENCE_CODE, required = false) final String referenceCode,
													  @RequestParam(value = PAGE, required = false) final Integer page,
													  @RequestParam(value = SIZE, required = false) final Integer size) {

		final String requestUUID = generateRequestUUID();
		final String urlWithParams = RequestURL.PRODUCT_RESOURCE + String.format("?%s=%s&%s=%s&%s=%s&%s=%s",
				REQUEST_UUID,requestUUID, //
				REFERENCE_CODE, Strings.nullToEmpty(referenceCode), //
				PAGE, page != null ? page : "", //
				SIZE, size != null ? size : "" //
		);


		log.info(String.format("m=listProducts,requestUUID=%s, page=%s, size=%s, referenceCode=%s, api_key=%s, url=%s",
				requestUUID, page, size, referenceCode, apiKey, urlWithParams));

		final ResponseEntity<List> response = doGetList(urlWithParams, Map.of(API_KEY, apiKey, REQUEST_UUID, requestUUID));

		if(Objects.isNull(response) || response.getBody().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Arrays.asList("Produto não encontrado!"));
		}

		return response;
	}


	@TokenValidation
	@PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> addProducts(@RequestHeader(value = "api_key") final String apiKey,
											  @RequestBody final Product product) {

		final String requestUUID = generateRequestUUID();
		final String url = RequestURL.PRODUCT_RESOURCE;

		log.info(String.format("m=addProducts,requestUUID=%s, product=%s, api_key=%s, url=%s",
				requestUUID, product, apiKey, url));

		try {
			return doPost(url, Map.of(API_KEY, apiKey, REQUEST_UUID, requestUUID), product);

		} catch (HttpClientErrorException e) {

			log.error(String.format("m=addProducts,requestUUID=%s, message=%s", requestUUID, e.getResponseBodyAsString()));
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());

		} catch (Exception e) {

			log.error(String.format("m=addProducts,requestUUID=%s, message=%s", requestUUID, e.getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@TokenValidation
	@PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateProducts(@RequestHeader(value = "api_key") final String apiKey,
											  @RequestBody final Product product) {

		final String requestUUID = generateRequestUUID();
		final String url = RequestURL.PRODUCT_RESOURCE;

		log.info(String.format("m=updateProducts,requestUUID=%s, product=%s, api_key=%s, url=%s",
				requestUUID, product, apiKey, url));

		try {
			return doPut(url, Map.of(API_KEY, apiKey, REQUEST_UUID, requestUUID), product);

		} catch (HttpStatusCodeException e) {

			log.error(String.format("m=updateProducts,requestUUID=%s, message=%s", requestUUID, e.getResponseBodyAsString()));
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());

		} catch (Exception e) {

			log.error(String.format("m=updateProducts,requestUUID=%s, message=%s", requestUUID, e.getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@TokenValidation
	@DeleteMapping("{referenceCode}")
	public ResponseEntity<String> deleteProducts(@RequestHeader(value = "api_key") final String apiKey,
												 @PathVariable final String referenceCode) {

		final String requestUUID = generateRequestUUID();
		final String url = RequestURL.PRODUCT_RESOURCE + "/" + referenceCode;

		log.info(String.format("m=deleteProducts,requestUUID=%s, product=%s, api_key=%s, url=%s",
				requestUUID, referenceCode, apiKey, url));

		try {
			return doDelete(url, Map.of(API_KEY, apiKey, REQUEST_UUID, requestUUID));

		} catch (HttpStatusCodeException e) {

			log.error(String.format("m=deleteProducts,requestUUID=%s, message=%s", requestUUID, e.getResponseBodyAsString()));
			return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());

		} catch (Exception e) {

			log.error(String.format("m=deleteProducts,requestUUID=%s, message=%s", requestUUID, e.getMessage()));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
