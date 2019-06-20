package ua.tracker.food.core.db.mapper;

import java.util.Collection;

public interface Mapper {
    <T> T convertValue(Object fromValue, Class<T> toValueType);
    <T extends Collection> T convertCollectionType(Object fromValue,Class<T> collectionClass, Class<?> elementClass);
}
