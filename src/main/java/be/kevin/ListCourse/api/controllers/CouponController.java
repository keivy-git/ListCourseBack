package be.kevin.ListCourse.api.controllers;

import be.kevin.ListCourse.dto.CouponDTO;
import be.kevin.ListCourse.entities.Coupon;
import be.kevin.ListCourse.entities.User;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.mapper.CouponMapper;
import be.kevin.ListCourse.service.CouponService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@RestController
@RequestMapping("/api-coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponMapper couponMapper;

    /**
     * Affiche les coupons déjà dans la bdd
     */
    @GetMapping("/")
    public ResponseEntity<List<Coupon>> getAll(){
        return ResponseEntity.ok(this.couponService.getAll());
    }

    /**Créer ses propre coupons via formulaire*/
    @PostMapping("create")
    public ResponseEntity<Coupon> create (@RequestBody Coupon coupon) {
        return ResponseEntity.ok(couponService.create(coupon));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Coupon> update(@PathVariable(value = "id") Long idCoupon, @RequestBody Coupon update) {
        return ResponseEntity.ok(this.couponService.update(update.getIdCoupon(), update.getName() ,update.getDescription(), update.getDateBegin(), update.getDateEnd()));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long idCoupon) throws NotDeleteException {
        couponService.delete(idCoupon);

        return ResponseEntity.ok(Boolean.TRUE) ;
    }



}
