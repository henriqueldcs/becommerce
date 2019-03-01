package br.com.becommerce.pms.resource;

import br.com.becommerce.commons.annotation.TokenValidation;
import br.com.becommerce.commons.constants.MessageConstants;
import br.com.becommerce.pms.exception.ProductAlreadyExists;
import br.com.becommerce.pms.exception.ProductNotFound;
import br.com.becommerce.pms.model.Product;
import br.com.becommerce.pms.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductResource {

    @Autowired
    private ProductService productService;

    @TokenValidation
    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Product> listProducts(@RequestHeader(value = "api_key") final String apiKey,
                                      @RequestHeader(value = "requestUUID") final String requestUUID,
                                      @RequestParam(value = "referenceCode", required = false) final String referenceCode,
                                      @RequestParam(value = "page", required = false) final Integer page,
                                      @RequestParam(value = "size", required = false) final Integer size) {

        log.info(String.format("m=listProducts,requestUUID=%s, page=%s, size=%s, referenceCode=%s, api_key=%s",
                requestUUID, page, size, referenceCode, apiKey));

        return productService.findProductBy(referenceCode, page, size);
    }

    @TokenValidation
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> addProducts(@RequestHeader(value = "api_key") final String apiKey,
                                      @RequestHeader(value = "requestUUID") final String requestUUID,
                                      @RequestBody final Product product) {

        log.info(String.format("m=addProducts,requestUUID=%s, product=%s, api_key=%s",
                requestUUID, product, apiKey));

        try {
            productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(MessageConstants.CREATE_SUCCESS_MESSAGE);
        } catch (ProductAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @TokenValidation
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updateProduct(@RequestHeader(value = "api_key") final String apiKey,
                                              @RequestHeader(value = "requestUUID") final String requestUUID,
                                              @RequestBody final Product product) {

        log.info(String.format("m=updateProduct,requestUUID=%s, product=%s, api_key=%s",
                requestUUID, product, apiKey));

        try {
            productService.updateProduct(product);
            return ResponseEntity.status(HttpStatus.OK).body(MessageConstants.UPDATE_SUCCESS_MESSAGE);
        } catch (ProductNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
