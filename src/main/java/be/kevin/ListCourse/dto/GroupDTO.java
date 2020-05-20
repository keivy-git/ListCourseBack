package be.kevin.ListCourse.dto;


import lombok.*;

@Data
public class GroupDTO {

    private Long idGroup;
    private String labelGroup;

    /**
     * constructeur GroupDTO
     * @param idGroup
     * @param labelGroup
     */
    public GroupDTO(Long idGroup, String labelGroup) {
        this.idGroup = idGroup;
        this.labelGroup = labelGroup;
    }
}
