package be.kevin.ListCourse.api.controllers;

import be.kevin.ListCourse.dto.ProductDTO;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import be.kevin.ListCourse.service.ProductService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost"})
@RestController
@RequestMapping("/api-product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAll(){
        return ResponseEntity.ok(this.productService.getAll());
    }

    /**Créer ses propre coupons via formulaire*/
    @PostMapping("create")
    public ResponseEntity<ProductDTO> create (@RequestBody ProductDTO productDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productDTO));

    }
    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getOneById(@PathVariable(value = "id") Long idProduct) throws NotFoundException {
       ProductDTO productDTO = productService.getOneById(idProduct);
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.FOUND);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable(value = "id") Long idProduct, @RequestBody ProductDTO update) throws NotUpdateException {
        return ResponseEntity.ok(this.productService.update(idProduct, update.getName() ,update.getQuantity(), update.getPoids(), update.getCategories()));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long idProduct) throws NotDeleteException {
        productService.delete(idProduct);
        return ResponseEntity.ok(Boolean.TRUE) ;
    }
}
