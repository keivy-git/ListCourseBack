package be.kevin.ListCourse.api.controllers;

import be.kevin.ListCourse.entities.Coupon;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.mapper.CouponMapper;
import be.kevin.ListCourse.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(couponService.create(coupon));
    }
    @GetMapping("{id}")
    public ResponseEntity<Coupon> getOneById(@PathVariable(value = "id") Long idCoupon) {
        Optional<Coupon> optional = couponService.getOneById(idCoupon);
        return new ResponseEntity<Coupon>(optional.get(), HttpStatus.FOUND);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Coupon> update(@PathVariable(value = "id") Long idCoupon, @RequestBody Coupon update) {
        return ResponseEntity.ok(this.couponService.update(idCoupon, update.getName() ,update.getDescription(), update.getDateBegin(), update.getDateEnd()));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long idCoupon) throws NotDeleteException {
        couponService.delete(idCoupon);
        return ResponseEntity.ok(Boolean.TRUE) ;
    }



}
