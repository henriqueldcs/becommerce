package br.com.becommerce.pms.resource;

import br.com.becommerce.commons.constants.MessageConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InventoryResourceTest {

    private static final String URI_BASE = "http://localhost:5006";
    private static final String PATH = "/inventories";
    private static final String REQUEST_UUID = UUID.randomUUID().toString();
    private static final String productReferenceCode = "p1";
    private static final String ACTION = "INCREMENT";
    private static final int PAGE = 0;
    private static final int SIZE = 1;


    private static final String PRODUCT_REF_COD_LABEL = "productReferenceCode";
    private static final String AMOUNT_LABEL = "amount";
    private static final String ACTION_LABEL = "action";
    private static final String PAGE_LABEL = "page";
    private static final String SIZE_LABEL = "size";
    private static final String VALUE_LABEL = "value";

    @Test
    public void a_addInventories() throws JSONException {

        RestAssured.baseURI = URI_BASE;
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put(PRODUCT_REF_COD_LABEL, productReferenceCode);
        requestParams.put(AMOUNT_LABEL, BigDecimal.TEN);
        setHeader(request, REQUEST_UUID);
        request.body(requestParams.toString());
        Response response = request.post(PATH);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(201, statusCode);
        String message = response.asString();
        Assert.assertEquals(MessageConstants.CREATE_SUCCESS_MESSAGE, message);

    }

    @Test
    public void b_listInventories() {

        RestAssured.baseURI = URI_BASE;
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();

        request.param(PAGE_LABEL, PAGE);
        request.param(SIZE_LABEL, SIZE);
        request.param(PRODUCT_REF_COD_LABEL, productReferenceCode);

        setHeader(request, REQUEST_UUID);

        request.body(requestParams.toString());
        Response response = request.get(PATH);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        String responseString = response.asString();
        Assert.assertTrue(responseString.contains(productReferenceCode));

    }

    @Test
    public void c_updateInventories() throws JSONException {

        RestAssured.baseURI = URI_BASE;
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put(ACTION_LABEL, ACTION);
        requestParams.put(VALUE_LABEL, BigDecimal.TEN);
        requestParams.put(PRODUCT_REF_COD_LABEL, productReferenceCode);

        setHeader(request, REQUEST_UUID);

        request.body(requestParams.toString());
        Response response = request.put(PATH);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        String responseString = response.asString();
        Assert.assertTrue(responseString.contains(MessageConstants.UPDATE_SUCCESS_MESSAGE));
    }

    private void setHeader(RequestSpecification request, String requestUuid) {

        request.header("Content-Type", "application/json");
        request.header("api_key", "Gdu2vkyfKrzb0OdZuoPP");
        request.header("requestUUID", requestUuid);
    }
}
