package be.kevin.ListCourse.dto;


import be.kevin.ListCourse.entities.Coupon;
import be.kevin.ListCourse.entities.Product;
import be.kevin.ListCourse.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**Data Transfert Object */
@Data

public class UserDTO {

        private Long idUser;
        private String firstName;
        private String name;
        private String email;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;
        private Set<Role> roles = new HashSet<>();
        private Set<Coupon> coupons = new HashSet<>();
        private Set<Product> products = new HashSet<>();

        /**
         * constructeur UserDto
         * @param idUser
         * @param firstName
         * @param name
         * @param email
         * @param password
         * @param roles
         */
        public UserDTO(Long idUser, String firstName, String name, String email, String password, Set<Role> roles, Set<Coupon> coupons, Set<Product> products) {
                this.idUser = idUser;
                this.firstName = firstName;
                this.name = name;
                this.email = email;
                this.password = password;
                this.roles = roles;
                this.coupons = coupons;
                this.products = products;

        }
}
