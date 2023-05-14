package kz.strongteam.strongteamnews.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import kz.strongteam.strongteamnews.exceptions.BadRequestException;
import kz.strongteam.strongteamnews.exceptions.UnauthorizedException;
import kz.strongteam.strongteamnews.models.User;
import kz.strongteam.strongteamnews.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class JwtUtil {

    private TokenProperties tokenProperties;
    private UserService userService;

    public String createJwtToken(User userDetails) {
        checkIfValid(userDetails);

        Instant instant = Instant.now();

        GrantedAuthority authority = userDetails.getAuthorities().stream()
                .findFirst()
                .orElseThrow(() -> {
                    throw new UnauthorizedException("User doesn't have any role");
                });
        HashMap<String, Object> subject = new HashMap<>();
        subject.put("email", userDetails.getEmail());
        subject.put("firstName", userDetails.getFirstName());
        subject.put("lastName", userDetails.getLastName());
        return JWT.create()
                .withIssuer(tokenProperties.getIssuer())
                .withAudience(tokenProperties.getAudience())
                .withSubject(userDetails.getUsername())
                .withIssuedAt(Date.from(instant))
                .withExpiresAt(Date.from(instant.plusSeconds(tokenProperties.getAccessExpirationTime())))
                .withClaim("user", subject)
                .withClaim("role", authority.getAuthority())
                .withClaim("fullName", String.format("%s %s", userDetails.getLastName(), userDetails.getFirstName()))
                .sign(Algorithm.HMAC512(tokenProperties.getSecret()));
    }

    public String createRefreshToken(User userDetails) {
        checkIfValid(userDetails);

        Instant now = Instant.now();

        return JWT.create()
                .withIssuer(tokenProperties.getIssuer())
                .withAudience(tokenProperties.getAudience())
                .withIssuedAt(Date.from(now))
                .withSubject(userDetails.getUsername())
                .withExpiresAt(now.plusSeconds(tokenProperties.getRefreshExpirationTime()))
                .withClaim("type", "Refresh")
                .sign(Algorithm.HMAC512(tokenProperties.getSecret()));
    }

    public String getSubject(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    public DecodedJWT getJwt(String token) {
        return getJWTVerifier().verify(token);
    }

    public Authentication getAuthentication(User user, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(user, null, authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }

    public boolean checkIfValid(UserDetails userDetails) {
        if (userDetails.getUsername().isEmpty())
            throw new BadRequestException("Cannot create JWT token without username");

        if (userDetails.getAuthorities().isEmpty())
            throw new UnauthorizedException("User doesn't have any authority");

        if (!userDetails.isAccountNonExpired() || !userDetails.isEnabled() || !userDetails.isAccountNonLocked() || !userDetails.isCredentialsNonExpired())
            throw new UnauthorizedException("User is not valid");
        return true;
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier jwtVerifier;

        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenProperties.getSecret());
            jwtVerifier = JWT.require(algorithm).withIssuer(tokenProperties.getIssuer()).build();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(tokenProperties.getTokenCannotBeVerified());
        }
        return jwtVerifier;
    }
}