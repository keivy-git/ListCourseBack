package be.kevin.ListCourse.service;

import be.kevin.ListCourse.dto.ProductDTO;
import be.kevin.ListCourse.entities.Category;
import be.kevin.ListCourse.entities.Product;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import be.kevin.ListCourse.mapper.ProductMapper;
import be.kevin.ListCourse.repository.ProductRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



    public ProductDTO create(ProductDTO productDTO) {
        return productMapper.toDto(this.productRepository.save(productMapper.toEntity(productDTO)));
    }

    public List<ProductDTO> getAll(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map((productMapper::toDto))
                .collect(Collectors.toList());
    }

    public ProductDTO getOneById(Long idProduct) throws NotFoundException {
        return productMapper.toDto(this.productRepository.findById(idProduct)
                .orElseThrow(() -> new NotFoundException("L'id du produit n'a pas été trouvé")));
    }

    public ProductDTO update(Long idProduct, String name, int quantity, int poids, Set<Category> category) throws NotUpdateException {
        Optional<Product> optional = this.productRepository.findById(idProduct);
        if (this.productRepository.existsById(idProduct)) {
            Product toUpdate = optional.get();
            toUpdate.setName(name);
            toUpdate.setQuantity(quantity);
            toUpdate.setPoids(poids);
            toUpdate.setCategories(category);
            return productMapper.toDto(this.productRepository.save(toUpdate));
        } else {
            throw new NotUpdateException();
        }
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
