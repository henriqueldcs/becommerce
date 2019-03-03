package br.com.becommerce.pms.resource;

import br.com.becommerce.commons.constants.MessageConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductResourceTest {

    private static final String URI_BASE = "http://localhost:5005";
    private String referenceCode = "TESTE1234";

    @Test
    public void b_listProducts() {

        String uriBase = URI_BASE + "/products";

        RestAssured.given()
                .relaxedHTTPSValidation()
                .header("api_key", "Gdu2vkyfKrzb0OdZuoPP")
                .header("requestUUID", UUID.randomUUID().toString())
                .param("page", "0")
                .param("size", "1")
                .param("referenceCode", referenceCode)
                .when()
                .get(uriBase)
                .then()
                .statusCode(200) // O status http retornado foi 200
                .contentType(ContentType.JSON) // O response foi retornado no formato JSON
                .body("referenceCode", Matchers.hasItem(referenceCode))
                .body("", Matchers.hasSize(1)); // A chave "host" possui exatamente o valor "postman-echo.com"
    }

    @Test
    public void a_addProducts() throws JSONException {

        RestAssured.baseURI = URI_BASE;
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("description", "dddddddddddddd");
        requestParams.put("name", "dddddddddddddd");
        requestParams.put("referenceCode", referenceCode);

        request.header("Content-Type", "application/json");
        request.header("api_key", "Gdu2vkyfKrzb0OdZuoPP");
        request.header("requestUUID", UUID.randomUUID().toString());

        request.body(requestParams.toString());

        Response response = request.post("/products");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(201, statusCode);
        String message = response.asString();
        Assert.assertEquals(MessageConstants.CREATE_SUCCESS_MESSAGE, message);

    }

    @Test
    public void c_updateProducts() throws JSONException {

        RestAssured.baseURI = URI_BASE;
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("description", "dddddddddddddd");
        requestParams.put("name", "dddddddddddddd");
        requestParams.put("referenceCode", referenceCode);

        request.header("Content-Type", "application/json");
        request.header("api_key", "Gdu2vkyfKrzb0OdZuoPP");
        request.header("requestUUID", UUID.randomUUID().toString());

        request.body(requestParams.toString());
        Response response = request.put("/products");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode );
        String message = response.asString();
        Assert.assertEquals(MessageConstants.UPDATE_SUCCESS_MESSAGE, message);
    }

    @Test
    public void d_deleteProducts() throws JSONException {

        RestAssured.baseURI = URI_BASE;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.header("api_key", "Gdu2vkyfKrzb0OdZuoPP");
        request.header("requestUUID", UUID.randomUUID().toString());

        Response response = request.delete("/products/" + referenceCode);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        String message = response.asString();
        Assert.assertEquals(MessageConstants.DELETE_SUCCESS_MESSAGE, message);
    }
}
