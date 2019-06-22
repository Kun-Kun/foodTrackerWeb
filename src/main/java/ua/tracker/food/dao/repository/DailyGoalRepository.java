package ua.tracker.food.dao.repository;

import ua.tracker.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.tracker.food.dao.entity.DailyGoalEntity;
import ua.tracker.food.dao.entity.DietEntity;
import ua.tracker.food.exception.InvalidResultException;

import java.util.Date;

public class DailyGoalRepository extends AbstractObjectRelationMapper<DailyGoalEntity,Integer> {

    private static DailyGoalRepository instance = null;

    private DailyGoalRepository(){};

    public synchronized static DailyGoalRepository getInstance(){
        if (instance == null){
            instance = new DailyGoalRepository();
        }
        return instance;
    }

    @Override
    protected Class<DailyGoalEntity> getMappedClass() {
        return DailyGoalEntity.class;
    }

    @Override
    protected String getTableName() {
        return "daily_goal";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }

    public DailyGoalEntity findOneByEventDateAndUserId(Date eventDate, Integer userId) throws InvalidResultException {
        return findOneQuery("SELECT * FROM daily_goal WHERE event_date = ? AND user_id = ?",eventDate,userId);
    }
}
