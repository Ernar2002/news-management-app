package kz.strongteam.strongteamnews.services;

import kz.strongteam.strongteamnews.dto.request.SourceDtoRequest;
import kz.strongteam.strongteamnews.dto.response.SourceDtoResponse;
import kz.strongteam.strongteamnews.exceptions.NotFoundException;
import kz.strongteam.strongteamnews.mappers.SourceDtoMapper;
import kz.strongteam.strongteamnews.models.Source;
import kz.strongteam.strongteamnews.repositories.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import kz.strongteam.strongteamnews.services.interfaces.SourceService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SourceServiceImpl implements SourceService {

    private final SourceRepository sourceRepository;
    private final SourceDtoMapper sourceDtoMapper;

    @Override
    public SourceDtoResponse getById(UUID id) {
        return sourceRepository.findById(id)
                .map(sourceDtoMapper)
                .orElseThrow(()-> new NotFoundException(String.format("Source with given id: {}", id + " not found!")));
    }

    @Override
    public List<SourceDtoResponse> getAll(Pageable pageable) {
        return sourceRepository.findAll(pageable)
                .stream()
                .map(sourceDtoMapper)
                .collect(Collectors.toList());
    }

    @Override
    public SourceDtoResponse save(SourceDtoRequest sourceDtoRequest) {
        Source source = Source.builder()
                .name(sourceDtoRequest.name())
                .url(sourceDtoRequest.url())
                .build();

        source = sourceRepository.save(source);

        return sourceDtoMapper.apply(source);
    }

    @Override
    public SourceDtoResponse update(UUID id, SourceDtoRequest sourceDtoRequest) {
        Source sourceFromDb = getSource(id);

        if (sourceDtoRequest.name() != null) {
            sourceFromDb.setName(sourceDtoRequest.name());
        }

        if (sourceDtoRequest.url() != null) {
            sourceFromDb.setUrl(sourceDtoRequest.url());
        }

        sourceFromDb = sourceRepository.save(sourceFromDb);

        return sourceDtoMapper.apply(sourceFromDb);
    }

    @Override
    public void delete(UUID id) {
        sourceRepository.deleteById(id);
    }

    private Source getSource(UUID id) {
        return sourceRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("Source with given id: {}", id + " not found!")));
    }
}
