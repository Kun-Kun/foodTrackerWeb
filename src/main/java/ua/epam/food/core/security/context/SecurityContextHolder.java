package ua.epam.food.core.security.context;

import ua.epam.food.core.security.data.UserDetail;

public class SecurityContextHolder {
    private static SecurityContextHolder instance;

    private SecurityContextHolder() {}

    public static synchronized SecurityContextHolder getInstance(){
        if (instance==null){
            instance = new SecurityContextHolder();
        }
        return instance;
    }
    private ThreadLocal<UserDetail> data = new ThreadLocal<UserDetail>();

    public UserDetail getSecurityData() {
        return data.get();
    }

    public void setSecurityData(UserDetail user) {
        this.data.set(user);
    }
}
