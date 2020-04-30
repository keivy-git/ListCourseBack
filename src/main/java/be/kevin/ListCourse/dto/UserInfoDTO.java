package be.kevin.ListCourse.dto;


import be.kevin.ListCourse.entities.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


/**Data Transfert Object */
@Data

public class UserInfoDTO {


    private Long idUser;

    private String firstName;

    private String name;

    private LocalDate dateBirth;
    private Set<Role> roles  = new HashSet();

    public UserInfoDTO(Long idUser, String firstName, String name, LocalDate dateBirth) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.name = name;
        this.dateBirth = dateBirth;
    }
}




