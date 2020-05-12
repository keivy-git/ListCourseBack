package be.kevin.ListCourse.mapper;

import be.kevin.ListCourse.dto.UserDTO;
import be.kevin.ListCourse.entities.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper implements GenericMapper<UserDTO, User>{

    @Override
    public UserDTO toDto(User user) {
        return new UserDTO(
                user.getIdUser(),
                user.getFirstName(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        return user;
    }

}
