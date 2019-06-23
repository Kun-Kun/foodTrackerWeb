package ua.tracker.food.services;

import ua.tracker.food.core.security.encoder.PasswordEncoder;
import ua.tracker.food.core.security.encoder.impl.ShaPasswordEncoder;
import ua.tracker.food.dao.entity.UserEntity;
import ua.tracker.food.dao.repository.UserRepository;

import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserAuthenticationServiceImpl implements UserAuthenticationService {
	private Logger log = LogManager.getLogger(UserAuthenticationServiceImpl.class);
    private UserRepository userRepository = UserRepository.getInstance();
    private PasswordEncoder hashGenerator = new ShaPasswordEncoder();

    private static UserAuthenticationServiceImpl instance = null;

    private UserAuthenticationServiceImpl(){};

    public synchronized static UserAuthenticationServiceImpl getInstance(){
        if (instance == null){
            instance = new UserAuthenticationServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean login(HttpSession session, String username, String password) {
		log.log(Level.INFO, "Trying to login user with username {} ", username);
        String sessionUsername = (String)session.getAttribute("username");
        if (sessionUsername == null){
			log.log(Level.INFO, "Session not contain authentication information");
            UserEntity userEntity = userRepository.findByUsername(username);
            if(userEntity==null){
				log.log(Level.INFO, "User not Found");
                return false;
            }else if (!userEntity.isEnabled()){
                log.log(Level.INFO, "User is disabled");
                return false;
            }else{
                boolean isPasswordCorrect = hashGenerator.isHashedTextMatch(password,userEntity.getPassword());
                if (isPasswordCorrect){
					log.log(Level.INFO, "Password is correct");
                    session.setAttribute("username",userEntity.getUsername());
                    session.setMaxInactiveInterval(3600);
                    return true;
                }else {
                    log.log(Level.INFO, "Password isn't correct");
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void logout(HttpSession session) {
        if(session != null) {
            session.invalidate();
			log.log(Level.INFO, "Session invalidated");
        }
    }
}
