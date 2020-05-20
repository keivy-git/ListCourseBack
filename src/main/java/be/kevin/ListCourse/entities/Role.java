package be.kevin.ListCourse.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 *  @see Entity permet à JPA de transporter des données entre l'application et la BDD.
 *
 *  @see Data est annotation pratique qui génère tout directement (tostring, equalsandhashcode, getter/setter). En d'autre terme l'annotation DATA est passe-partout.
 *
 *  @see Table Création de la table role.
 */
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

    @Override
    public String getAuthority() {
        return name;
    }
}
