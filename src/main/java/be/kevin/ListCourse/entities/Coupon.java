package be.kevin.ListCourse.entities;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 *  @see Entity permet à JPA de transporter des données entre l'application et la BDD.
 *
 *  @see Data est annotation pratique qui génère tout directement (tostring, equalsandhashcode, getter/setter). En d'autre terme l'annotation DATA est passe-partout.
 *
 *  @see Table Création de la table coupon.
 */

@Data
@Entity
@Table(name = "coupon")
public class Coupon {

    /** création des colonnes de la table */
    @Id
    /** Attribue des ids de mannières automatiques.*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCoupon;

    @Column(length=50)
    private String name;

    @Column
    private String description;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column (name = "dateBegin_coupon")
    private LocalDate dateBegin;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column (name="dateEnd_Coupon")
    private LocalDate  dateEnd;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = User.class, mappedBy = "coupons")
    private Set<User> users = new HashSet<>();
}
