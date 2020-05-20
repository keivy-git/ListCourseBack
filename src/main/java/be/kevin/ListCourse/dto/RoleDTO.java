package be.kevin.ListCourse.dto;


import lombok.Data;

@Data
public class RoleDTO {

    private Long idRole;
    private String name;
    private String description;

    /**
     * constructeur RoleDTO
     * @param idRole
     * @param name
     * @param description
     */
    public RoleDTO(Long idRole, String name, String description) {
        this.idRole = idRole;
        this.name = name;
        this.description = description;
    }
}

