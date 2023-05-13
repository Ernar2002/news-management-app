package kz.strongteam.strongteamnews.repositories;

import kz.strongteam.strongteamnews.models.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SourceRepository extends JpaRepository<Source, UUID> {
}
