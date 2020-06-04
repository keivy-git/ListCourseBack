package be.kevin.ListCourse.mapper;

public interface GenericMapper<TDTO, TENTITY> {

    TDTO toDto(TENTITY tentity);
    TENTITY toEntity(TDTO tdto);

}
