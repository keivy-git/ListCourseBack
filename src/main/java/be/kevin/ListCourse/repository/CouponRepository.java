package be.kevin.ListCourse.repository;

import be.kevin.ListCourse.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO = Data Access Object
 * pour les opérations avec la base de donnée
 *
 * @Repository : cette annotation est appliquée à la classe afin d'indiquer à Spring qu'il s'agit d'une classe qui gère les données.
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Coupon findByName(String name);

}
