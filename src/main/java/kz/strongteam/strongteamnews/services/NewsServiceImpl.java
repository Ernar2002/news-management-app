package kz.strongteam.strongteamnews.services;

import kz.strongteam.strongteamnews.dto.request.NewsDtoRequest;
import kz.strongteam.strongteamnews.dto.response.NewsDtoResponse;
import kz.strongteam.strongteamnews.exceptions.NotFoundException;
import kz.strongteam.strongteamnews.mappers.NewsDtoMapper;
import kz.strongteam.strongteamnews.models.News;
import kz.strongteam.strongteamnews.models.Source;
import kz.strongteam.strongteamnews.models.Topic;
import kz.strongteam.strongteamnews.repositories.NewsRepository;
import kz.strongteam.strongteamnews.repositories.SourceRepository;
import kz.strongteam.strongteamnews.repositories.TopicRepository;
import kz.strongteam.strongteamnews.services.interfaces.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsDtoMapper newsDtoMapper;
    private final SourceRepository sourceRepository;
    private final TopicRepository topicRepository;

    @Override
    public NewsDtoResponse getById(UUID id) {
        return newsRepository.findById(id)
                .map(newsDtoMapper)
                .orElseThrow(() -> new NotFoundException(String.format("News with given id: {}", id + " not found!")));
    }

    @Override
    public List<NewsDtoResponse> getAll(Pageable pageable) {
        return newsRepository.findAll(pageable)
                .stream()
                .map(newsDtoMapper)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDtoResponse save(NewsDtoRequest newsDtoRequest) {

        Source source = getSource(newsDtoRequest.source_id());

        Topic topic = getTopic(newsDtoRequest.topic_id());

        News news = News.builder()
                .title(newsDtoRequest.title())
                .content(newsDtoRequest.content())
                .source(source)
                .topic(topic)
                .build();

        news = newsRepository.save(news);

        return newsDtoMapper.apply(news);
    }

    @Override
    public NewsDtoResponse update(UUID id, NewsDtoRequest newsDtoRequest) {

        News newsFromDb = getNews(id);

        if (newsDtoRequest.title() != null) {
            newsFromDb.setTitle(newsDtoRequest.title());
        }

        if (newsDtoRequest.content() != null) {
            newsFromDb.setContent(newsDtoRequest.content());
        }

        if (newsDtoRequest.source_id() != null) {
            Source source = getSource(newsDtoRequest.source_id());
            newsFromDb.setSource(source);
        }

        if (newsDtoRequest.topic_id() != null) {
            Topic topic = getTopic(newsDtoRequest.topic_id());
            newsFromDb.setTopic(topic);
        }

        newsFromDb.setUpdatedAt(new Date());
        newsFromDb = newsRepository.save(newsFromDb);

        return newsDtoMapper.apply(newsFromDb);
    }

    @Override
    public void delete(UUID id) {
        try {
            newsRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException(String.format("News with given id: {}", id + " not found!"));
        }
    }

    @Override
    public List<NewsDtoResponse> getNewsBySourceId(UUID sourceId, Pageable pageable) {
        return newsRepository.findBySourceId(sourceId, pageable)
                .stream()
                .map(newsDtoMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsDtoResponse> getNewsByTopicId(UUID topicId, Pageable pageable) {
        return newsRepository.findByTopicId(topicId, pageable)
                .stream()
                .map(newsDtoMapper)
                .collect(Collectors.toList());
    }

    private News getNews(UUID id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("News with given id: {}", id + " not found!")));
    }

    private Source getSource(UUID id) {
        return sourceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Source with given id: {}", id + " not found!")));
    }

    private Topic getTopic(UUID id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Topic with given id: {}", id + " not found!")));
    }
}
