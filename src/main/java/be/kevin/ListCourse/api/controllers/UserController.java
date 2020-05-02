package be.kevin.ListCourse.api.controllers;


import be.kevin.ListCourse.dto.UserDTO;
import be.kevin.ListCourse.entities.User;
import be.kevin.ListCourse.exceptionHandler.NotCreateException;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import be.kevin.ListCourse.mapper.UserMapper;
import be.kevin.ListCourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(this.userService.get());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getOneById(@PathVariable(value = "id") Long idUser) {
        Optional<User> optional = userService.getOneById(idUser);
            return new ResponseEntity<User>(optional.get(), HttpStatus.FOUND);
    }

    @PostMapping("create")
    public ResponseEntity<User> create (@RequestBody User user ) throws NotCreateException {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.create(user));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<User> update(@PathVariable(value = "id") Long idUser, @RequestBody User update) throws NotUpdateException {
        return ResponseEntity.ok(this.userService.updateId(idUser, update.getFirstName() ,update.getName()));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long idUser) throws NotDeleteException {
        userService.deleteId(idUser);

        return ResponseEntity.ok(Boolean.TRUE) ;
    }


}

