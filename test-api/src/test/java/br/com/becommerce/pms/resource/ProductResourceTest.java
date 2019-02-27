package br.com.becommerce.pms.resource;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProductResourceTest {


    @Test
    public void listProducts() {

        String uriBase = "http://localhost:5005/products";

        RestAssured.given()
                .relaxedHTTPSValidation()
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
}
