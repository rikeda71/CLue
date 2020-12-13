package api.clue.security;

import static api.clue.config.Constants.TOKEN_PARAM;

import api.clue.domain.User;
import api.clue.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
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

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {
    Optional<String> token = Optional.ofNullable(req.getHeader(TOKEN_PARAM));
    System.out.println(req.getHeader(TOKEN_PARAM));
    Optional<String> email = Optional.empty();
    if (token.isPresent()) {
      try {
        email = Optional.of(JwtTokenUtil.getEmailFromToken(token.get()));
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
    if (email.isPresent() && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(email.get());

      if (JwtTokenUtil.validateToken(token.get(), userDetails)) {
        User user = new User();
        user.setEmail(userDetails.getUsername());
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(user, null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        logger.info("authenticated user " + email + ", setting security context");
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    chain.doFilter(req, res);
  }

}
