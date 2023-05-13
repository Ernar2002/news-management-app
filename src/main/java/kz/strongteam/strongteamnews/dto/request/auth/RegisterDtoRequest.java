package kz.strongteam.strongteamnews.dto.request.auth;

public record RegisterDtoRequest(
        String email,
        String first_name,
        String last_name,
        String password,
        String re_password
) {
}
