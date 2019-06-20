package ua.tracker.food.dao.repository;

import ua.tracker.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.tracker.food.dao.entity.DietEntity;
import ua.tracker.food.dao.entity.FoodEntity;

import java.util.List;

public class FoodRepository extends AbstractObjectRelationMapper<FoodEntity,Integer> {

    private static FoodRepository instance = null;

    private FoodRepository(){};

    public synchronized static FoodRepository getInstance(){
        if (instance == null){
            instance = new FoodRepository();
        }
        return instance;
    }

    @Override
    protected Class<FoodEntity> getMappedClass() {
        return FoodEntity.class;
    }

    @Override
    protected String getTableName() {
        return "food";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }

    public List<FoodEntity> findAllByTitleOrDescriptionContainsIgnoreCaseAndNotDeletedAndDefaultRecord(String text){
        return findAllQuery("SELECT * FROM food WHERE deleted=false AND default_record = true AND (lower(title) LIKE lower(concat('%', ?,'%')) OR lower(description) LIKE lower(concat('%', ?,'%'))) LIMIT 20",text,text);
    }
    public List<FoodEntity> findAllByTitleOrDescriptionContainsIgnoreCaseAndNotDeletedAndDefaultAndUserRecord(String text,Integer userId){
        return findAllQuery("SELECT * FROM food WHERE deleted=false AND (default_record = true OR creator_id=?) AND (lower(title) LIKE lower(concat('%', ?,'%')) OR lower(description) LIKE lower(concat('%', ?,'%'))) LIMIT 20",userId,text,text);
    }
}
