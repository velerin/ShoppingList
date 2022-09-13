package com.example.shoppingList.constants;

public enum Roles {


    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER");

    public final String value;

    Roles(String roleUser) {
        this.value = roleUser;
    }
}
