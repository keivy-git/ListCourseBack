package be.kevin.ListCourse.service;

import be.kevin.ListCourse.dto.UserDTO;
import be.kevin.ListCourse.entities.Role;
import be.kevin.ListCourse.entities.User;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import be.kevin.ListCourse.mapper.UserMapper;
import be.kevin.ListCourse.repository.UserRepository;
import be.kevin.ListCourse.utils.configSecu.JwtTokenProvider;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public UserDTO create (UserDTO userDTO) {
        return userMapper.toDto(this.userRepository.save(userMapper.toEntity(userDTO)));
    }

    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((userMapper::toDto))
                .collect(Collectors.toList());
    }

    public  UserDTO getOneById(Long idUser) throws NotFoundException  {
        return userMapper.toDto(this.userRepository.findById(idUser)
                .orElseThrow(() -> new NotFoundException("L'id de l'utilisateur n'a pas été trouvé")));
    }

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

    public void deleteId(Long idUser) throws NotDeleteException {
        if (this.userRepository.existsById(idUser) ) {

            this.userRepository.deleteById(idUser);
        }
        else {
            throw new NotDeleteException();
        }
    }
    // To do
    public UserDTO login (UserDTO userDTO){
        try {
            String login = userDTO.getName();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, userDTO.getPassword()));
            String token = jwtTokenProvider
                    .createToken(login,
                            this.userRepository
                                    .findByName(login)
                                    .getRoles()
                                    .stream()
                                    .map(Role::getAuthority)
                                    .collect(Collectors.toList())
                    );
            Map<Object, Object> model = new HashMap<>();
            model.put("name", login);
            model.put("token", token);
        } catch (Exception e) {
            throw new BadCredentialsException("Le login et le mot de passe est incorrecte");
        }
        return userDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByName(name);
    }
}

