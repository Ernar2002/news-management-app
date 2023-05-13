package kz.strongteam.strongteamnews.repositories;

import kz.strongteam.strongteamnews.models.Role;
import kz.strongteam.strongteamnews.models.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(ERole name);
}
