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
    public void lista() {

        String uriBase = "https://postman-echo.com/get";

        RestAssured.given()
                .relaxedHTTPSValidation()
                .param("foo1", "bar1")
                .param("foo2", "bar2")
                .when()
                .get(uriBase)
                .then()
                .statusCode(200) // O status http retornado foi 200
                .contentType(ContentType.JSON) // O response foi retornado no formato JSON
                .body("headers.host", Matchers.is("postman-echo.com")) // A chave "host" possui exatamente o valor "postman-echo.com"
                .body("args.foo1", Matchers.containsString("bar")); //A chave "foo1" contém o valor "bar"

    }



    @Test
    public void Teste() {

        String uriBase = "https://postman-echo.com/get";

        RestAssured.given()
                .relaxedHTTPSValidation()
                .param("foo1", "bar1")
                .param("foo2", "bar2")
                .when()
                .get(uriBase)
                .then()
                .statusCode(200) // O status http retornado foi 200
                .contentType(ContentType.JSON) // O response foi retornado no formato JSON
                .body("headers.host", Matchers.is("postman-echo.com")) // A chave "host" possui exatamente o valor "postman-echo.com"
                .body("args.foo1", Matchers.containsString("bar")); //A chave "foo1" contém o valor "bar"

    }

}
