package api.clue.util;

import java.util.Base64;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

public class CookieUtil {

  /**
   * Cookieの中から指定した値を取得
   *
   * @param request リクエストパラメータ
   * @param name cookieの中から取得したい値
   * @return cookie
   */
  public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
    var cookies = Optional.ofNullable(request.getCookies());

    if (cookies.isEmpty()) {
      return Optional.empty();
    }
    for (Cookie cookie : cookies.get()) {
      if (cookie.getName().equals(name)) {
        return Optional.of(cookie);
      }
    }
    return Optional.empty();
  }

  /**
   * cookieをレスポンスに追加
   *
   * @param response レスポンスオブジェクト
   * @param name cookieのkey
   * @param value cookieのvalue
   * @param maxAge どれだけcookieを保持するか
   */
  public static void addCookie(HttpServletResponse response, String name, String value,
      int maxAge) {
    Cookie cookie = new Cookie(name, value);
    cookie.setPath("/");
    cookie.setMaxAge(maxAge);
    response.addCookie(cookie);
  }

  public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
      String name) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(name)) {
          cookie.setValue("");
          cookie.setPath("/");
          cookie.setMaxAge(0);
          response.addCookie(cookie);
        }
      }
    }
  }

  public static String serialize(Object object) {
    return Base64.getUrlEncoder()
        .encodeToString(SerializationUtils.serialize(object));
  }

  public static <T> T deserialize(Cookie cookie, Class<T> cls) {
    return cls.cast(SerializationUtils.deserialize(
        Base64.getUrlDecoder().decode(cookie.getValue())));
  }


}
