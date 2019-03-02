package io.swagger.api;

import br.com.becommerce.commons.to.Inventory;
import br.com.becommerce.commons.to.InventoryProductAction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.swagger.annotations.ApiParam;
import io.swagger.util.ApiGatewayURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static br.com.becommerce.commons.util.RequestUtil.doGetList;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-18T04:49:14.604Z")

@Controller
public class InventoriesApiController implements InventoriesApi {

    private static final Logger log = LoggerFactory.getLogger(InventoriesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public InventoriesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addInventoryProduct(@ApiParam(value = "Campos informados na adição de um produto ao estoque." ,required=true )  @Valid @RequestBody Inventory inventory, @RequestHeader(name = "api_key")  String apiKey) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List> listInventoryProducts(@ApiParam(value = "Número da página a ser retornada", defaultValue = "0") @Valid @RequestParam(value = "page", required = false, defaultValue="0") Integer page, @ApiParam(value = "Tamanho da página a ser retornada", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size, @ApiParam(value = "Código de referência do produto em estoque") @Valid @RequestParam(value = "productReferenceCode", required = false) String productReferenceCode, @RequestHeader(name = "api_key")  String apiKey) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {

            String urlWithParams = ApiGatewayURL.INVENTORY_RESOURCE + String.format("?%s=%s&%s=%s&%s=%s",
                    "productReferenceCode", Strings.nullToEmpty(productReferenceCode), //
                    "page", page != null ? page : "", //
                    "size", size != null ? size : "" //
            );

            try {
                return doGetList(urlWithParams, Map.of("api_key", apiKey));
            } catch (HttpClientErrorException e) {
                return ResponseEntity.status(e.getStatusCode()).body(Arrays.asList(e.getResponseBodyAsString()));
            }

        }

        return new ResponseEntity<List>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Void> updateInventoryProduct(@Valid InventoryProductAction inventoryProductAction, String apiKey) {
        return null;
    }

}
