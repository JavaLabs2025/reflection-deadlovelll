package org.example.classes;

import java.util.List;

public class Cart {
    private List<org.example.classes.Product> items;

    public Cart(List<org.example.classes.Product> items) {
        this.items = items;
    }

    public List<org.example.classes.Product> getItems() {
        return items;
    }

    public void setItems(List<org.example.classes.Product> items) {
        this.items = items;
    }
}