package kz.strongteam.strongteamnews.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDtoRequest (
        String email,
        String firstName,
        String lastName,
        String password
) {
}
