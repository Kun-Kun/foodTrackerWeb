package ua.epam.food.dao.repository;

import ua.epam.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.epam.food.dao.entity.PhysicalLoadLevelEntity;
import ua.epam.food.dao.entity.PrivilegeEntity;

import java.util.List;

public class PhysicalLoadLevelRepository extends AbstractObjectRelationMapper<PhysicalLoadLevelEntity,Integer> {

    private static PhysicalLoadLevelRepository instance = null;

    private PhysicalLoadLevelRepository(){};

    public synchronized static PhysicalLoadLevelRepository getInstance(){
        if (instance == null){
            instance = new PhysicalLoadLevelRepository();
        }
        return instance;
    }

    @Override
    protected Class<PhysicalLoadLevelEntity> getMappedClass() {
        return PhysicalLoadLevelEntity.class;
    }

    @Override
    protected String getTableName() {
        return "physical_load_level";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }


}
