package edu.cs544.team5.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface AbstractService<T> {

    // read - one

    T findById(final int id);

    // read - all

    List<T> findAll();

    Page<T> findPaginated(int page, int size);

    // write

    T create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final int entityId);

}
