package be.kevin.ListCourse.api.controllers;

import be.kevin.ListCourse.dto.ProductDTO;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import be.kevin.ListCourse.service.ProductServiceImpl;
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
    private ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    /**
     * méthode qui permet d'affiche la liste des produits enregistrés
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAll(){
        return ResponseEntity.ok(this.productService.getAll());
    }

    /**
     * méthode qui permet de créer sa propre liste de produit
     * @param productDTO
     * @return
     */
    @PostMapping("create")
    public ResponseEntity<ProductDTO> create (@RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productDTO));
    }

    /**
     * méthode qui permet d'affiche un produit créer via son id
     * @param idProduct
     * @return
     * @throws NotFoundException
     */
    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getOneById(@PathVariable(value = "id") Long idProduct) throws NotFoundException {
       ProductDTO productDTO = productService.getOneById(idProduct);
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.FOUND);
    }

    /**
     * méthode qui permet de mettre à jour un id
     * @param idProduct
     * @param update
     * @return
     * @throws NotUpdateException
     */
    @PutMapping("update/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable(value = "id") Long idProduct, @RequestBody ProductDTO update) throws NotUpdateException {
        return ResponseEntity.ok(this.productService.update(idProduct, update.getName() ,update.getQuantity(), update.getPoids(), update.getCategories()));
    }
    /**
     *
     * Méthode qui permet de supprimer un produit
     * @param idProduct
     * @return
     * @throws NotDeleteException
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long idProduct) throws NotDeleteException {
        productService.delete(idProduct);
        return ResponseEntity.ok(Boolean.TRUE) ;
    }
}
