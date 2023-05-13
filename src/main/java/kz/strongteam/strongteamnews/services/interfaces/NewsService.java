package kz.strongteam.strongteamnews.services.interfaces;

import kz.strongteam.strongteamnews.dto.request.NewsDtoRequest;
import kz.strongteam.strongteamnews.dto.response.NewsDtoResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface NewsService extends BaseService<NewsDtoResponse, NewsDtoRequest>{
    List<NewsDtoResponse> getNewsBySourceId(UUID sourceId, Pageable pageable);

    List<NewsDtoResponse> getNewsByTopicId(UUID topicId, Pageable pageable);
}
