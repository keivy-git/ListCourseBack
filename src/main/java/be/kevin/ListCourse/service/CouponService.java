package be.kevin.ListCourse.service;

import be.kevin.ListCourse.entities.Coupon;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.mapper.CouponMapper;
import be.kevin.ListCourse.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CouponMapper couponMapper;


    public List<Coupon> getAll() {
        return couponRepository.findAll();
    }

    public Coupon create(Coupon coupon) {
        return this.couponRepository.save(coupon);
    }


    public Optional<Coupon> getOneById(Long idCoupon) {
        return this.couponRepository.findById(idCoupon);
    }

    public Coupon update(Long idCoupon, String name, String description, LocalDate dateBegin, LocalDate dateEnd) {
        Optional<Coupon> optionalCoupon = this.getOneById(idCoupon);
        if (optionalCoupon.isEmpty()) {
            return null;
        }
        Coupon toUpdate = optionalCoupon.get();
        toUpdate.setName(name);
        toUpdate.setDescription(description);
        toUpdate.setDateBegin(dateBegin);
        toUpdate.setDateEnd(dateEnd);
        return this.couponRepository.save(toUpdate);
    }

    public void delete(Long idCoupon) throws NotDeleteException {
        if (this.couponRepository.existsById(idCoupon) ) {

            this.couponRepository.deleteById(idCoupon);
        }
        else {
            throw new NotDeleteException();
        }
    }


}

