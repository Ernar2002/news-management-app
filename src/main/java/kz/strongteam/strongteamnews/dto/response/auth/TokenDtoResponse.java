package kz.strongteam.strongteamnews.dto.response.auth;

public record TokenDtoResponse (
        String accessToken,
        String refreshToken
) {
}
