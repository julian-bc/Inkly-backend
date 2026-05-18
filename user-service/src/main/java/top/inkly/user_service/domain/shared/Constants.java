package top.inkly.user_service.domain.shared;

public class Constants {

    // KEYCLOAK Constants
    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String SCOPE = "scope";
    public static final String OPEN_ID = "openid";
    // Login Response
    public static final String BODY_ACCESS_TOKEN = "access_token";
    public static final String BODY_REFRESH_TOKEN = "refresh_token";
    public static final String BODY_TOKEN = "token";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String REFRESH_TOKEN = "refreshToken";
    // Validation Response
    public static final String ACTIVE = "active";
    public static final String SUB = "sub";

    // COOKIES Constants
    public static final boolean HTTP_ONLY = true;
    public static final String SAME_SITE_DEV = "Lax";
    public static final String NONE_SITE_PROD = "None";
    public static final boolean COOKIE_SECURE_DEV = false;
    public static final String COOKIE_PATH = "/";
    public static final Integer COOKIE_ACCESS_TOKEN_DURATION = 15;
    public static final Integer COOKIE_REFRESH_TOKEN_DURATION = 7;
    public static final String DEV = "DEV";
    public static final String INT = "INT";
    public static final String AUS = "AUS";
    public static final String PROD = "PROD";

    // UTIL Constants
    public static final String BLANK = "";
    public static final Integer ZERO = 0;

}
