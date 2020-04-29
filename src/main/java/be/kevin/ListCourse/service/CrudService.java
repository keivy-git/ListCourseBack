package be.kevin.ListCourse.service;


import java.util.List;
import java.util.Optional;

public interface CrudService<TENTITY, TID>{

    List<TENTITY> getAll();
    Optional<TENTITY> getOneById(TID id);
    int create (TENTITY entity);
    boolean update (TENTITY entity, TID id);
    boolean delete(TID id);


}
