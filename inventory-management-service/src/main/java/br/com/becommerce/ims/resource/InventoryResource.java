package br.com.becommerce.ims.resource;

import br.com.becommerce.commons.annotation.TokenValidation;
import br.com.becommerce.ims.model.Inventory;
import br.com.becommerce.ims.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/inventories")
@Slf4j
public class InventoryResource {

    @Autowired
    private InventoryService inventoryService;

    @TokenValidation
    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Inventory> listInventories(@RequestHeader(value = "api_key") final String apiKey,
                                        @RequestHeader(value = "requestUUID") final String requestUUID,
                                        @RequestParam(value = "productReferenceCode", required = false) final String productReferenceCode,
                                        @RequestParam(value = "page", required = false) final Integer page,
                                        @RequestParam(value = "size", required = false) final Integer size) {

        log.info(String.format("m=listInventories,requestUUID=%s, page=%s, size=%s, productReferenceCode=%s, api_key=%s",
                requestUUID, page, size, productReferenceCode, apiKey));

        return inventoryService.findInventoryBy(productReferenceCode, page, size);
    }
}
