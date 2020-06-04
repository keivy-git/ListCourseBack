package be.kevin.ListCourse.service;

import be.kevin.ListCourse.dto.UserDTO;
import be.kevin.ListCourse.entities.User;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import be.kevin.ListCourse.mapper.UserMapper;
import be.kevin.ListCourse.repository.RoleRepository;
import be.kevin.ListCourse.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @service permet à spring de considerer la classe comme un bean qu'il créera au démarrage
 * @Autowired permet d'injecter un bean "supposé" existant dans le contexte de la classe appelante
 */

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private UserMapper userMapper;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                       UserMapper userMapper,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDTO create (UserDTO userDTO) {
        return userMapper.toDto(this.userRepository.save(userMapper.toEntity(userDTO)));
    }

    @Override
    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((userMapper::toDto))
                .collect(Collectors.toList());
    }

    @Override
    public  UserDTO getOneById(Long idUser) throws NotFoundException  {
        return userMapper.toDto(this.userRepository.findById(idUser)
                .orElseThrow(() -> new NotFoundException("L'id de l'utilisateur n'a pas été trouvé")));
    }

    @Override
    public UserDTO updateId(Long idUser, String firstName, String name) throws NotUpdateException{
        Optional<User> optional = this.userRepository.findById(idUser);
        if (this.userRepository.existsById(idUser)) {
            User toUpdate = optional.get();
            toUpdate.setFirstName(firstName);
            toUpdate.setName(name);
            return userMapper.toDto(this.userRepository.save(toUpdate));
        }
        else {
            throw new NotUpdateException();
        }
    }

    @Override
    public void deleteId(Long idUser) throws NotDeleteException {
        if (this.userRepository.existsById(idUser) ) {

            this.userRepository.deleteById(idUser);
        }
        else {
            throw new NotDeleteException();
        }
    }
}

