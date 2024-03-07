package ru.yandex.test_classes;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.yandex.methods.CommonMethod;
import ru.yandex.methods.OrderMethod;

import static ru.yandex.constants.ServiceURI.URI_QA_SCOOTER;
import static ru.yandex.constants.StatusCode.CODE_200;


public class ListOrderTest {

    private String orderBodyField = "orders";

    @BeforeClass
    public static void suiteSetup() {
        RestAssured.baseURI = URI_QA_SCOOTER;
    }

    @Test
    @DisplayName("Check get order list")
    public void checkOrderList() {
        Response response = OrderMethod.sendGetRequestGetOrderList();
        CommonMethod.checkResponseCode(response, CODE_200);
        CommonMethod.checkResponseBodyNotNullField(response, orderBodyField);
    }
}
