package kz.strongteam.strongteamnews.services.interfaces;

import kz.strongteam.strongteamnews.dto.request.UserDtoRequest;
import kz.strongteam.strongteamnews.dto.response.UserDtoResponse;
import kz.strongteam.strongteamnews.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends BaseService<UserDtoResponse, UserDtoRequest> {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User findByEmail(String email);
}
