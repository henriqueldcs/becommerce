package br.com.becommerce.pms.resource;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
public class ProductResourceTest {


    @Test
    public void listProducts() {

        String uriBase = "http://localhost:5005/products";

        RestAssured.given()
                .relaxedHTTPSValidation()
                .header("api_key", "Gdu2vkyfKrzb0OdZuoPP")
                .header("requestUUID", UUID.randomUUID().toString())
                .param("page", "0")
                .param("size", "1")
                .param("referenceCode", "p1")
                .when()
                .get(uriBase)
                .then()
                .statusCode(200) // O status http retornado foi 200
                .contentType(ContentType.JSON) // O response foi retornado no formato JSON
                .body("name", Matchers.hasItem("pro1"))
                .body("", Matchers.hasSize(1)); // A chave "host" possui exatamente o valor "postman-echo.com"

    }

    @Test
    public void addProducts() throws JSONException {

        final String SUCCESS_MESSAGE = "Produto cadastrado com sucesso!";
        final String uriBase = "http://localhost:5005";

        RestAssured.baseURI = uriBase;
        RequestSpecification request = RestAssured.given();
        String uuid = UUID.randomUUID().toString();

        JSONObject requestParams = new JSONObject();
        requestParams.put("description", "dddddddddddddd");
        requestParams.put("name", "dddddddddddddd");
        requestParams.put("referenceCode", uuid);

        request.header("Content-Type", "application/json");
        request.header("api_key", "Gdu2vkyfKrzb0OdZuoPP");
        request.header("requestUUID", uuid);

        request.body(requestParams.toString());

        Response response = request.post("/products");


        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
        String message = response.asString();
        Assert.assertEquals( SUCCESS_MESSAGE, message);

    }

}
