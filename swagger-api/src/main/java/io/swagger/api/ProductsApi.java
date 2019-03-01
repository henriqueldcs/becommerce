/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import br.com.becommerce.commons.to.Product;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-18T04:49:14.604Z")

@Api(value = "products", description = "the products API")
public interface ProductsApi {

    @ApiOperation(value = "Cadastrar um novo produto.", nickname = "addProduct", notes = "Recurso usado para fazer a adição de novos produtos ao sistema.", authorizations = {
            @Authorization(value = "api_key")
    }, tags = {"Produtos",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto Criado"),
            @ApiResponse(code = 401, message = "Token inválido"),
            @ApiResponse(code = 403, message = "Produto já cadastrado no sistema")})
    @RequestMapping(value = "/products",
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<String> addProduct(@ApiParam(value = "Informado para adição de um produto.", required = true) @Valid @RequestBody Product product, @RequestHeader(name = "api_key")  String apiKey);


    @ApiOperation(value = "Listar produtos cadastrados.", nickname = "listProducts", notes = "Recurso usado para listar produtos do sistema.", response = Product.class, responseContainer = "List", authorizations = {
            @Authorization(value = "api_key")
    }, tags={ "Produtos", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Product.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Token inválido"),
            @ApiResponse(code = 404, message = "Produto não encontrado") })
    @RequestMapping(value = "/products",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List> listProducts(
            @ApiParam(value = "Número da página a ser retornada", defaultValue = "0") @Valid @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @ApiParam(value = "Tamanho da página a ser retornada", defaultValue = "10") @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @ApiParam(value = "Código de referência do produto") @Valid @RequestParam(value = "referenceCode", required = false) String referenceCode,
            @RequestHeader(name = "api_key")  String apiKey);


    @ApiOperation(value = "Remover um produto do sistema", nickname = "removeProduct", notes = "", authorizations = {
            @Authorization(value = "api_key")
    }, tags = {"Produtos",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Produto removido"),
            @ApiResponse(code = 401, message = "Token inválido"),
            @ApiResponse(code = 403, message = "Não foi possível remover o produto")})
    @RequestMapping(value = "/products",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> removeProduct(@ApiParam(value = "Código de referência do produto a remover", required = true) @PathVariable("referenceCode") String referenceCode, @RequestHeader(name = "api_key")  String apiKey);


    @ApiOperation(value = "Alterar um produto cadastrado.", nickname = "updateProduct", notes = "Recurso usado para fazer a alteração de produtos do sistema.", authorizations = {
            @Authorization(value = "api_key")
    }, tags = {"Produtos",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Produto alterado"),
            @ApiResponse(code = 401, message = "Token inválido"),
            @ApiResponse(code = 404, message = "Produto não encontrado")})
    @RequestMapping(value = "/products",
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<String> updateProduct(@ApiParam(value = "Campos informados na adição de um produto.", required = true) @Valid @RequestBody Product product, @RequestHeader(name = "api_key")  String apiKey);

}
