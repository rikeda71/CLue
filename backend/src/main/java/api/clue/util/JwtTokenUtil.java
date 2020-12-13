package api.clue.util;

import static api.clue.config.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static api.clue.config.Constants.SIGNING_KEY;

import api.clue.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtTokenUtil implements Serializable {


  public static String generateToken(User user) {
    Claims claims = Jwts.claims().setSubject(user.getEmail());

    return Jwts.builder()
        .setClaims(claims)
        .setIssuer("https://s14t284.github.io/portforio/")
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
        .signWith(SignatureAlgorithm.HS256, SIGNING_KEY.getBytes())
        .compact();
  }

  public static Boolean validateToken(String token, UserDetails userDetails) {
    final String email = getEmailFromToken(token);
    // userDetailsの仕様上、usernameとpasswordを格納することになっているが、
    // usernameにemailを格納しているためこのようになっている
    return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  private static Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public static String getEmailFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  private static Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  private static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private static Claims getAllClaimsFromToken(String token) {
    System.out.println(token.replace("Bearer ", ""));
    return Jwts.parser()
        .setSigningKey(SIGNING_KEY.getBytes())
        .parseClaimsJws(token.replace("Bearer ", ""))
        .getBody();
  }

}
