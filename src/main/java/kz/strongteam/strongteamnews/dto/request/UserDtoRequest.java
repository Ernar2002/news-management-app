package kz.strongteam.strongteamnews.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record UserDtoRequest (
        String email,
        String first_name,
        String last_name,
        String password
) {
}
