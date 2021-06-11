package ua.training.model.dao;

import java.util.List;

public interface GenericDAO<T> {

    T create(T entity);

    T findById(Long id);

    List<T> findAll();

    T update(T entity);

    void delete(Long id);

}
