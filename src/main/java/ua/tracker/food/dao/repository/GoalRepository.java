package ua.tracker.food.dao.repository;

import ua.tracker.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.tracker.food.dao.entity.GoalEntity;
import ua.tracker.food.dao.entity.PrivilegeEntity;

import java.util.List;

public class GoalRepository extends AbstractObjectRelationMapper<GoalEntity,Integer> {

    private static GoalRepository instance = null;

    private GoalRepository(){};

    public synchronized static GoalRepository getInstance(){
        if (instance == null){
            instance = new GoalRepository();
        }
        return instance;
    }

    @Override
    protected Class<GoalEntity> getMappedClass() {
        return GoalEntity.class;
    }

    @Override
    protected String getTableName() {
        return "goal";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }


}
