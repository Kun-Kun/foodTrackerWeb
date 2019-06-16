package ua.epam.food.services;

import ua.epam.food.core.security.data.*;
import ua.epam.food.core.security.data.impl.SimplePrivilege;
import ua.epam.food.core.security.data.impl.SimpleRole;
import ua.epam.food.core.security.data.impl.SimpleUser;
import ua.epam.food.core.security.data.impl.SimpleUserDetail;
import ua.epam.food.dao.entity.ProfileEntity;
import ua.epam.food.dao.repository.ProfileRepository;
import ua.epam.food.dto.Profile;
import ua.epam.food.dao.entity.RoleEntity;
import ua.epam.food.dao.entity.UserEntity;
import ua.epam.food.dao.repository.PrivilegeRepository;
import ua.epam.food.dao.repository.RoleRepository;
import ua.epam.food.dao.repository.UserRepository;
import ua.epam.food.exception.UsernameNotFoundException;
import ua.epam.food.mapper.ProfileMapper;
import ua.epam.food.mapper.ProfileMapperImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailServiceImpl implements UserDetailService{

    private UserRepository userRepository = UserRepository.getInstance();
    private RoleRepository roleRepository = RoleRepository.getInstance();
    private PrivilegeRepository privilegeRepository = PrivilegeRepository.getInstance();
    private ProfileRepository profileRepository = ProfileRepository.getInstance();
    private ProfileMapper profileMapper = new ProfileMapperImpl();
    private static UserDetailServiceImpl instance = null;

    private UserDetailServiceImpl(){};

    public synchronized static UserDetailServiceImpl getInstance(){
        if (instance == null){
            instance = new UserDetailServiceImpl();
        }
        return instance;
    }


    public User loadUserByUserEntity(UserEntity user)
            throws UsernameNotFoundException {

        List<RoleEntity> roleEntities = roleRepository.findAllByUserId(user.getId());
        List<Privilege> privileges = getUserPrivileges(roleEntities);
        List<Role> roles = getUserRoles(roleEntities);
        return new SimpleUser(user.getUsername(),user.isEnabled(),roles, privileges,true);
    }

    public Profile loadProfileByUserEntity(UserEntity user){
        ProfileEntity entity = profileRepository.findByUserId(user.getId());
        return profileMapper.entityToDto(entity);
    }

    @Override
    public UserDetail loadUserByUsername(String username)
            throws UsernameNotFoundException {

        SimpleUserDetail userDetail = new SimpleUserDetail();

        if (username==null){
            userDetail.setUser(getGuestUser());
            return userDetail;
        }

        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            userDetail.setUser(getGuestUser());
            return userDetail;
        }else {
            userDetail.setUser(loadUserByUserEntity(user));
            userDetail.setProfile(loadProfileByUserEntity(user));
            return userDetail;
        }

    }

    private User getGuestUser(){
        String guestRoleName = "GUEST_ROLE";
        return new SimpleUser("Guest",
                true,
                Arrays.asList( new SimpleRole(guestRoleName)),
                getRolePrivilege(guestRoleName),
                false);
    }

    private List<Privilege> getRolePrivilege(String roleName){
        List<RoleEntity> roles = Arrays.asList(roleRepository.findByName(roleName));
        return getUserPrivileges(roles);
    }

    private List<Privilege> getUserPrivileges(Collection<RoleEntity> roles) {
        return roles.stream()
                .map(roleEntity -> privilegeRepository.findAllByRoleId(roleEntity.getId()))
                .flatMap(List::stream)
                .distinct()
                .map(privilegeEntity -> new SimplePrivilege(privilegeEntity.getName()))
                .collect(Collectors.toList());

    }

    private List<Role> getUserRoles(Collection<RoleEntity> roles) {
        return roles.stream()
                .map(roleEntity -> new SimpleRole(roleEntity.getName()))
                .collect(Collectors.toList());
    }

}
