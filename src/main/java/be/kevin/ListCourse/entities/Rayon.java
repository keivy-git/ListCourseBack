package be.kevin.ListCourse.entities;


import lombok.Data;

import javax.persistence.*;


/**
 *  @see Entity permet à JPA de transporter des données entre l'application et la BDD.
 *
 *  @see Data est annotation pratique qui génère tout directement (tostring, equalsandhashcode, getter/setter). En d'autre terme l'annotation DATA est passe-partout.
 *
 *  @see Table Création de la table rayon.
 */

@Data
@Entity
@Table(name = "rayon")
public class Rayon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRayon;

    @Column(name = "name", length=50)
    private String name;
}
