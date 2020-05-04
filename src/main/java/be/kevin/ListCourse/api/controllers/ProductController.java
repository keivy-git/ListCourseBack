package be.kevin.ListCourse.api.controllers;

import be.kevin.ListCourse.dto.ProductDTO;
import be.kevin.ListCourse.entities.Coupon;
import be.kevin.ListCourse.entities.Product;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.mapper.ProductMapper;
import be.kevin.ListCourse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@RestController
@RequestMapping("/api-product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;


    @GetMapping("/")
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(this.productService.getAll());
    }

    /**Créer ses propre coupons via formulaire*/
    @PostMapping("create")
    public ResponseEntity<Product> create (@RequestBody Product product) {
        return ResponseEntity.ok(productService.create(product));
    }
    @GetMapping("{id}")
    public ResponseEntity<Product> getOneById(@PathVariable(value = "id") Long idCoupon) {
        Optional<Product> optional = productService.getOneById(idCoupon);
        return new ResponseEntity<Product>(optional.get(), HttpStatus.FOUND);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Product> update(@PathVariable(value = "id") Long idProduct, @RequestBody Product update) {
        return ResponseEntity.ok(this.productService.update(idProduct, update.getName() ,update.getQuantity(), update.getPoids(), update.getCategories()));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long idProduct) throws NotDeleteException {
        productService.delete(idProduct);
        return ResponseEntity.ok(Boolean.TRUE) ;
    }
}
