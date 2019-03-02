package br.com.becommerce.api.gateway.resource;

import br.com.becommerce.api.gateway.util.RequestURL;
import br.com.becommerce.commons.annotation.TokenValidation;
import br.com.becommerce.commons.constants.MessageConstants;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static br.com.becommerce.commons.util.RequestUtil.doGetList;
import static br.com.becommerce.commons.util.RequestUtil.generateRequestUUID;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/inventories")
@Slf4j
public class APIInventoryResource {

	private static final String API_KEY = "api_key";
	private static final String PRODUCT_REFERENCE_CODE = "productReferenceCode";
	private static final String PAGE = "page";
	private static final String SIZE = "size";
	private static final String REQUEST_UUID = "requestUUID";


	@TokenValidation
	@GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List> listInventories(@RequestHeader(value = API_KEY) final String apiKey,
													  @RequestParam(value = PRODUCT_REFERENCE_CODE, required = false) final String referenceCode,
													  @RequestParam(value = PAGE, required = false) final Integer page,
													  @RequestParam(value = SIZE, required = false) final Integer size) {

		final String requestUUID = generateRequestUUID();
		final String urlWithParams = RequestURL.INVENTORY_RESOURCE + String.format("?%s=%s&%s=%s&%s=%s&%s=%s",
				REQUEST_UUID,requestUUID, //
				PRODUCT_REFERENCE_CODE, Strings.nullToEmpty(referenceCode), //
				PAGE, page != null ? page : "", //
				SIZE, size != null ? size : "" //
		);


		log.info(String.format("m=listInventories,requestUUID=%s, page=%s, size=%s, referenceCode=%s, api_key=%s, url=%s",
				requestUUID, page, size, referenceCode, apiKey, urlWithParams));

		final ResponseEntity<List> response = doGetList(urlWithParams, Map.of(API_KEY, apiKey, REQUEST_UUID, requestUUID));

		if(Objects.isNull(response) || response.getBody().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Arrays.asList(MessageConstants.INVENTORY_NOT_FOUND));
		}

		return response;
	}

}
