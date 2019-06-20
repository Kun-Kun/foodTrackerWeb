package ua.tracker.food.dao.repository;

import ua.tracker.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.tracker.food.dao.entity.PrivilegeEntity;
import ua.tracker.food.dao.entity.SexEntity;

import java.util.List;

public class SexRepository extends AbstractObjectRelationMapper<SexEntity,Integer> {

    private static SexRepository instance = null;

    private SexRepository(){};

    public synchronized static SexRepository getInstance(){
        if (instance == null){
            instance = new SexRepository();
        }
        return instance;
    }

    @Override
    protected Class<SexEntity> getMappedClass() {
        return SexEntity.class;
    }

    @Override
    protected String getTableName() {
        return "sex";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }

}
