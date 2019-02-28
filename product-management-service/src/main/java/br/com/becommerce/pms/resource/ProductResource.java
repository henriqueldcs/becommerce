package br.com.becommerce.pms.resource;

import br.com.becommerce.pms.model.Product;
import br.com.becommerce.pms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Product> listProducts(@RequestHeader(value = "api_key") final String apiKey,
                                      @RequestParam(value = "referenceCode", required = false) final String referenceCode,
                                      @RequestParam(value = "page", required = false) final Integer page,
                                      @RequestParam(value = "size", required = false) final Integer size) {

        return productService.findProductBy(referenceCode, page, size);
    }

}
