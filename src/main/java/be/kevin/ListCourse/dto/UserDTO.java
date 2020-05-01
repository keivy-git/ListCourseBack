package be.kevin.ListCourse.dto;


import be.kevin.ListCourse.entities.Role;
//import be.kevin.ListCourse.utils.verifMail.EmailValid;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**Data Transfert Object */
@Data

public class UserDTO implements UserDetails {


        private Long idUser;
        private String firstName;
        private String name;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String email;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;
        private String confirmPassword;
        private LocalDate dateBirth;
        private Set<Role> roles  = new HashSet();

        public boolean passwordEqualsConfirm() {
                return password.equals(confirmPassword);
        }

        public UserDTO(Long idUser, String firstName, String name, String email, String password, LocalDate dateBirth) {
                this.idUser = idUser;
                this.firstName = firstName;
                this.name = name;
                this.email = email;
                this.password = password;
                this.dateBirth = dateBirth;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
        }

        @Override
        public String getUsername() {
                return name;
        }
        @Override
        public String getPassword() {
                return password;
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }
}
