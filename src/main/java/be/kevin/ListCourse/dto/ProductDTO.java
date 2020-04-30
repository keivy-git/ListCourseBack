package be.kevin.ListCourse.dto;


import lombok.Data;

@Data
public class ProductDTO  {

    private Long idProduct;
    private String name;
    private int quantity;
    private int poids;

    public ProductDTO(Long idProduct, String name, int quantity, int poids) {
        this.idProduct = idProduct;
        this.name = name;
        this.quantity = quantity;
        this.poids = poids;
    }
}
