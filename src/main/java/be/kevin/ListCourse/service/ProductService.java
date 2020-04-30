package be.kevin.ListCourse.service;

import be.kevin.ListCourse.entities.Product;
import be.kevin.ListCourse.repository.ProductRepository;
import be.kevin.ListCourse.dto.ProductDTO;
import be.kevin.ListCourse.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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


    public Optional<Product> getOneById(Long id) {
        return Optional.empty();
    }


    public Product create() {
        return null;
    }


    public Product update() {
        return null;
    }


    public Product delete() {
        return null;
    }
}
