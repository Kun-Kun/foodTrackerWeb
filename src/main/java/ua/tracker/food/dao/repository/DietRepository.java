package ua.tracker.food.dao.repository;

import ua.tracker.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.tracker.food.dao.entity.DietEntity;
import ua.tracker.food.dao.entity.PrivilegeEntity;

import java.util.List;

public class DietRepository extends AbstractObjectRelationMapper<DietEntity,Integer> {

    private static DietRepository instance = null;

    private DietRepository(){};

    public synchronized static DietRepository getInstance(){
        if (instance == null){
            instance = new DietRepository();
        }
        return instance;
    }

    @Override
    protected Class<DietEntity> getMappedClass() {
        return DietEntity.class;
    }

    @Override
    protected String getTableName() {
        return "diet";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }


}
