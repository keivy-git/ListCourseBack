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


}
