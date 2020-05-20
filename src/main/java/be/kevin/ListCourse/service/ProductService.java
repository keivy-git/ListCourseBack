package be.kevin.ListCourse.service;

import be.kevin.ListCourse.dto.ProductDTO;
import be.kevin.ListCourse.entities.Category;
import be.kevin.ListCourse.exceptionHandler.NotDeleteException;
import be.kevin.ListCourse.exceptionHandler.NotUpdateException;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ProductService {

    ProductDTO create(ProductDTO ProductDTO);
    List<ProductDTO> getAll();
    ProductDTO getOneById(Long idUser) throws NotFoundException;
    ProductDTO update(Long idProduct, String name, int quantity, int poids, Set<Category> category) throws NotUpdateException;
    void delete(Long idUser) throws NotDeleteException;


}
