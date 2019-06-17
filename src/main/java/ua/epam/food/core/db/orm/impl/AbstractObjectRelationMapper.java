package ua.epam.food.core.db.orm.impl;


import ua.epam.food.core.db.CrudQueryExecutorService;
import ua.epam.food.core.db.mapper.Mapper;
import ua.epam.food.core.db.mapper.impl.MapperImpl;
import ua.epam.food.core.db.orm.ObjectRelationMapper;
import ua.epam.food.core.db.tool.KeyStringValueArray;
import ua.epam.food.exception.InvalidInputException;
import ua.epam.food.exception.InvalidResultException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractObjectRelationMapper<E, ID> implements ObjectRelationMapper<E, ID> {

    private CrudQueryExecutorService<ID> queryExecutorService = new CrudQueryExecutorService<>();
    private Mapper mapper = new MapperImpl();

    public E save(E entity) {
        Map<String, Object> map = mapper.convertValue(entity, Map.class);
        KeyStringValueArray array = KeyStringValueArray.convert(map);

        List<ID> databaseIds = queryExecutorService.updateAndReturnAffectedIds("REPLACE INTO " + getTableName() + " (" + array.getFields() + ") values (" + array.getQuestionsSigns() + ")", array.getValues());
        return findOne(databaseIds.get(0));
    }

    public List<E> saveAll(List<E> entities) {
        List<Map<String, Object>> maps = mapper.convertCollectionType(entities, List.class, Map.class);
        List<KeyStringValueArray> parameters = maps.stream()
                .map(map -> KeyStringValueArray.convert(map)).collect(Collectors.toList());
        if (parameters.size() > 0) {
            KeyStringValueArray oneOfValue = parameters.get(0);
            List<Object[]> parameterMatrix = parameters.stream().map(KeyStringValueArray::getValues).collect(Collectors.toList());
            List<ID> databaseIds = queryExecutorService.updateAndReturnAffectedIds("REPLACE INTO " + getTableName() + " (" + oneOfValue.getFields() + ") values (" + oneOfValue.getQuestionsSigns() + ")", parameterMatrix);
            return databaseIds.stream().map(id -> findOne(id)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public E findOne(ID id) throws InvalidResultException {
        List<Map<String, Object>> result = queryExecutorService.find("SELECT * FROM " + getTableName() + " WHERE " + getIdFieldName() + " = ?", id);
        if (result.size() != 1) {
            throw new InvalidResultException();
        }
        return mapper.convertValue(result.get(0), getMappedClass());
    }

    public List<E> findAll() {
        return findAllQuery("SELECT * FROM " + getTableName());
    }

    public void deleteAll() {
        queryExecutorService.update("TRUNCATE TABLE " + getTableName());
    }

    public void deleteAll(List<E> entities) {
        List<Map<String, Object>> maps = mapper.convertCollectionType(entities, List.class, Map.class);
        List<Object[]> ids = maps.stream()
                .map(stringObjectMap -> stringObjectMap.get(getIdFieldName()))
                .filter(key -> key != null)
                .distinct()
                .map(id -> {
                    Object[] idList = new Object[1];
                    idList[0] = id;
                    return idList;
                }).collect(Collectors.toList());
        System.out.println(ids);
        queryExecutorService.update("DELETE FROM " + getTableName() + " WHERE " + getIdFieldName() + " = ?", ids);
    }

    public void deleteById(ID id) throws InvalidInputException {
        if (id == null) {
            throw new InvalidInputException();
        }
        queryExecutorService.update("DELETE FROM " + getTableName() + " WHERE " + getIdFieldName() + " = ?", id);
    }

    public void delete(E entity) throws InvalidInputException {
        Map<String, Object> map = mapper.convertValue(entity, Map.class);
        ID id = (ID) map.get(getIdFieldName());
        deleteById(id);
    }

    public boolean existsById(ID id) {
        List<Map<String, Object>> result = queryExecutorService.find("SELECT * FROM " + getTableName() + " WHERE " + getIdFieldName() + " = ?", id);
        return !result.isEmpty();
    }

    public <T> T getValuesQuery(String sql, Class<T> clazz, Object... param) {
        List<Map<String, Object>> result = queryExecutorService.find(sql, param);
        if (result.size() == 1) {
            return mapper.convertValue(result.get(0), clazz);
        } else if (result.isEmpty()) {
            return null;
        } else throw new InvalidResultException();
    }

    public <T> T getSingleValueQuery(String sql, Class<T> clazz, Object... param) {
        List<Map<String, Object>> result = queryExecutorService.find(sql, param);
        if (result.size() == 1) {
            Object value = result.get(0).values().toArray()[0];
            return mapper.convertValue(value, clazz);
        } else if (result.isEmpty()) {
            return null;
        } else throw new InvalidResultException();
    }

    public List<E> findAllQuery(String sql, Object... param) {
        List<Map<String, Object>> result = queryExecutorService.find(sql, param);
        return mapper.convertCollectionType(result, List.class, getMappedClass());
    }

    public E findOneQuery(String sql, Object... param) throws InvalidResultException {
        List<Map<String, Object>> result = queryExecutorService.find(sql, param);
        if (result.size() == 1) {
            return mapper.convertValue(result.get(0), getMappedClass());
        } else if (result.isEmpty()) {
            return null;
        } else throw new InvalidResultException();
    }


    public int updateQuery(String sql, Object... param) {
        return queryExecutorService.update(sql, param);
    }

    public int updateQuery(String sql, List<Object> param) {
        return queryExecutorService.update(sql, param);
    }

    public List<ID> updateQueryAffectedIds(String sql, List<Object> param) {
        return queryExecutorService.updateAndReturnAffectedIds(sql, param);
    }

    public List<ID> updateQueryAffectedIds(String sql, Object... param) {
        return queryExecutorService.updateAndReturnAffectedIds(sql, param);
    }

    public long count() {
        return getSingleValueQuery("SELECT count(*) FROM " + getTableName(), Long.class);
    }

    protected abstract Class<E> getMappedClass();

    protected abstract String getTableName();

    protected abstract String getIdFieldName();
}
