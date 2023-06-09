package kz.strongteam.strongteamnews.services;

import kz.strongteam.strongteamnews.dto.request.auth.LoginDtoRequest;
import kz.strongteam.strongteamnews.dto.request.auth.RegisterDtoRequest;
import kz.strongteam.strongteamnews.dto.request.auth.TokenDtoRequest;
import kz.strongteam.strongteamnews.dto.response.auth.TokenDtoResponse;
import kz.strongteam.strongteamnews.exceptions.BadRequestException;
import kz.strongteam.strongteamnews.exceptions.NotFoundException;
import kz.strongteam.strongteamnews.jwt.JwtUtil;
import kz.strongteam.strongteamnews.models.Role;
import kz.strongteam.strongteamnews.models.User;
import kz.strongteam.strongteamnews.models.enums.ERole;
import kz.strongteam.strongteamnews.repositories.UserRepository;
import kz.strongteam.strongteamnews.services.interfaces.AuthService;
import kz.strongteam.strongteamnews.services.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenDtoResponse login(LoginDtoRequest request) {

        User user = getUserByEmail(request.email());

        boolean matches = passwordEncoder.matches(request.password(), user.getPassword());

        if(!matches) {
            throw new BadRequestException("Password is incorrect");
        }

        String token = jwtUtil.createJwtToken(user);
        String refreshToken = jwtUtil.createRefreshToken(user);

        return new TokenDtoResponse(token, refreshToken);
    }

    @Override
    @Transactional
    public void register(RegisterDtoRequest request) {
        if (!request.password().equals(request.re_password()))
            throw new BadRequestException("Passwords are not equal");

        Role userRole = roleService.findByName(ERole.ROLE_USER);

        User user = User.builder()
                .email(request.email())
                .firstName(request.first_name())
                .lastName(request.last_name())
                .role(userRole)
                .password(passwordEncoder.encode(request.password()))
                .build();


        userRepository.save(user);
    }

    @Override
    public TokenDtoResponse refresh(TokenDtoRequest request) {
        return null;
    }

    private User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("User with given email: %s is not found", email)));
    }
}
