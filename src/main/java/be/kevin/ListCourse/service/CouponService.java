package be.kevin.ListCourse.service;

import be.kevin.ListCourse.dto.CouponDTO;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CouponService {

    CouponDTO create(CouponDTO couponDTO);
    List<CouponDTO> getAll();
    CouponDTO getOneById(Long idUser) throws NotFoundException;
    CouponDTO update(Long idCoupon, String name, String description, LocalDate dateBegin, LocalDate dateEnd) throws NotUpdateException;
    void delete(Long idUser) throws NotDeleteException;

}
