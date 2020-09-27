package api.clue.security;

import static api.clue.config.Constants.TOKEN_PARAM;

import api.clue.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final UserDetailsService userDetailsService;

  public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @SuppressWarnings("checkstyle:WhitespaceAround")
  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
    String token = req.getHeader(TOKEN_PARAM);
    String email = null;
    if (token != null) {
      try {
        email = JwtTokenUtil.getEmailFromToken(token);
      } catch (IllegalArgumentException e) {
        logger.error("an error occurred during getting username from token", e);
      } catch (ExpiredJwtException e) {
        logger.warn("the token is expired and not valid anymore", e);
      } catch (SignatureException e) {
        logger.error("Authentication Failed. Username or Password not valid.");
      }
    } else {
      logger.warn("couldn't find bearer string, will ignore the header");
    }
    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      UserDetails userDetails = userDetailsService.loadUserByUsername(email);

      if (JwtTokenUtil.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(userDetails, null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        logger.info("authenticated user " + email + ", setting security context");
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    chain.doFilter(req, res);
  }

}
