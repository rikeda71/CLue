package api.clue.config;

import api.clue.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;

public class JwtTokenUtil implements Serializable {

  public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
  public static final String SIGNING_KEY = "000s14t284";

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

}
