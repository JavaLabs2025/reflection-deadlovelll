package org.example.classes;

import org.example.annotation.Generatable;

import java.util.List;

@Generatable
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