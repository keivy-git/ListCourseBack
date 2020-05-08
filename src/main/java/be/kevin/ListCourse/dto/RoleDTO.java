package be.kevin.ListCourse.dto;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class RoleDTO {

    private Long idRole;
    private String name;
    private String description;


}

