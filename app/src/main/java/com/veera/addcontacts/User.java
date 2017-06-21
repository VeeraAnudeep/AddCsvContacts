package com.veera.addcontacts;

/**
 * Created by veera on 20/06/17.
 */

public class User {
    private String name;
    private String phone;
    private boolean isSelected;

    public User(String displayName, String phone, String photoUri) {
        this.name = displayName;
        this.phone = phone;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
