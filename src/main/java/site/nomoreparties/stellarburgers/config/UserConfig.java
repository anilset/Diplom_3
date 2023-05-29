package site.nomoreparties.stellarburgers.config;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import site.nomoreparties.stellarburgers.pojo.AuthResponse;
import site.nomoreparties.stellarburgers.pojo.User;

import static io.restassured.RestAssured.given;

public class UserConfig {
    public static final String URL = "https://stellarburgers.nomoreparties.site/";
    public static final String REGISTER_PATH = "auth/register";
    public static final String USER_PATH = "auth/user";
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/api/";

    public static String getRandomLogin() {
        String login = RandomStringUtils.randomAlphanumeric(6) + "@yandex.ru";
        String loginToLC = login.toLowerCase();
        return loginToLC;
    }

    public static String getRandomPwd() {
        return RandomStringUtils.randomNumeric(6);
    }

    public static String getRandomString(int count) {
        return RandomStringUtils.randomAlphabetic(count);
    }
    public static ValidatableResponse createUser(String login, String password, String name){
        return given()
                .contentType(ContentType.JSON)
                .and()
                .body(new User(login, password, name))
                .when()
                .post(REGISTER_PATH)
                .then();
    }

    public static ValidatableResponse deleteUser(String accessToken){
        return given()
                .contentType(ContentType.JSON)
                .auth().oauth2(accessToken)
                .contentType(ContentType.JSON)
                .when()
                .delete(USER_PATH)
                .then();
    }

    public static String getAccessToken(ValidatableResponse response) {
        AuthResponse authResponse = response.extract().body().as(AuthResponse.class);
        String[] bearerToken = authResponse.getAccessToken().split(" ");
        return bearerToken[1];
    }
}
