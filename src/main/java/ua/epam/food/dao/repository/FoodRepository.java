package ua.epam.food.dao.repository;

import ua.epam.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.epam.food.dao.entity.DietEntity;
import ua.epam.food.dao.entity.FoodEntity;

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


}
