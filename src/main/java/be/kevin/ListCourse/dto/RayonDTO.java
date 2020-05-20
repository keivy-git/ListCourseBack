package be.kevin.ListCourse.dto;


import lombok.Data;

@Data
public class RayonDTO  {

    private Long idRayon;
    private String name;

    /**
     * Constructeur RayonDTO
     * @param idRayon
     * @param name
     */
    public RayonDTO(Long idRayon, String name) {
        this.idRayon = idRayon;
        this.name = name;
    }
}
