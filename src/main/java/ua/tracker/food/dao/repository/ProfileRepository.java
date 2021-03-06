package ua.tracker.food.dao.repository;

import ua.tracker.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.tracker.food.dao.entity.PrivilegeEntity;
import ua.tracker.food.dao.entity.ProfileEntity;

import java.util.List;

public class ProfileRepository extends AbstractObjectRelationMapper<ProfileEntity,Integer> {

    private static ProfileRepository instance = null;

    private ProfileRepository(){};

    public synchronized static ProfileRepository getInstance(){
        if (instance == null){
            instance = new ProfileRepository();
        }
        return instance;
    }

    @Override
    protected Class<ProfileEntity> getMappedClass() {
        return ProfileEntity.class;
    }

    @Override
    protected String getTableName() {
        return "profile";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }

    public ProfileEntity findByUserId(Integer userId){
        return findOneQuery("SELECT * FROM profile WHERE user_id LIKE ?",userId);
    }

    public ProfileEntity findByEmail(String email){
        return findOneQuery("SELECT * FROM profile WHERE email LIKE ?",email);
    }
}
