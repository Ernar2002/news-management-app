package kz.strongteam.strongteamnews.services;

import kz.strongteam.strongteamnews.exceptions.NotFoundException;
import kz.strongteam.strongteamnews.models.Role;
import kz.strongteam.strongteamnews.models.enums.ERole;
import kz.strongteam.strongteamnews.repositories.RoleRepository;
import kz.strongteam.strongteamnews.services.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(ERole name) {
        return roleRepository.findByName(name)
                .orElseThrow(()-> new NotFoundException(String.format("Role with given name: %s is not found", name)));
    }
}
