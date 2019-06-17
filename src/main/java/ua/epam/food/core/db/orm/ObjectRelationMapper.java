package ua.epam.food.core.db.orm;

import ua.epam.food.exception.InvalidInputException;
import ua.epam.food.exception.InvalidResultException;


import java.util.List;


public interface ObjectRelationMapper<E, ID> {
    E save(E entity);

    List<E> saveAll(List<E> entities);

    E findOne(ID id) throws InvalidResultException;

    List<E> findAll();

    void deleteAll();

    void deleteAll(List<E> entities);

    void deleteById(ID id) throws InvalidInputException;

    void delete(E entity) throws InvalidInputException;

    boolean existsById(ID id);

    <T> T getValuesQuery(String sql, Class<T> clazz, Object... param);

    <T> T getSingleValueQuery(String sql, Class<T> clazz, Object... param);

    List<E> findAllQuery(String sql, Object... param);

    E findOneQuery(String sql, Object... param) throws InvalidResultException;

    int updateQuery(String sql, Object... param);

    int updateQuery(String sql, List<Object> param);

    List<ID> updateQueryAffectedIds(String sql, List<Object> param);

    List<ID> updateQueryAffectedIds(String sql, Object... param);

    long count();

}
