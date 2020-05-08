package be.kevin.ListCourse.dto;


import be.kevin.ListCourse.entities.Category;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ProductDTO  {

    private Long idProduct;
    private String name;
    private int quantity;
    private int poids;
    private Set<Category> categories = new HashSet<>();

    public ProductDTO(Long idProduct, String name, int quantity, int poids, Set<Category> categories) {
        this.idProduct = idProduct;
        this.name = name;
        this.quantity = quantity;
        this.poids = poids;
        this.categories = categories;
    }
}
