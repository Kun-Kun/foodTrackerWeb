package ua.epam.food.core.security.data;

public class SimplePrivilege implements Privilege{

    private String privilege;

    public SimplePrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Override
    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}
