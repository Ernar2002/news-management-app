package kz.strongteam.strongteamnews.dto.request;

import java.util.UUID;

public record NewsDtoRequest(
        String title,
        String content,
        UUID source_id,
        UUID topic_id
) {
}
