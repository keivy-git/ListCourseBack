package be.kevin.ListCourse.service;

import be.kevin.ListCourse.dto.CouponDTO;
import be.kevin.ListCourse.entities.Coupon;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import be.kevin.ListCourse.mapper.CouponMapper;
import be.kevin.ListCourse.repository.CouponRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CouponMapper couponMapper;


    public CouponDTO create(CouponDTO couponDTO) {
        return couponMapper.toDto(this.couponRepository.save(couponMapper.toEntity(couponDTO)));
    }

    public List<CouponDTO> getAll(){
        List<Coupon> coupons = couponRepository.findAll();
        return coupons.stream()
                .map((couponMapper::toDto))
                .collect(Collectors.toList());
    }

    public CouponDTO getOneById(Long idCoupon) throws NotFoundException {
        return couponMapper.toDto(this.couponRepository.findById(idCoupon)
                .orElseThrow(() -> new NotFoundException("l'id des coupons n'a pas été trouvé")));
    }

    public CouponDTO update(Long idCoupon, String name, String description, LocalDate dateBegin, LocalDate dateEnd) throws NotUpdateException {
        Optional<Coupon> optionalCoupon = this.couponRepository.findById(idCoupon);
        if (this.couponRepository.existsById(idCoupon)) {
            Coupon toUpdate = optionalCoupon.get();
            toUpdate.setName(name);
            toUpdate.setDescription(description);
            toUpdate.setDateBegin(dateBegin);
            toUpdate.setDateEnd(dateEnd);
            return couponMapper.toDto(this.couponRepository.save(toUpdate));
        } else {
            throw new NotUpdateException();
        }
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

