package be.kevin.ListCourse.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    /**
     * length = longueur de caractère dans le texte
     * sans cette option la longueur par défaut est de 255
     */
    @Column(unique = true, nullable = false, length=50)
    private String name;

    @Column
    private String DescriptionRole;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = User.class, mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    @Override
    public String getAuthority() {
        return name;
    }
}
