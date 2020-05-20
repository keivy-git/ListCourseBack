package be.kevin.ListCourse.api.controllers;


import be.kevin.ListCourse.dto.UserDTO;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import be.kevin.ListCourse.service.UserServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@RestController
@RequestMapping("/api-user")
public class UserController {

    /**
     * @Autowired afin que Spring se charge d'en fabriquer une instance
     */
    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;

    /**
     * Controlleur
     * @param userService
     * @param passwordEncoder
     */
    @Autowired
    public UserController(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    /**
     * méthode pour afficher la liste des utilisateurs
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(this.userService.getAll());
    }

    /**
     * méthode qui permet d'afficher un utilisateur via son id
     * @param idUser
     * @return
     * @throws NotFoundException
     */
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getOneById(@PathVariable(value = "id") Long idUser) throws NotFoundException {
        UserDTO userDTO = userService.getOneById(idUser);
            return new ResponseEntity<UserDTO>(userDTO, HttpStatus.FOUND);
    }
    /**
     * méthode qui permet la création d'un utilisateur
     * @param userDTO
     * @return
     */
    @PostMapping({"create", "register"})
    public ResponseEntity<UserDTO> create (@RequestBody UserDTO userDTO ) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDTO));
    }

    /**
     * méthode pour mettre à jour l'utilisateur
     * @param idUser
     * @param update
     * @return
     * @throws NotUpdateException
     */
    @PutMapping("update/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable(value = "id") Long idUser, @RequestBody UserDTO update) throws NotUpdateException {
        return ResponseEntity.ok(this.userService.updateId(idUser, update.getFirstName() ,update.getName()));
    }
    /** Supprimer un user par son id */
    /**
     * méthode pour supprimer un utilisateur
     * @param idUser
     * @return
     * @throws NotDeleteException
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long idUser) throws NotDeleteException {
        userService.deleteId(idUser);

        return ResponseEntity.ok(Boolean.TRUE) ;
    }

    /**
     * to do
     * méthode qui permettra de réaliser la connexion
     * @param userDTO
     * @return
     */
    @PostMapping("login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO ) {
        userService.login(userDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.login(userDTO));
    }
}

