package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-18T04:49:14.604Z")

@Controller
public class ProductsApiController implements ProductsApi {

    private static final Logger log = LoggerFactory.getLogger(ProductsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ProductsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addProduct(@ApiParam(value = "Informado para adição de um produto." ,required=true )  @Valid @RequestBody Product product, @RequestHeader(name = "api_key")  String apiKey) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Product>> listProducts(@ApiParam(value = "Número da página a ser retornada", defaultValue = "0") @Valid @RequestParam(value = "page", required = false, defaultValue="0") Integer page,@ApiParam(value = "Tamanho da página a ser retornada", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size,@ApiParam(value = "Código de referência do produto") @Valid @RequestParam(value = "referenceCode", required = false) String referenceCode, @RequestHeader(name = "api_key")  String apiKey) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Product>>(objectMapper.readValue("[ {  \"Description\" : \"Produto usado para pesca de pequenos peixes\",  \"name\" : \"Vara de pesca pequena\",  \"referenceCode\" : \"P123\"}, {  \"Description\" : \"Produto usado para pesca de pequenos peixes\",  \"name\" : \"Vara de pesca pequena\",  \"referenceCode\" : \"P123\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Product>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Product>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> removeProduct(@ApiParam(value = "Código de referência do produto a remover",required=true) @PathVariable("referenceCode") String referenceCode, @RequestHeader(name = "api_key")  String apiKey) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateProduct(@ApiParam(value = "Campos informados na adição de um produto." ,required=true )  @Valid @RequestBody Product product, @RequestHeader(name = "api_key")  String apiKey) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
