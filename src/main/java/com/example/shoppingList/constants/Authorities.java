package com.example.shoppingList.constants;

public enum Authorities {


    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER");

    public final String value;

    Authorities(String roleUser) {
        this.value = roleUser;
    }

    public String getShortName(){
        return value.substring(value.indexOf("_")+1);
    }
}
