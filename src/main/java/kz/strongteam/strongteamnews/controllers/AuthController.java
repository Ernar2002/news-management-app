package kz.strongteam.strongteamnews.controllers;

import kz.strongteam.strongteamnews.dto.request.auth.LoginDtoRequest;
import kz.strongteam.strongteamnews.dto.request.auth.RegisterDtoRequest;
import kz.strongteam.strongteamnews.dto.request.auth.TokenDtoRequest;
import kz.strongteam.strongteamnews.dto.response.UserDtoResponse;
import kz.strongteam.strongteamnews.dto.response.auth.TokenDtoResponse;
import kz.strongteam.strongteamnews.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/auth/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenDtoResponse login(@Valid @RequestBody LoginDtoRequest request) {
        return authService.login(request);
    }

    @PostMapping("refresh")
    public TokenDtoResponse refresh(@Valid @RequestBody TokenDtoRequest request) {
        return authService.refresh(request);
    }

    @PostMapping("/register")
    public UserDtoResponse register(@Valid @RequestBody RegisterDtoRequest request) {
        return authService.register(request);
    }
}
