package kz.strongteam.strongteamnews.configs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Getter
@Component
public class SecurityProperties {

    private final String[] NOT_AUTHENTICATED = {
            "/api/v1/auth/login",
            "/api/v1/auth/register"
    };

    private final String[] ADMIN_URLS = {
    };

    public final String[] SWAGGER = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v1/api-docs/**",
            "/swagger-ui/**"
    };

    private final Roles roles = new Roles();

    @Getter
    @NoArgsConstructor
    public class Roles {
        private final String USER = "ROLE_USER";
        private final String ADMIN = "ROLE_ADMIN";
    }

    public Boolean contains(String url) {
        return Arrays.asList(NOT_AUTHENTICATED).contains(url) || Arrays.asList(SWAGGER).contains(url);
    }
}