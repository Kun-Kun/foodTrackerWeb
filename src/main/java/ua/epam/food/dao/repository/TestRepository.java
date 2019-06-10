package ua.epam.food.dao.repository;

import ua.epam.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.epam.food.dao.entity.TestEntity;

public class TestRepository extends AbstractObjectRelationMapper<TestEntity,Integer> {

    @Override
    protected Class<TestEntity> getMappedClass() {
        return TestEntity.class;
    }

    @Override
    protected String getTableName() {
        return "developers";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }
}
