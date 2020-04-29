package be.kevin.ListCourse.repository;

import be.kevin.ListCourse.entities.Rayon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RayonRepository extends JpaRepository<Rayon, Long> {


}
