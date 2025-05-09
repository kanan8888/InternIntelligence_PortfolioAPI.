package az.portfolioapi.util.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {

    private static final String REFRESH_TOKEN_NAME = "refresh_token";
    private static final int COOKIE_MAX_AGE = 7 * 24 * 60 * 60; // 7 days

    public static void addRefreshToken(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(REFRESH_TOKEN_NAME, token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // prod-da true
        cookie.setPath("/api//v1auth/refresh");
        cookie.setMaxAge(COOKIE_MAX_AGE);
        response.addCookie(cookie);
    }

    public static String getRefreshToken(HttpServletRequest request) {
        if (request.getCookies() == null) return null;
        for (Cookie cookie : request.getCookies()) {
            if (REFRESH_TOKEN_NAME.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static void deleteRefreshToken(HttpServletResponse response) {
        Cookie cookie = new Cookie(REFRESH_TOKEN_NAME, null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // prod-da true
        cookie.setPath("/api/v1/auth/refresh");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
