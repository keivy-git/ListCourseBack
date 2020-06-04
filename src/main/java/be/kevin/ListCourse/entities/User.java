package be.kevin.ListCourse.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
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

    /**
     * @JsonProperty permet de définir un accès précis a l'information... Je peux créer un mot de passe, mais pas l'afficher via le json
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Email(message = "Cette e-mail n'est pas valide")
    private String email;


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = Role.class)
    @JoinTable(name = "userRole", joinColumns = @JoinColumn(name = "idUser"), inverseJoinColumns = @JoinColumn(name = "idRole"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = Coupon.class)
    @JoinTable(name = "userCoupon", joinColumns = @JoinColumn(name = "idUser"), inverseJoinColumns = @JoinColumn(name = "idCoupon"))
    private Set<Coupon> coupons = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = Product.class)
    @JoinTable(name = "userSaveListProduct", joinColumns = @JoinColumn(name = "idUser"), inverseJoinColumns = @JoinColumn(name = "idProduct"))
    private Set<Product> products = new HashSet<>();

//region Implements UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
