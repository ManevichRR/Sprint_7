package methods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.Courier;


import static io.restassured.RestAssured.given;
import static ru.yandex.constants.Header.APPLICATION_JSON;
import static ru.yandex.constants.Header.CONTENT_TYPE;
import static ru.yandex.constants.StatusCode.CODE_200;

public class CourierMethod {

    public static final String COURIER_API_CREATE = "/api/v1/courier";
    public static final String COURIER_API_DELETE = "/api/v1/courier/";
    public static final String COURIER_API_LOGIN =  "/api/v1/courier/login";

    @Step("Send POST create courier")
    public static Response sendPostRequestCreateCourier(Courier courier) {
        return given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(courier).when().post(COURIER_API_CREATE);
    }

    @Step("Send POST create courier")
    public static Response sendPostRequestCreateCourier(String json) {
        return given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(json).when().post(COURIER_API_CREATE);
    }

    @Step("Send DELETE delete courier")
    public static Response sendDeleteRequestDeleteCourier(Courier courier, int courierId) {
        return given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(courier).when().delete(COURIER_API_DELETE+courierId);
    }

    @Step("Send POST login courier")
    public static Response sendPostRequestLoginCourier(Courier courier) {
        return given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(courier).when().post(COURIER_API_LOGIN);
    }

    @Step("Send POST login courier")
    public static Response sendPostRequestLoginCourier(String json) {
        return given().header(CONTENT_TYPE, APPLICATION_JSON).and().body(json).when().post(COURIER_API_LOGIN);
    }

    @Step("Send login and delete requests")
    public static void loginAndDeleteCourier(Courier courier) {
        Response responseCourierLogin = sendPostRequestLoginCourier(courier);
        Courier courierToDelete = responseCourierLogin.body().as(Courier.class);
        Response responseDeletedCourier = sendDeleteRequestDeleteCourier(courierToDelete, courierToDelete.getId());
        CommonMethod.checkResponseCode(responseDeletedCourier, CODE_200);
    }

}
