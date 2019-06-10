package ua.epam.food.core.db.mapper.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.epam.food.core.db.mapper.Mapper;

import java.util.Collection;

public class MapperImpl implements Mapper {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return mapper.convertValue(fromValue,toValueType);
    }

    @Override
    public <T extends Collection> T convertCollectionType(Object fromValue, Class<T> collectionClass, Class<?> elementClass) {
        JavaType type = mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
        return  mapper.convertValue(fromValue,type);
    }
}
