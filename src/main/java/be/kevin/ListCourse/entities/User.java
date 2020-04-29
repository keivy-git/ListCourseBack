package be.kevin.ListCourse.entities;



import be.kevin.ListCourse.utils.verifMail.EmailValid;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *  @see Entity permet à JPA de transporter des données entre l'application et la BDD.
 *
 *  @see Data est annotation pratique qui génère tout directement (tostring, equalsandhashcode, getter/setter). En d'autre terme l'annotation DATA est passe-partout.
 *
 *  @see Table Création de la table user.
 */
@Entity
@Data
@Table(name ="user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {

    /** création des colonnes de la table */
    @Id
    /** Attribue des ids de mannières automatiques.*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(unique=true, nullable = false, length = 50)
    private String firstName;

    @Column(unique = true, nullable = false, length=50)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @EmailValid
    private String email;

    /** permet d'instaurer une format à la date, un paterne jour, mois, année */


    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column
    private LocalDate dateBirth;


    @OneToMany
    @JoinTable(name = "userRole", joinColumns = @JoinColumn(name = "idUser"), inverseJoinColumns = @JoinColumn(name = "idRole"))
//    private Set<Role> roles= new HashSet<>();
    private Set<Role> roles = new HashSet<>();


    //@ManyToMany(mappedBy = "user")
    //private Set<Group> groups;

//region UserDetails méthode
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
    //endregion

}
