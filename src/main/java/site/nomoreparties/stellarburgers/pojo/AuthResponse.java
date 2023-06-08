package site.nomoreparties.stellarburgers.pojo;

public class AuthResponse {
    public Boolean success;
    public String accessToken;
    public String refreshToken;
    public User user;

    public AuthResponse() {
    }

    public Boolean isSuccessful() {
        return success;
    }

    public String getAccessToken() {
        return accessToken;
    }


    public String getRefreshToken() {
        return refreshToken;
    }

    public User getUser() {
        return user;
    }

}
