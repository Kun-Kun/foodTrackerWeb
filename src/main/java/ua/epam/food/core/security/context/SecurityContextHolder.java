package ua.epam.food.core.security.context;

public class SecurityContextHolder {
    private static SecurityContextHolder instance;

    private SecurityContextHolder() {}

    public synchronized SecurityContextHolder getInstance(){
        if (instance==null){
            instance = new SecurityContextHolder();
        }
        return instance;
    }
    private ThreadLocal<SecurityData> data = new ThreadLocal<SecurityData>();

    public SecurityData getSecurityData() {
        return data.get();
    }

    public void setSecurityData(SecurityData userID) {
        this.data.set(userID);
    }
}
