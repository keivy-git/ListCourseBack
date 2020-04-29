package be.kevin.ListCourse.dto;


import be.kevin.ListCourse.entities.Role;
import be.kevin.ListCourse.utils.verifMail.EmailValid;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


/**Data Transfert Object */
@Data
/**
 * @iPassword verifie que les deux mot de passe corresponde
 */
//@iPassword
public class UserDTO {

        @NotEmpty
        @NotNull
        private Long idUser;
        @NotEmpty
        @NotNull
        private String firstName;
        @NotEmpty
        @NotNull
        private String name;
        @NotEmpty
        @NotNull
        @EmailValid
        private String email;
        @NotEmpty
        @NotNull
        private String password;
        @NotEmpty
        @NotNull
        private String confirmPassword;
        @NotEmpty
        @NotNull
        private LocalDate dateBirth;
        private Set<Role> roles  = new HashSet();

        public boolean passwordEqualsConfirm() {
                return password.equals(confirmPassword);
        }



}
