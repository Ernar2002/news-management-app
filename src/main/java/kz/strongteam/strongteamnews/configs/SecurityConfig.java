package kz.strongteam.strongteamnews.configs;

import kz.strongteam.strongteamnews.jwt.JwtUtil;
import kz.strongteam.strongteamnews.jwt.TokenProperties;
import kz.strongteam.strongteamnews.jwt.filter.JWTAuthenticationFilter;
import kz.strongteam.strongteamnews.jwt.filter.JWTAuthorizationFilter;
import kz.strongteam.strongteamnews.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityProperties securityProperties;

    private final TokenProperties tokenProperties;

    private final JwtUtil jwtUtil;

    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()
                .antMatchers(securityProperties.getNOT_AUTHENTICATED()).permitAll()
                .antMatchers(securityProperties.getSWAGGER()).permitAll()
                .anyRequest().authenticated();

        http.addFilter(jwtAuthorizationFilter());
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    private JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter(securityProperties, tokenProperties, jwtUtil, userService);
    }

    private JWTAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JWTAuthorizationFilter(authenticationManagerBean(), jwtUtil);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring();
    }
}