package app.config.security.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshJwtRequest {

    public String refreshToken;

}
