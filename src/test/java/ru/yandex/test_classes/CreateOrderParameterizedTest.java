package ru.yandex.test_classes;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.Order;
import ru.yandex.methods.CommonMethod;

import java.util.List;

import static ru.yandex.constants.ServiceURI.URI_QA_SCOOTER;
import static ru.yandex.constants.StatusCode.CODE_201;
import static ru.yandex.methods.OrderMethod.sendPostRequestCreateOrder;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest {

    // Test data
    private final String orderBodyFieldTrack = "track";

    public CreateOrderParameterizedTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {

    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Kazimir", "Malevich", "Wallstreet 1", 1, "+79587", 5, "2023-10-30", "comment1", List.of("BLACK")},
                {"Kazimir", "Malevich", "Wallstreet 1", 1, "+79587", 5, "2023-10-30", "comment2", List.of("GREY")},
                {"Kazimir", "Malevich", "Wallstreet 1", 1, "+79587", 5, "2023-10-30", "comment3", List.of("BLACK", "GREY")},
                {"Roman", "Manevich", "Wallstreet 1", 1, "+79587", 5, "2023-10-30", "comment4", List.of()},
        };
    }

    @Test
    @DisplayName("Check create order (parameterized)")
    public void createOrderTest() {
        RestAssured.baseURI = URI_QA_SCOOTER;
        Order order = new Order();
        Response response = sendPostRequestCreateOrder(order);
        CommonMethod.checkResponseCode(response, CODE_201);
        CommonMethod.checkResponseBodyNotNullField(response, orderBodyFieldTrack);
    }

}