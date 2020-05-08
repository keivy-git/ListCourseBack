package be.kevin.ListCourse.api.controllers;


import be.kevin.ListCourse.dto.UserDTO;
import be.kevin.ListCourse.entities.Role;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import be.kevin.ListCourse.mapper.UserMapper;
import be.kevin.ListCourse.repository.UserRepository;
import be.kevin.ListCourse.service.UserService;
import be.kevin.ListCourse.utils.configSecu.JwtTokenProvider;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@RestController
@RequestMapping("/api-user")
public class UserController implements Serializable {

    /** @Autowired afin que Spring se charge d'en fabriquer une instance */
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(this.userService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getOneById(@PathVariable(value = "id") Long idUser) throws NotFoundException {
        UserDTO userDTO = userService.getOneById(idUser);
            return new ResponseEntity<UserDTO>(userDTO, HttpStatus.FOUND);
    }
    /**Creation ou register d'un utilisateur */
    @PostMapping({"create", "register"})
    public ResponseEntity<UserDTO> create (@RequestBody UserDTO userDTO ) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDTO));
    }

    /** update d'un utilisateur */
    @PutMapping("update/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable(value = "id") Long idUser, @RequestBody UserDTO update) throws NotUpdateException {
        return ResponseEntity.ok(this.userService.updateId(idUser, update.getFirstName() ,update.getName()));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long idUser) throws NotDeleteException {
        userService.deleteId(idUser);

        return ResponseEntity.ok(Boolean.TRUE) ;
    }

    /** params de connexion*/

    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserDTO userDTO ) {
        userService.login(userDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.login(userDTO));
    }


}

