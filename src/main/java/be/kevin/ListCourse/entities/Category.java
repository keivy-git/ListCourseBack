package be.kevin.ListCourse.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *  @see Entity permet à JPA de transporter des données entre l'application et la BDD.
 *
 *  @see Data est annotation pratique qui génère tout directement (tostring, equalsandhashcode, getter/setter). En d'autre terme l'annotation DATA est passe-partout.
 *
 *  @see Table Création de la table categorie.
 */
@Entity
@Data
@Table(name ="category")

public class Category {

    @Id
    /** Attribue des ids de mannières automatiques.*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @Column(nullable = false, length=50)
    private String name;

    @ManyToMany
    private Set<Product> products = new HashSet<>();

}
