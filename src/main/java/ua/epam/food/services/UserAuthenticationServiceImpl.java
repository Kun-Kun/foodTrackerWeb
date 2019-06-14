package ua.epam.food.services;

import ua.epam.food.core.security.PasswordEncoder;
import ua.epam.food.core.security.ShaPasswordEncoder;
import ua.epam.food.dao.entity.UserEntity;
import ua.epam.food.dao.repository.UserRepository;

import javax.servlet.http.HttpSession;

public class UserAuthenticationServiceImpl implements UserAuthenticationService {

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
        String sessionUsername = (String)session.getAttribute("username");
        if (sessionUsername == null){
            UserEntity userEntity = userRepository.findByUsername(username);
            if(userEntity==null){
                System.out.println("User not Found");
                return false;
            }else if (!userEntity.isEnabled()){
                System.out.println("User is disabled");
                return false;
            }else{
                boolean isPasswordCorrect = hashGenerator.isHashedTextMatch(password,userEntity.getPassword());
                if (isPasswordCorrect){
                    System.out.println("Password is correct");
                    session.setAttribute("username",userEntity.getUsername());
                    session.setMaxInactiveInterval(3600);
                    return true;
                }else {
                    System.out.println("Password isn't correct");
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
        }
    }
}
