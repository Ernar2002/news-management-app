package kz.strongteam.strongteamnews.mappers;

import kz.strongteam.strongteamnews.dto.response.NewsDtoResponse;
import kz.strongteam.strongteamnews.models.News;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class NewsDtoMapper implements Function<News, NewsDtoResponse> {
    @Override
    public NewsDtoResponse apply(News news) {
        return new NewsDtoResponse(
                news.getId(),
                news.getCreatedAt(),
                news.getUpdatedAt(),
                news.getActive(),
                news.getTitle(),
                news.getContent(),
                news.getSource().getId(),
                news.getTopic().getId()
        );
    }
}
