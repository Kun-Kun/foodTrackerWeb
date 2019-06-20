package ua.tracker.food.core.security.data.impl;

import ua.tracker.food.core.security.data.Privilege;

public class SimplePrivilege implements Privilege {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Privilege that = (Privilege) o;

        return privilege != null ? privilege.equals(that.getPrivilege()) : that.getPrivilege() == null;
    }

    @Override
    public int hashCode() {
        return privilege != null ? privilege.hashCode() : 0;
    }
}
