package ua.tracker.food.dao.repository;

import ua.tracker.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.tracker.food.dao.entity.DailyGoalEntity;

public class EatenRecordRepository extends AbstractObjectRelationMapper<DailyGoalEntity,Integer> {

    private static EatenRecordRepository instance = null;

    private EatenRecordRepository(){};

    public synchronized static EatenRecordRepository getInstance(){
        if (instance == null){
            instance = new EatenRecordRepository();
        }
        return instance;
    }

    @Override
    protected Class<DailyGoalEntity> getMappedClass() {
        return DailyGoalEntity.class;
    }

    @Override
    protected String getTableName() {
        return "eaten";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }


}
