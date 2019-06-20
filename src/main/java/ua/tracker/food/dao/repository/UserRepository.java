package ua.tracker.food.dao.repository;

import ua.tracker.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.tracker.food.dao.entity.ProfileEntity;
import ua.tracker.food.dao.entity.UserEntity;

public class UserRepository extends AbstractObjectRelationMapper<UserEntity,Integer> {

    private static UserRepository instance = null;

    private UserRepository(){};

    public synchronized static UserRepository getInstance(){
        if (instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    @Override
    protected Class<UserEntity> getMappedClass() {
        return UserEntity.class;
    }

    @Override
    protected String getTableName() {
        return "user";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }

    public UserEntity findByUsername(String username){
        return findOneQuery("SELECT * FROM user WHERE username LIKE ?",username);
    }

}
