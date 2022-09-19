package com.example.shoppingList.constants;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum ProductsFieldsForView {

    PRODUCT_NAME("product_name",1,"Product Name"),
    AMOUNT("amount",2,"Amount"),
    PRICE_PER_PIECE("price_per_piece",3,"Price per piece"),
    CURRENCY("currency",4,"Currency");

    private final String label;

    private final int value;
    private final String displayName;

    ProductsFieldsForView(String label, int value, String displayName) {
        this.label = label;
        this.value = value;
        this.displayName = displayName;
    }

    public int getValue() {
        return value;
    }

    private static final List<ProductsFieldsForView> listAll = new LinkedList<>();

    static {
        Collections.addAll(listAll, ProductsFieldsForView.values());
        Collections.sort(listAll);
    }

    public static List<ProductsFieldsForView> all() {
        return listAll;
    }

    public String getLabel() {
        return label;
    }

    public String getDisplayName() {
        return displayName;
    }
}
