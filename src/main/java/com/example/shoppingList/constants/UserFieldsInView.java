package com.example.shoppingList.constants;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum UserFieldsInView {

    FIRST_NAME("firstName",1,"First Name"),
    LAST_NAME("lastName",2,"Last Name"),
    EMAIL("email",3,"Email");

    public final String label;
    public final String displayName;
    public final int value;
    private static final List<UserFieldsInView> listAll = new LinkedList<>();

    static {
        Collections.addAll(listAll,UserFieldsInView.values());
    }

    UserFieldsInView(String label, int value,String displayName) {
        this.label = label;
        this.value = value;
        this.displayName =displayName;
    }
    public static List<UserFieldsInView> all(){
        return listAll;
    }

    public String displayName() {
        return displayName;
    }

    public int value() {
        return value;
    }
}
