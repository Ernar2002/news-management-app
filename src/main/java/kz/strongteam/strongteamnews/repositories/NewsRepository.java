package kz.strongteam.strongteamnews.repositories;

import kz.strongteam.strongteamnews.models.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NewsRepository extends JpaRepository<News, UUID> {
    List<News> findBySourceId(UUID sourceId, Pageable pageable);
    List<News> findByTopicId(UUID topicId, Pageable pageable);
}
