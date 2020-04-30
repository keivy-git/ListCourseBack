package be.kevin.ListCourse.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class CouponDTO {

    private Long idCoupon;
    private String name;
    private String description;
    private LocalDate dateBegin;
    private LocalDate dateEnd;

    public CouponDTO(Long idCoupon, String name, String description, LocalDate dateBegin, LocalDate dateEnd) {
        this.idCoupon = idCoupon;
        this.name = name;
        this.description = description;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }
}
