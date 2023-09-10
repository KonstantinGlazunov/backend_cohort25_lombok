package repositories;

import java.util.List;

public interface CrudRepository <T>{

    T findByID(Long id);

    List<T> findAll();

    void save(T model);

    void deleteById(Long id);

    void update(T model);

}
