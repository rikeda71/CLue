package api.clue.domain;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.security.user")
public class AuthInfo {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String password;

}
