package be.kevin.ListCourse.service;

import be.kevin.ListCourse.dto.UserDTO;
import be.kevin.ListCourse.dto.UserInfoDTO;
import be.kevin.ListCourse.entities.User;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.mapper.UserInfoMapper;
import be.kevin.ListCourse.mapper.UserMapper;
import be.kevin.ListCourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @service permet à spring de considerer la classe comme un bean qu'il créera au démarrage
 * @Autowired permet d'injecter un bean "supposé" existant dans le contexte de la classe appelante
 */



@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public List<UserInfoDTO> get(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((userInfoMapper::toDto))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException{
        Objects.requireNonNull(name);
        User user =  userRepository.findByName(name)
                .orElseThrow(()-> new UsernameNotFoundException("user non trouvé"));
    return user;
    }

    public User create (User user)  {
        return this.userRepository.save(user);
    }

    public Optional<User> getOneById(Long idUser)  {
        return this.userRepository.findById(idUser);
    }

    public User updateId(Long idUser, String firstName, String name) {
        Optional<User> optional = this.getOneById(idUser);
        if (optional.isEmpty()) {
            return null;
        }
        User toUpdate = optional.get();
        toUpdate.setFirstName(firstName);
        toUpdate.setName(name);
        return this.userRepository.save(toUpdate);
    }


    public void deleteId(Long idUser) throws NotDeleteException {
        if (this.userRepository.existsById(idUser) ) {

            this.userRepository.deleteById(idUser);
        }
        else {
            throw new NotDeleteException();
        }
    }
    //    public Long create(UserDTO form) throws NotFoundException, confirmPassword {
//
//        if (!form.passwordEqualsConfirm()) {
//            throw new confirmPassword("Le mot de passe répéter est different");
//        }
//        userRepository.save(userMapper.fromFormulaire(form));
//
//        User user = userRepository.findByName(form.getName()).orElse(new User());
//
//        return user.getIdUser();
//    }

}

