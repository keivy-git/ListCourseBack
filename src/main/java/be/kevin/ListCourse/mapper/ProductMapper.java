package be.kevin.ListCourse.mapper;


import be.kevin.ListCourse.dto.ProductDTO;
import be.kevin.ListCourse.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements GenericMapper<ProductDTO, Product> {


    @Override
    public ProductDTO toDto(Product product) {
        return new ProductDTO(
                product.getIdProduct(),
                product.getName(),
                product.getPoids(),
                product.getQuantity(),
                product.getCategories()

        );
    }
    @Override
    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setIdProduct(productDTO.getIdProduct());
        product.setName(productDTO.getName());
        product.setQuantity(productDTO.getQuantity());
        product.setPoids(productDTO.getPoids());
        product.setCategories(productDTO.getCategories());
        return product;
    }
}
