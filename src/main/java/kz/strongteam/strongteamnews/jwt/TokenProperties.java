package kz.strongteam.strongteamnews.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenProperties {
    private String prefix;
    private String clientIp;
    private String secret;
    private String issuer;
    private String audience;
    private Integer accessExpirationTime;
    private Integer refreshExpirationTime;
    private String jwtTokenHeader;
    private String tokenCannotBeVerified;
}