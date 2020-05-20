package be.kevin.ListCourse.service;

import be.kevin.ListCourse.dto.UserDTO;
import be.kevin.ListCourse.entities.User;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDTO create(UserDTO userDTO);
    List<UserDTO> getAll();
    UserDTO getOneById(Long idUser) throws NotFoundException;
    UserDTO updateId(Long idUser, String firstName, String name) throws NotUpdateException;
    void deleteId(Long idUser) throws NotDeleteException;
    UserDTO login(UserDTO userDTO);
}
