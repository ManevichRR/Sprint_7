package ru.yandex.methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CommonMethod {

    @Step("Check response code")
    public static void checkResponseCode(Response response, int code) {
        response.then().statusCode(code);
    }

    @Step("Check response body field value")
    public static void checkResponseBody(Response response, String checkedField, boolean expectedValue) {
        response.then().assertThat().body(checkedField, equalTo(expectedValue));
    }

    @Step("Check response body field value")
    public static void checkResponseBody(Response response, String checkedField, String expectedValue) {
        response.then().assertThat().body(checkedField, equalTo(expectedValue));
    }

    @Step("Check response body field is not null")
    public static void checkResponseBodyNotNullField(Response response, String checkedField) {
        response.then().assertThat().body(checkedField, notNullValue());
    }


}