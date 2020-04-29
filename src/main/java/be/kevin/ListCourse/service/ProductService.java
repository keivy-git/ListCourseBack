package be.kevin.ListCourse.service;

import be.kevin.ListCourse.repository.ProductRepository;
import be.kevin.ListCourse.dto.ProductDTO;
import be.kevin.ListCourse.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements CrudService<ProductDTO, Long> {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<ProductDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getOneById(Long id) {
        return Optional.empty();
    }

    @Override
    public int create(ProductDTO entity) {
        return 0;
    }

    @Override
    public boolean update(ProductDTO entity, Long id) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
