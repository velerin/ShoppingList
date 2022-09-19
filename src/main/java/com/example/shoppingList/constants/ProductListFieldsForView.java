package com.example.shoppingList.constants;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum ProductListFieldsForView {

    TITLE("title",1,"Title");

    private static final List<ProductListFieldsForView> listAll = new LinkedList<>();

    static {
        Collections.addAll(listAll, ProductListFieldsForView.values());
        Collections.sort(listAll);
    }

    private final String label;

    private final int value;
    private final String displayName;

    ProductListFieldsForView(String label, int value, String displayName) {
        this.label = label;
        this.value = value;
        this.displayName = displayName;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<ProductListFieldsForView> all() {
        return listAll;
    }

}
