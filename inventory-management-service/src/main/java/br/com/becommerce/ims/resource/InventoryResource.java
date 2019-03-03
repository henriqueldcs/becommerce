package br.com.becommerce.ims.resource;

import br.com.becommerce.commons.annotation.TokenValidation;
import br.com.becommerce.commons.constants.MessageConstants;
import br.com.becommerce.commons.to.InventoryProduct;
import br.com.becommerce.commons.to.InventoryProductAction;
import br.com.becommerce.commons.to.InventoryResponse;
import br.com.becommerce.ims.exception.InventoryAlreadyExists;
import br.com.becommerce.ims.exception.InventoryNotFound;
import br.com.becommerce.ims.model.Inventory;
import br.com.becommerce.ims.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
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
    public List<InventoryProduct> listInventories(@RequestHeader(value = "api_key") final String apiKey,
                                                  @RequestHeader(value = "requestUUID") final String requestUUID,
                                                  @RequestParam(value = "productReferenceCode", required = false) final String productReferenceCode,
                                                  @RequestParam(value = "page", required = false) final Integer page,
                                                  @RequestParam(value = "size", required = false) final Integer size) {

        log.info(String.format("m=listInventories,requestUUID=%s, page=%s, size=%s, productReferenceCode=%s, api_key=%s",
                requestUUID, page, size, productReferenceCode, apiKey));

        return inventoryService.findInventoryProductBy(productReferenceCode, page, size, apiKey, requestUUID);
    }


    @TokenValidation
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> addInventory(@RequestHeader(value = "api_key") final String apiKey,
                                              @RequestHeader(value = "requestUUID") final String requestUUID,
                                              @RequestBody final Inventory inventory) {

        log.info(String.format("m=addInventory, inventory=%s, requestUUID=%s, api_key=%s",
                requestUUID, inventory, apiKey));

        try {
            inventoryService.addInventory(inventory);
            return ResponseEntity.status(HttpStatus.CREATED).body(MessageConstants.CREATE_SUCCESS_MESSAGE);
        } catch (InventoryAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @TokenValidation
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<InventoryResponse> updateInventory(@RequestHeader(value = "api_key") final String apiKey,
                                                             @RequestHeader(value = "requestUUID") final String requestUUID,
                                                             @RequestBody final InventoryProductAction inventoryProductAction) {

        log.info(String.format("m=updateInventory, inventoryProductAction=%s, requestUUID=%s api_key=%s",
                inventoryProductAction, requestUUID, apiKey));

        try {

            var inventory = inventoryService.updateInventory(inventoryProductAction);


            return ResponseEntity.status(HttpStatus.OK).body(
                    InventoryResponse.builder().message(MessageConstants.UPDATE_SUCCESS_MESSAGE).inventory(
                            new br.com.becommerce.commons.to.Inventory()
                                    .amount(inventory.getAmount())
                                    .productReferenceCode(inventory.getProductReferenceCode())
                    ).build());
        } catch (InventoryNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(InventoryResponse.builder().message(e.getMessage()).build());
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(InventoryResponse.builder().message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(InventoryResponse.builder().message(e.getMessage()).build());
        }
    }
}
