package be.kevin.ListCourse.service;

import be.kevin.ListCourse.entities.Category;
import be.kevin.ListCourse.entities.Product;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.repository.ProductRepository;
import be.kevin.ListCourse.dto.ProductDTO;
import be.kevin.ListCourse.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;



    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        return this.productRepository.save(product);
    }

    public Optional<Product> getOneById(Long idProduct) {
        return this.productRepository.findById(idProduct);
    }


    public Product update(Long idProduct, String name, int quantity, int poids, Set<Category> category) {
        Optional<Product> optionalProduct = this.getOneById(idProduct);
        if (optionalProduct.isEmpty()) {
            return null;
        }
        Product toUpdate = optionalProduct.get();
        toUpdate.setName(name);
        toUpdate.setQuantity(quantity);
        toUpdate.setPoids(poids);
        return this.productRepository.save(toUpdate);
    }

    public void delete(Long idProduct) throws NotDeleteException {
        if (this.productRepository.existsById(idProduct)) {
            this.productRepository.deleteById(idProduct);
        }
        else {
            throw new NotDeleteException();
        }
    }
}
