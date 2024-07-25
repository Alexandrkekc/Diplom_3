package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.constants.UrlConstants.*;

public class StepsForUser {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Step("Создать пользователя")
    public Response createUser() {
        Response response =
                given().log().all()
                        .header("Content-type", "application/json")
                        .and()
                        .body(user)
                        .when()
                        .post(CREATE_URL);
        return response;
    }

    @Step("Авторизоваться пользователем")
    public Response loginUser(User user) {
        return given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(LOGIN_URL);
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken){
        return given()
                .header("Authorization",accessToken)
                .when()
                .delete(DELETE_URL);
    }
}
