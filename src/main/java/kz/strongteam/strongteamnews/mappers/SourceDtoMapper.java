package kz.strongteam.strongteamnews.mappers;

import kz.strongteam.strongteamnews.dto.response.SourceDtoResponse;
import kz.strongteam.strongteamnews.models.Source;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SourceDtoMapper implements Function<Source, SourceDtoResponse> {
    @Override
    public SourceDtoResponse apply(Source Source) {
        return new SourceDtoResponse(
                Source.getId(),
                Source.getCreatedAt(),
                Source.getUpdatedAt(),
                Source.getActive(),
                Source.getName(),
                Source.getUrl()
        );
    }
}
