package ua.tracker.food.dao.repository;

import ua.tracker.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.tracker.food.dao.entity.RoleEntity;

import java.util.List;

public class RoleRepository extends AbstractObjectRelationMapper<RoleEntity,Integer> {

    private static RoleRepository instance = null;

    private RoleRepository(){};

    public synchronized static RoleRepository getInstance(){
        if (instance == null){
            instance = new RoleRepository();
        }
        return instance;
    }

    @Override
    protected Class<RoleEntity> getMappedClass() {
        return RoleEntity.class;
    }

    @Override
    protected String getTableName() {
        return "role";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }
    public RoleEntity findByName(String name){
        return findOneQuery("SELECT * FROM role WHERE name LIKE ?",name);
    }

    public List<RoleEntity> findAllByUserId(Integer userId){
        return findAllQuery("SELECT r.* FROM user_role AS ur RIGHT JOIN role AS r ON ur.user_id = r.id WHERE ur.user_id = ?",userId);
    }
}
