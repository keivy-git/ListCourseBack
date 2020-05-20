package be.kevin.ListCourse.entities;

import lombok.Data;

import javax.persistence.*;


/**
 *  @see Entity permet à JPA de transporter des données entre l'application et la BDD.
 *
 *  @see Data est annotation pratique qui génère tout directement (tostring, equalsandhashcode, getter/setter). En d'autre terme l'annotation DATA est passe-partout.
 *
 *  @see Table Création de la table group.
 */
@Entity
@Data
@Table(name = "[GROUP]")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdGroup;

    @Column(nullable = false, length=50)
    private String labelGroup;



}
