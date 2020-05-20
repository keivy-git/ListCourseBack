package be.kevin.ListCourse.api.controllers;

import be.kevin.ListCourse.dto.CouponDTO;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import be.kevin.ListCourse.service.CouponServiceImpl;
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

    private CouponServiceImpl couponService;

    @Autowired
    public CouponController(CouponServiceImpl couponService) {
        this.couponService = couponService;
    }

    /**
     * méthode qui permet d'affiche les coupons déjà dans la bdd
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<CouponDTO>> getAll(){
        return ResponseEntity.ok(this.couponService.getAll());
    }

    /**
     * méthode qui permet de créer ses propre coupons via formulair
     * @param couponDTO
     * @return
     * @throws NotFoundException
     */
    @PostMapping("create")
    public ResponseEntity<CouponDTO> create (@RequestBody CouponDTO couponDTO) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(couponService.create(couponDTO));
    }
    /**
     * méthode qui permet d'afficher un coupon via son id
     * @param idCoupon
     * @return
     * @throws NotFoundException
     */
    @GetMapping("{id}")
    public ResponseEntity<CouponDTO> getOneById(@PathVariable(value = "id") Long idCoupon) throws NotFoundException {
        CouponDTO couponDTO = couponService.getOneById(idCoupon);
        return new ResponseEntity<CouponDTO>(couponDTO, HttpStatus.FOUND);
    }

    /**
     * méthode qui permet de mettre à jour un coupon via son id
     * @param idCoupon
     * @param update
     * @return
     * @throws NotUpdateException
     */
    @PutMapping("update/{id}")
    public ResponseEntity<CouponDTO> update(@PathVariable(value = "id") Long idCoupon, @RequestBody CouponDTO update) throws NotUpdateException {
        return ResponseEntity.ok(this.couponService.update(idCoupon, update.getName() ,update.getDescription(), update.getDateBegin(), update.getDateEnd()));
    }

    /**
     * méthode qui permet de supprimer un coupon
     * @param idCoupon
     * @return
     * @throws NotDeleteException
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long idCoupon) throws NotDeleteException {
        couponService.delete(idCoupon);
        return ResponseEntity.ok(Boolean.TRUE) ;
    }
}
