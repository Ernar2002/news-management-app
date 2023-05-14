package kz.strongteam.strongteamnews.jwt.filter;

import kz.strongteam.strongteamnews.configs.SecurityProperties;
import kz.strongteam.strongteamnews.jwt.JwtUtil;
import kz.strongteam.strongteamnews.jwt.TokenProperties;
import kz.strongteam.strongteamnews.models.User;
import kz.strongteam.strongteamnews.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@AllArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private SecurityProperties securityProperties;

    private TokenProperties tokenProperties;

    private JwtUtil jwtUtil;

    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info(request.getServletPath());

        String header = request.getHeader(tokenProperties.getJwtTokenHeader());
        if (Strings.isBlank(header) || !header.startsWith(tokenProperties.getPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(tokenProperties.getPrefix().length() + 1);
        try {
            String username = jwtUtil.getSubject(token);
            User user = userService.findByEmail(username);
            if (!user.isAccountNonLocked() || !user.isEnabled()) {
                response.setStatus(UNAUTHORIZED.value());
                return;
            }
            if (!jwtUtil.getJwt(token).getClaim("type").isMissing()) {
                response.setStatus(UNAUTHORIZED.value());
                return;
            }
            List<GrantedAuthority> authorityList = new ArrayList<>();
            if (user.getAuthorities() != null) {
                authorityList = new ArrayList<>(user.getAuthorities());
            }
            Authentication authentication = jwtUtil.getAuthentication(user, authorityList, request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            response.setStatus(UNAUTHORIZED.value());
        }
        filterChain.doFilter(request, response);
    }
}