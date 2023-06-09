package kz.strongteam.strongteamnews.dto.response;

import kz.strongteam.strongteamnews.models.enums.EActive;

import java.util.Date;
import java.util.UUID;

public record UserDtoResponse (
        UUID id,
        Date created_at,
        Date updated_at,
        EActive active,
        String email,
        String first_name,
        String last_name
) {
}