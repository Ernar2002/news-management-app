package kz.strongteam.strongteamnews.controllers;

import kz.strongteam.strongteamnews.dto.request.auth.LoginDtoRequest;
import kz.strongteam.strongteamnews.dto.request.auth.RegisterDtoRequest;
import kz.strongteam.strongteamnews.dto.request.auth.TokenDtoRequest;
import kz.strongteam.strongteamnews.dto.response.auth.TokenDtoResponse;
import kz.strongteam.strongteamnews.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenDtoResponse login(@Valid @RequestBody LoginDtoRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public TokenDtoResponse refresh(@Valid @RequestBody TokenDtoRequest request) {
        return authService.refresh(request);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDtoRequest request) {
        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body("");
    }

}
