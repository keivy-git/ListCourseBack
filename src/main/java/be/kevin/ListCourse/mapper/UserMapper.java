package be.kevin.ListCourse.mapper;

import be.kevin.ListCourse.dto.UserDTO;
import be.kevin.ListCourse.entities.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper implements GenericMapper<UserDTO, User>{

    /**
     * entity to dto
     * @param user
     * @return new UserDTO
     */
    @Override
    public UserDTO toDto(User user) {
        return new UserDTO(
                user.getIdUser(),
                user.getFirstName(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles(),
                user.getCoupons(),
                user.getProducts()
        );
    }

    /**
     * dto to entity
     * @param userDTO
     * @return user
     */
    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRoles(userDTO.getRoles());
        user.setCoupons(userDTO.getCoupons());
        user.setProducts(userDTO.getProducts());
        return user;
    }

}
