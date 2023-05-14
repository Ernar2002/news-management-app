package kz.strongteam.strongteamnews.dto.request;

public record UserDtoRequest(
        String email,
        String first_name,
        String last_name,
        String password
) {
}
