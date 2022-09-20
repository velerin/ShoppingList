package com.example.shoppingList.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ListModel {
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String title;

    public ListModel() {
    }

    public ListModel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
