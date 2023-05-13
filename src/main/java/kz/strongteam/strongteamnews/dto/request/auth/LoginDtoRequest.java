package kz.strongteam.strongteamnews.dto.request.auth;

public record LoginDtoRequest(
        String email,
        String password
) {
}
