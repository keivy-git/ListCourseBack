package be.kevin.ListCourse.service;

import be.kevin.ListCourse.dto.UserDTO;
import be.kevin.ListCourse.entities.User;
import be.kevin.ListCourse.exceptionHandler.NotCreateException;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import be.kevin.ListCourse.mapper.UserMapper;
import be.kevin.ListCourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
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


    public User create (User user) throws NotCreateException {

         return this.userRepository.save(user);

    }

    public List<UserDTO> get(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((userMapper::toDto))
                .collect(Collectors.toList());
    }

    public Optional<User> getOneById(Long idUser)  {
        return this.userRepository.findById(idUser);
    }

    public User updateId(Long idUser, String firstName, String name) throws NotUpdateException {
        Optional<User> optional = this.getOneById(idUser);
        if (this.userRepository.existsById(idUser)) {
            User toUpdate = optional.get();
            toUpdate.setFirstName(firstName);
            toUpdate.setName(name);
            return this.userRepository.save(toUpdate);
        }
        else {
            throw new NotUpdateException();
        }
    }


    public void deleteId(Long idUser) throws NotDeleteException {
        if (this.userRepository.existsById(idUser) ) {

            this.userRepository.deleteById(idUser);
        }
        else {
            throw new NotDeleteException();
        }
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}

