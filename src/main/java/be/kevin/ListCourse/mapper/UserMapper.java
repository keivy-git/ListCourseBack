package be.kevin.ListCourse.mapper;

import be.kevin.ListCourse.entities.Role;
import be.kevin.ListCourse.entities.User;
import be.kevin.ListCourse.repository.RoleRepository;
import be.kevin.ListCourse.dto.UserDTO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class UserMapper implements GenericMapper<UserDTO, User>{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDTO toDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setIdUser(user.getIdUser());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setDateBirth(user.getDateBirth());
        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setIdUser(userDTO.getIdUser());
        user.setFirstName(userDTO.getFirstName());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setDateBirth(userDTO.getDateBirth());
        return user;
    }

    public User fromFormulaire (UserDTO form) throws NotFoundException {
        User user = new User();
        user.setName(form.getName());

        user.setPassword(encoder.encode(form.getPassword()));

        Role userRole = roleRepository.findAll()
                .stream()
                .filter(role -> role.getName().equals("Role_user"))
                .findFirst()
                .orElseThrow(()-> new NotFoundException("Role non trouvé"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        return user;
    }
}
