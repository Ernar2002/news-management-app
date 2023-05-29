package kz.strongteam.strongteamnews.services;

import kz.strongteam.strongteamnews.dto.request.TopicDtoRequest;
import kz.strongteam.strongteamnews.dto.response.TopicDtoResponse;
import kz.strongteam.strongteamnews.exceptions.NotFoundException;
import kz.strongteam.strongteamnews.mappers.TopicDtoMapper;
import kz.strongteam.strongteamnews.models.Topic;
import kz.strongteam.strongteamnews.repositories.TopicRepository;
import kz.strongteam.strongteamnews.services.interfaces.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final TopicDtoMapper topicDtoMapper;

    @Override
    public TopicDtoResponse getById(UUID id) {
        return topicRepository.findById(id)
                .map(topicDtoMapper)
                .orElseThrow(() -> new NotFoundException(String.format("Topic with given id: {}", id + " not found!")));
    }

    @Override
    public List<TopicDtoResponse> getAll(Pageable pageable) {
        return topicRepository.findAll(pageable)
                .stream()
                .map(topicDtoMapper)
                .collect((Collectors.toList()));
    }

    @Override
    @Transactional
    public TopicDtoResponse save(TopicDtoRequest topicDtoRequest) {
        Topic topic = Topic.builder()
                .name(topicDtoRequest.name())
                .build();

        topic = topicRepository.save(topic);

        return topicDtoMapper.apply(topic);
    }

    @Override
    @Transactional
    public TopicDtoResponse update(UUID id, TopicDtoRequest topicDtoRequest) {
        Topic topicFromDb = getTopic(id);

        if (topicDtoRequest.name() != null) {
            topicFromDb.setName(topicDtoRequest.name());
        }

        topicFromDb.setUpdatedAt(new Date());
        topicFromDb = topicRepository.save(topicFromDb);

        return topicDtoMapper.apply(topicFromDb);
    }

    @Override
    public void delete(UUID id) {
        try {
            topicRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException(String.format("Topic with given id: {}", id + " not found!"));
        }
    }

    private Topic getTopic(UUID id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Topic with given id: {}", id + " not found!")));
    }
}
