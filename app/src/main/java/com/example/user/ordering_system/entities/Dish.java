package com.example.user.ordering_system.entities;



import java.io.Serializable;

/**
 * 餐點
 */
public class Dish implements Serializable {
    private String mTitle;
    private double mPrice;

    public Dish(String title, double price) {
        mTitle = title;
        mPrice = price;
    }

    /**
     * 餐點名稱
     * @return
     */
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    /**
     * 餐點價格
     * @return
     */
    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

}
