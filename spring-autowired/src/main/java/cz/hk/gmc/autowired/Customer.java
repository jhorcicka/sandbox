package cz.hk.gmc.autowired;

import org.springframework.beans.factory.annotation.Autowired;

public class Customer {
    @Autowired
    private Person person;

    private int type;
    private String action;

    public int getType() {
        return type;
    }

    public void setType(final int type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "person=" + person +
                ", type=" + type +
                ", action='" + action + '\'' +
                '}';
    }
}
