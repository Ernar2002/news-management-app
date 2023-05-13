package kz.strongteam.strongteamnews.dto.response;

import kz.strongteam.strongteamnews.models.enums.EActive;

import java.util.Date;
import java.util.UUID;

public record UserDtoResponse (
        UUID id,
        Date createdAt,
        Date updatedAt,
        EActive active,
        String email,
        String firstName,
        String lastName
) {
}