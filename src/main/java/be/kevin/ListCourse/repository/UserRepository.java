package be.kevin.ListCourse.repository;

import be.kevin.ListCourse.dto.UserInfoDTO;
import be.kevin.ListCourse.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * pour les opérations avec la base de donnée
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName (String name);


}
