package kz.strongteam.strongteamnews.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.strongteam.strongteamnews.dto.request.auth.LoginDtoRequest;
import kz.strongteam.strongteamnews.exceptions.UnauthorizedException;
import kz.strongteam.strongteamnews.jwt.JwtUtil;
import kz.strongteam.strongteamnews.models.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class JWTAuthorizationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginDtoRequest userLogin;
        try {
            userLogin = new ObjectMapper().readValue(request.getInputStream(), LoginDtoRequest.class);
        } catch (IOException e) {
            throw new UnauthorizedException("HELP", e);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLogin.email(), userLogin.password());
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws ServletException, IOException {
        User user = (User) authentication.getPrincipal();
        String token = jwtUtil.createJwtToken(user);
        response.setHeader("token", token);
    }
}