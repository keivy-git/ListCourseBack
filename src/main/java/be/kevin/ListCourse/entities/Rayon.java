package be.kevin.ListCourse.entities;


import lombok.Data;

import javax.persistence.*;

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
