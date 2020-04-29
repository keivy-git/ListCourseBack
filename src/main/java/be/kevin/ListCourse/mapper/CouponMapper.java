package be.kevin.ListCourse.mapper;

import be.kevin.ListCourse.entities.Coupon;
import be.kevin.ListCourse.dto.CouponDTO;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper implements GenericMapper<CouponDTO, Coupon>  {

    @Override
    public CouponDTO toDto(Coupon coupon) {
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setIdCoupon(coupon.getIdCoupon());
        couponDTO.setName(coupon.getName());
        couponDTO.setDescription(coupon.getDescription());
        couponDTO.setDateBegin(coupon.getDateBegin());
        couponDTO.setDateEnd(coupon.getDateEnd());
        return couponDTO;
    }

    @Override
    public Coupon toEntity(CouponDTO couponDTO) {
        Coupon coupon = new Coupon()   ;
        coupon.setIdCoupon(couponDTO.getIdCoupon());
        coupon.setName(couponDTO.getName());
        coupon.setDescription(couponDTO.getDescription());
        coupon.setDateBegin(couponDTO.getDateBegin());
        coupon.setDateEnd(couponDTO.getDateEnd());
        return coupon;
    }
}
