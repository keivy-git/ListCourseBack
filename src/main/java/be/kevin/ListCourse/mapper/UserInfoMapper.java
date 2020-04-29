package be.kevin.ListCourse.mapper;

import be.kevin.ListCourse.dto.UserDTO;
import be.kevin.ListCourse.dto.UserInfoDTO;
import be.kevin.ListCourse.entities.User;
import be.kevin.ListCourse.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInfoMapper implements GenericMapper<UserInfoDTO, User> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserInfoDTO toDto(User user) {
        UserInfoDTO userDTO = new UserInfoDTO();
        userDTO.setIdUser(user.getIdUser());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setName(user.getName());
        userDTO.setDateBirth(user.getDateBirth());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

    @Override
    public User toEntity(UserInfoDTO userDTO) {
        User user = new User();
        user.setIdUser(userDTO.getIdUser());
        user.setFirstName(userDTO.getFirstName());
        user.setName(userDTO.getName());
        user.setDateBirth(userDTO.getDateBirth());
        user.setRoles(userDTO.getRoles());
        return user;
    }
}