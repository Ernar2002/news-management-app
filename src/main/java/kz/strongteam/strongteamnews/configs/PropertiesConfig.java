package kz.strongteam.strongteamnews.configs;

import kz.strongteam.strongteamnews.jwt.TokenProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class PropertiesConfig {

    private final Environment environment;

    @Bean
    public TokenProperties tokenProperties() {
        return new TokenProperties(
                environment.getRequiredProperty("security.token.prefix"),
                environment.getRequiredProperty("security.token.client-ip"),
                environment.getRequiredProperty("security.token.secret"),
                environment.getRequiredProperty("security.token.issuer"),
                environment.getRequiredProperty("security.token.audience"),
                environment.getRequiredProperty("security.token.access-expiration-time", Integer.class),
                environment.getRequiredProperty("security.token.refresh-expiration-time", Integer.class),
                environment.getRequiredProperty("security.token.jwt-token-header"),
                environment.getRequiredProperty("security.token.token-cannot-be-verified")
        );
    }
}