package be.kevin.ListCourse.mapper;


import be.kevin.ListCourse.dto.ProductDTO;
import be.kevin.ListCourse.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements GenericMapper<ProductDTO, Product> {


    @Override
    public ProductDTO toDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setIdProduct(product.getIdProduct());
        productDTO.setName(product.getName());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setPoids(product.getPoids());
        return productDTO;
    }

    @Override
    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setIdProduct(productDTO.getIdProduct());
        product.setName(productDTO.getName());
        product.setQuantity(productDTO.getQuantity());
        product.setPoids(productDTO.getPoids());
        return product;
    }
}
