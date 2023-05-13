package kz.strongteam.strongteamnews.dto.response;

import kz.strongteam.strongteamnews.models.enums.EActive;

import java.util.Date;
import java.util.UUID;

public record NewsDtoResponse(
        UUID id,
        Date created_at,
        Date updated_at,
        EActive active,
        String title,
        String content,
        UUID source_id,
        UUID topic_id
) {
}
