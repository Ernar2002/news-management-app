//package kz.strongteam.strongteamnews.mappers;
//
//import kz.strongteam.strongteamnews.dto.response.UserDtoResponse;
//import kz.strongteam.strongteamnews.models.User;
//import org.springframework.stereotype.Service;
//
//import java.util.function.Function;
//
//@Service
//public class UserDtoMapper implements Function<User, UserDtoResponse> {
//    @Override
//    public UserDtoResponse apply(User user) {
//        return new UserDtoResponse(
//                user.getId(),
//                user.getCreatedAt(),
//                user.getUpdatedAt(),
//                user.getActive(),
//                user.getEmail(),
//                user.getFirstName(),
//                user.getLastName()
//        );
//    }
//}
