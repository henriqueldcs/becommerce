package br.com.becommerce.pms.resource;

import br.com.becommerce.commons.annotation.TokenValidation;
import br.com.becommerce.pms.model.Product;
import br.com.becommerce.pms.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                                      @RequestParam(value = "referenceCode", required = false) final String referenceCode,
                                      @RequestParam(value = "page", required = false) final Integer page,
                                      @RequestParam(value = "size", required = false) final Integer size,
                                      @RequestParam(value = "requestUUID") final String requestUUID) {

        log.info(String.format("m=listProducts,requestUUID=%s, page=%s, size=%s, referenceCode=%s, api_key=%s",
                requestUUID, page, size, referenceCode, apiKey));

        return productService.findProductBy(referenceCode, page, size);
    }

}
