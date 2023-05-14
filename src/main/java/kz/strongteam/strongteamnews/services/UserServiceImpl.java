package kz.strongteam.strongteamnews.services;

import kz.strongteam.strongteamnews.dto.request.UserDtoRequest;
import kz.strongteam.strongteamnews.dto.response.UserDtoResponse;
import kz.strongteam.strongteamnews.exceptions.NotFoundException;
import kz.strongteam.strongteamnews.mappers.UserDtoMapper;
import kz.strongteam.strongteamnews.models.User;
import kz.strongteam.strongteamnews.repositories.UserRepository;
import kz.strongteam.strongteamnews.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("User with given email: %s is not found", username)));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("User with given email: %s is not found", email)));
    }

    @Override
    public UserDtoResponse getById(UUID id) {
        return userRepository.findById(id)
                .map(userDtoMapper)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("User with given id: {}", id)));
    }

    @Override
    public List<UserDtoResponse> getAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .stream()
                .map(userDtoMapper)
                .collect((Collectors.toList()));
    }

    @Override
    public UserDtoResponse save(UserDtoRequest userDtoRequest) {
        User user = User.builder()
                .email(userDtoRequest.email())
                .firstName(userDtoRequest.first_name())
                .lastName(userDtoRequest.last_name())
                .password(passwordEncoder.encode(userDtoRequest.password()))
                .build();

        user = userRepository.save(user);

        return userDtoMapper.apply(user);
    }

    @Override
    public UserDtoResponse update(UUID id, UserDtoRequest user) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
