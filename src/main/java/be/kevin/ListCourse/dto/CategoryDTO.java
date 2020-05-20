package be.kevin.ListCourse.dto;


import lombok.Data;

/**
 * Foncionnalité future.
 */
@Data
public class CategoryDTO {

    private Long id;
    private String name;

    /**
     * Constructeur CategoryDTO
     * @param id
     * @param name
     */
    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
