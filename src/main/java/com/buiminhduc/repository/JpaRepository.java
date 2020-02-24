package com.buiminhduc.repository;

import com.buiminhduc.paging.Pageable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface JpaRepository<T, ID> {

    <S extends T> S insert(T entity) throws SQLException;

    void update(T entity) throws SQLException;

    <S extends T> S findById(ID id);

    <S extends T> List<S> findAll(Pageable pageable) throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException;

    long count() throws SQLException;

    void delete(ID id) throws NoSuchFieldException;

    <S extends T> List<S> findByProperties(Map<String , Object> filter);

    <S extends T> List<S> findAll();

    void update(T entity, int ID) throws SQLException;
}
