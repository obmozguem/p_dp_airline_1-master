package app.config.security.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {

    private String username;
    private String password;

}
