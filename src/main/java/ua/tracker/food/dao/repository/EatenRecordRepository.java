package ua.tracker.food.dao.repository;

import ua.tracker.food.component.RepastType;
import ua.tracker.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.tracker.food.dao.entity.DailyGoalEntity;
import ua.tracker.food.dao.entity.EatenRecordEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EatenRecordRepository extends AbstractObjectRelationMapper<EatenRecordEntity,Integer> {

    private static EatenRecordRepository instance = null;

    private EatenRecordRepository(){};

    public synchronized static EatenRecordRepository getInstance(){
        if (instance == null){
            instance = new EatenRecordRepository();
        }
        return instance;
    }

    @Override
    protected Class<EatenRecordEntity> getMappedClass() {
        return EatenRecordEntity.class;
    }

    @Override
    protected String getTableName() {
        return "eaten";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }

    public List<EatenRecordEntity> findAllByEventDateAndUserIdAndRepastType(Date eventDate, Integer userId, RepastType repastType){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(eventDate);
        return findAllQuery("SELECT e.* FROM tracker.eaten AS e LEFT JOIN tracker.daily_goal AS dg ON e.daily_goal_id = dg.id WHERE dg.event_date = ? AND dg.user_id = ? AND e.repast_type = ?",strDate,userId,repastType.getId());
    }


}
