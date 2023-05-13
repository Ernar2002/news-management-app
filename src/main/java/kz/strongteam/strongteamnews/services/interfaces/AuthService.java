package kz.strongteam.strongteamnews.services.interfaces;

import kz.strongteam.strongteamnews.dto.request.auth.LoginDtoRequest;
import kz.strongteam.strongteamnews.dto.request.auth.RegisterDtoRequest;
import kz.strongteam.strongteamnews.dto.request.auth.TokenDtoRequest;
import kz.strongteam.strongteamnews.dto.response.UserDtoResponse;
import kz.strongteam.strongteamnews.dto.response.auth.TokenDtoResponse;

public interface AuthService {
    TokenDtoResponse login(LoginDtoRequest request);

    UserDtoResponse register(RegisterDtoRequest request);

    TokenDtoResponse refresh(TokenDtoRequest request);
}
