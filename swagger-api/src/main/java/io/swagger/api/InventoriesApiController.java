package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Inventory;
import io.swagger.model.InventoryProduct;
import io.swagger.model.InventoryProductAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
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

    public ResponseEntity<List<InventoryProduct>> listInventoryProducts(@ApiParam(value = "Número da página a ser retornada", defaultValue = "0") @Valid @RequestParam(value = "page", required = false, defaultValue="0") Integer page,@ApiParam(value = "Tamanho da página a ser retornada", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size,@ApiParam(value = "Código de referência do produto em estoque") @Valid @RequestParam(value = "productReferenceCode", required = false) String productReferenceCode, @RequestHeader(name = "api_key")  String apiKey) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<InventoryProduct>>(objectMapper.readValue("[ {  \"product\" : {    \"Description\" : \"Produto usado para pesca de pequenos peixes\",    \"name\" : \"Vara de pesca pequena\",    \"referenceCode\" : \"P123\"  },  \"amount\" : 7.5}, {  \"product\" : {    \"Description\" : \"Produto usado para pesca de pequenos peixes\",    \"name\" : \"Vara de pesca pequena\",    \"referenceCode\" : \"P123\"  },  \"amount\" : 7.5} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<InventoryProduct>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<InventoryProduct>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateInventoryProduct(@ApiParam(value = "Campos informados na adição de um produto." ,required=true )  @Valid @RequestBody InventoryProductAction inventoryProductAction, @RequestHeader(name = "api_key")  String apiKey) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
