package be.kevin.ListCourse.api.controllers;

import be.kevin.ListCourse.dto.CouponDTO;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import be.kevin.ListCourse.service.CouponService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@RestController
@RequestMapping("/api-coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * Affiche les coupons déjà dans la bdd
     */
    @GetMapping("/")
    public ResponseEntity<List<CouponDTO>> getAll(){
        return ResponseEntity.ok(this.couponService.getAll());
    }

    /**Créer ses propre coupons via formulaire*/
    @PostMapping("create")
    public ResponseEntity<CouponDTO> create (@RequestBody CouponDTO couponDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(couponService.create(couponDTO));
    }
    @GetMapping("{id}")
    public ResponseEntity<CouponDTO> getOneById(@PathVariable(value = "id") Long idCoupon) throws NotFoundException {
        CouponDTO couponDTO = couponService.getOneById(idCoupon);
        return new ResponseEntity<CouponDTO>(couponDTO, HttpStatus.FOUND);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<CouponDTO> update(@PathVariable(value = "id") Long idCoupon, @RequestBody CouponDTO update) throws NotUpdateException {
        return ResponseEntity.ok(this.couponService.update(idCoupon, update.getName() ,update.getDescription(), update.getDateBegin(), update.getDateEnd()));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long idCoupon) throws NotDeleteException {
        couponService.delete(idCoupon);
        return ResponseEntity.ok(Boolean.TRUE) ;
    }



}
