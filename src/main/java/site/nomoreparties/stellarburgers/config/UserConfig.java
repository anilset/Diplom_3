package site.nomoreparties.stellarburgers.config;

import org.apache.commons.lang3.RandomStringUtils;

public class UserConfig {
    public static final String URL = "https://stellarburgers.nomoreparties.site/";
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
}
