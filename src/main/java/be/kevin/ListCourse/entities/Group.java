package be.kevin.ListCourse.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "[group]")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdGroup;

    @Column(nullable = false, length=50)
    private String labelGroup;



}
