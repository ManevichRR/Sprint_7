package ru.yandex.test_classes;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.yandex.Courier;
import ru.yandex.methods.CommonMethod;
import ru.yandex.methods.CourierMethod;

import static ru.yandex.constants.ServiceURI.URI_QA_SCOOTER;
import static ru.yandex.constants.StatusCode.*;


public class CreateCourierTest {

    static Courier courier;
    static Courier courierDouble;

    //Create courier test
    private static final String courierLogin = "manro9090";
    private static final String courierPassword = "password";
    private static final String courierName  = "Roman";
    private static final String courierBodyFieldOk = "ok";
    private static final Boolean courierBodyValueTrue = true;

    //Create courier double test
    private static final String courierLoginDouble = "double_co222";
    private static final String courierBodyFieldMessageDouble = "message";
    private static final String courierBodyValueMessageDouble = "Этот логин уже используется. Попробуйте другой.";

    //Create courier without login test
    private static final String courierJsonWithoutLoginField = "{\"password\": \"password\", \"firstName\": \"John\"}";
    private static final String courierBodyFieldMessageWithoutLoginField  = "message";
    private static final String courierBodyValueMessageWithoutLoginField  = "Недостаточно данных для создания учетной записи";

    @BeforeClass
    public static void suiteSetup() {
        RestAssured.baseURI = URI_QA_SCOOTER;
        courier = new Courier(courierLogin, courierPassword, courierName);
        courierDouble = new Courier(courierLoginDouble, courierPassword, courierName);
    }

    @Test
    @DisplayName("Check successful create courier")
    public void checkCreateCourier() {
        Response response = CourierMethod.sendPostRequestCreateCourier(courier);
        CommonMethod.checkResponseCode(response, CODE_201);
        CommonMethod.checkResponseBody(response, courierBodyFieldOk, courierBodyValueTrue);
    }

    @Test
    @DisplayName("Check create duplicate courier")
    public void checkCreateCourierDouble() {
        Response response = CourierMethod.sendPostRequestCreateCourier(courierDouble);
        CommonMethod.checkResponseCode(response, CODE_201);
        Response responseCourierDouble = CourierMethod.sendPostRequestCreateCourier(courierDouble);
        CommonMethod.checkResponseCode(responseCourierDouble, CODE_409);
        CommonMethod.checkResponseBody(responseCourierDouble, courierBodyFieldMessageDouble, courierBodyValueMessageDouble);
    }

    @Test
    @DisplayName("Check create courier without field 'login'")
    public void checkCreateCourierWithoutLoginField() {
        Response response = CourierMethod.sendPostRequestCreateCourier(courierJsonWithoutLoginField);
        CommonMethod.checkResponseCode(response, CODE_400);
        CommonMethod.checkResponseBody(response, courierBodyFieldMessageWithoutLoginField, courierBodyValueMessageWithoutLoginField);
    }

    @AfterClass
    public static void suiteTeardown() {
        CourierMethod.loginAndDeleteCourier(courier);
        CourierMethod.loginAndDeleteCourier(courierDouble);
    }

}
