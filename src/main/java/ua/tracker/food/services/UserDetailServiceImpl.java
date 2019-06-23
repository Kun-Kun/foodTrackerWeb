package ua.tracker.food.services;

import java.util.ArrayList;
import ua.tracker.food.core.security.data.Privilege;
import ua.tracker.food.core.security.data.Role;
import ua.tracker.food.core.security.data.User;
import ua.tracker.food.core.security.data.UserDetail;
import ua.tracker.food.core.security.data.impl.SimplePrivilege;
import ua.tracker.food.core.security.data.impl.SimpleRole;
import ua.tracker.food.core.security.data.impl.SimpleUser;
import ua.tracker.food.core.security.data.impl.SimpleUserDetail;
import ua.tracker.food.dao.entity.ProfileEntity;
import ua.tracker.food.dao.entity.RoleEntity;
import ua.tracker.food.dao.entity.UserEntity;
import ua.tracker.food.dao.repository.PrivilegeRepository;
import ua.tracker.food.dao.repository.ProfileRepository;
import ua.tracker.food.dao.repository.RoleRepository;
import ua.tracker.food.dao.repository.UserRepository;
import ua.tracker.food.dto.Profile;
import ua.tracker.food.mapper.ProfileMapper;
import ua.tracker.food.mapper.ProfileMapperImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.tracker.food.controller.LoginServlet;
import ua.tracker.food.exception.ApplicationDatabaseException;

public class UserDetailServiceImpl implements UserDetailService {
	private Logger log = LogManager.getLogger(UserDetailServiceImpl.class);
    private static UserDetailServiceImpl instance = null;
    private UserRepository userRepository = UserRepository.getInstance();
    private RoleRepository roleRepository = RoleRepository.getInstance();
    private PrivilegeRepository privilegeRepository = PrivilegeRepository.getInstance();
    private ProfileRepository profileRepository = ProfileRepository.getInstance();
    private ProfileMapper profileMapper = new ProfileMapperImpl();

    private UserDetailServiceImpl() {
    }

    public synchronized static UserDetailServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserDetailServiceImpl();
        }
        return instance;
    }


    private User loadUserByUserEntity(UserEntity user) {
		log.log(Level.INFO, "Loading user roles and privileges");
        List<RoleEntity> roleEntities = roleRepository.findAllByUserId(user.getId());
        List<Privilege> privileges = getUserPrivileges(roleEntities);
        List<Role> roles = getUserRoles(roleEntities);
        return new SimpleUser(user.getUsername(), user.isEnabled(), roles, privileges, true);
    }

    private Profile loadProfileByUserEntity(UserEntity user) {
		log.log(Level.INFO, "Loading user profile");
        ProfileEntity entity = profileRepository.findByUserId(user.getId());
        return profileMapper.entityToDto(entity);
    }

    @Override
    public UserDetail loadUserByUsername(String username) {
	log.log(Level.INFO, "Loading user by username {}" , username);
        SimpleUserDetail userDetail = new SimpleUserDetail();

        if (username == null) {
			log.log(Level.INFO, "Username is null");
            userDetail.setUser(getGuestUser());
            return userDetail;
        }

        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
			log.log(Level.INFO, "User with username {} not found" , username);
            userDetail.setUser(getGuestUser());
            return userDetail;
        } else {
            userDetail.setUser(loadUserByUserEntity(user));
            userDetail.setProfile(loadProfileByUserEntity(user));
            return userDetail;
        }

    }

    private User getGuestUser() {
		log.log(Level.INFO, "Setting up user roles and privileges as guest");
        String guestRoleName = "GUEST_ROLE";
        return new SimpleUser("Guest",
                true,
                Arrays.asList(new SimpleRole(guestRoleName)),
                getRolePrivilege(guestRoleName),
                false);
    }

    private List<Privilege> getRolePrivilege(String roleName) {
		log.log(Level.INFO, "Loading user privileges by role name {}",roleName);
		try{
			List<RoleEntity> roles = Arrays.asList(roleRepository.findByName(roleName));
			log.log(Level.INFO, "Loaded user privileges: {}",roles);
			return getUserPrivileges(roles);
		}catch (ApplicationDatabaseException ade){
			log.error("Database error",ade);
			return new ArrayList<>();
		}
    }

    private List<Privilege> getUserPrivileges(Collection<RoleEntity> roles) {
		log.log(Level.INFO, "Loading user privileges by roles {}",roles);
        return roles.stream()
                .map(roleEntity -> privilegeRepository.findAllByRoleId(roleEntity.getId()))
                .flatMap(List::stream)
                .distinct()
                .map(privilegeEntity -> new SimplePrivilege(privilegeEntity.getName()))
                .collect(Collectors.toList());

    }

    private List<Role> getUserRoles(Collection<RoleEntity> roles) {
		log.log(Level.INFO, "Mapping roles {}",roles);
        return roles.stream()
                .map(roleEntity -> new SimpleRole(roleEntity.getName()))
                .collect(Collectors.toList());
    }

}
