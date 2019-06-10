package ua.epam.food.core.db.orm;

import ua.epam.food.exception.WrongInputException;
import ua.epam.food.exception.WrongResultException;


import java.util.List;


public interface ObjectRelationMapper<E, ID> {
    E save(E entity);

    List<E> saveAll(List<E> entities);

    E findOne(ID id) throws WrongResultException;

    List<E> findAll();

    void deleteAll();

    void deleteAll(List<E> entities);

    void deleteById(ID id) throws WrongInputException;

    void delete(E entity) throws WrongInputException;

    boolean existsById(ID id);

    <T> T getValuesQuery(String sql, Class<T> clazz, Object... param);

    <T> T getSingleValueQuery(String sql, Class<T> clazz, Object... param);

    List<E> findAllQuery(String sql, Object... param);

    E findOneQuery(String sql, Object... param) throws WrongResultException;

    int updateQuery(String sql, Object... param);

    int updateQuery(String sql, List<Object> param);

    List<ID> updateQueryAffectedIds(String sql, List<Object> param);

    List<ID> updateQueryAffectedIds(String sql, Object... param);

    long count();

}
