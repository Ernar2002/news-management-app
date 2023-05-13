package kz.strongteam.strongteamnews.mappers;

import kz.strongteam.strongteamnews.dto.response.TopicDtoResponse;
import kz.strongteam.strongteamnews.models.Topic;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TopicDtoMapper implements Function<Topic, TopicDtoResponse> {
    @Override
    public TopicDtoResponse apply(Topic topic) {
        return new TopicDtoResponse(
                topic.getId(),
                topic.getCreatedAt(),
                topic.getUpdatedAt(),
                topic.getActive(),
                topic.getName()
        );
    }
}