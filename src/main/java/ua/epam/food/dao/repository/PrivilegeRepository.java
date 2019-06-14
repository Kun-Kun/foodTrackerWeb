package ua.epam.food.dao.repository;

import ua.epam.food.core.db.orm.impl.AbstractObjectRelationMapper;
import ua.epam.food.dao.entity.PrivilegeEntity;
import ua.epam.food.dao.entity.RoleEntity;

import java.util.List;

public class PrivilegeRepository extends AbstractObjectRelationMapper<PrivilegeEntity,Integer> {

    private static PrivilegeRepository instance = null;

    private PrivilegeRepository(){};

    public synchronized static PrivilegeRepository getInstance(){
        if (instance == null){
            instance = new PrivilegeRepository();
        }
        return instance;
    }

    @Override
    protected Class<PrivilegeEntity> getMappedClass() {
        return PrivilegeEntity.class;
    }

    @Override
    protected String getTableName() {
        return "privilege";
    }

    @Override
    protected String getIdFieldName() {
        return "id";
    }

    public PrivilegeEntity findByName(String name){
        return findOneQuery("SELECT * FROM privilege WHERE name LIKE ?",name);
    }

    public List<PrivilegeEntity> findAllByRoleId(Integer roleId){
        return findAllQuery("SELECT p.* FROM role_privilege AS rp RIGHT JOIN privilege AS p ON rp.privilege_id = p.id WHERE rp.role_id = ?",roleId);
    }
}
