package com.example.user.ordering_system.shopCart;

import com.example.user.ordering_system.entities.Dish;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 購物車
 *
 * 裝載 {@link com.example.user.ordering_system.entities.Dish} 物件. 因為要透過 {@code Intent} 傳遞,
 * 所以實作 {@code Serializable} 界面.
 *
 * @author hychen39@gm.cyut.edu.tw
 * @since 2018/12/16
 */
public class ShopCart implements Serializable {
    private ArrayList<Dish> items;

    public ShopCart(ArrayList<Dish> items) {
        this.items = items;
    }

    /**
     * Get the items in the cart.
     * @return Array List of {@link Dish} instances.
     */
    public ArrayList<Dish> getItems() {
        return items;
    }

    public void setItems(ArrayList<Dish> items) {
        this.items = items;
    }
}
