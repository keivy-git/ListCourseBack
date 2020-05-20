package be.kevin.ListCourse.mapper;

import be.kevin.ListCourse.entities.Coupon;
import be.kevin.ListCourse.dto.CouponDTO;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper implements GenericMapper<CouponDTO, Coupon>  {

    /**
     * entity to dto
     * @param coupon
     * @return new CouponDTO
     */
    @Override
    public CouponDTO toDto(Coupon coupon) {
        return new CouponDTO(
              coupon.getIdCoupon(),
              coupon.getName(),
              coupon.getDescription(),
              coupon.getDateBegin(),
              coupon.getDateEnd()
        );
    }

    /**
     * dto to entity
     * @param couponDTO
     * @return coupon
     */
    @Override
    public Coupon toEntity(CouponDTO couponDTO) {
        Coupon coupon = new Coupon() ;
        coupon.setName(couponDTO.getName());
        coupon.setDescription(couponDTO.getDescription());
        coupon.setDateBegin(couponDTO.getDateBegin());
        coupon.setDateEnd(couponDTO.getDateEnd());
        return coupon;
    }
}
