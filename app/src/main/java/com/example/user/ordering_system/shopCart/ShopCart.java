package com.example.user.ordering_system.shopCart;

import com.example.user.ordering_system.entities.Dish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * Update the shop cart lines.
     * @param selectItems
     */
    public void updateList(ArrayList<Dish> selectItems) {
        for(Dish dish : selectItems){
            if (!linesMap.containsKey(dish))
                linesMap.put(dish, new ShopCartLine(dish, 1));
        }
    }

    /**
     * ShopCart Line
     */
    public static class ShopCartLine implements Serializable{
        private Dish item;
        private Integer qty;
        // line total
        private Double total;

        public ShopCartLine(Dish item, int qty) {
            this.item = item;
            this.qty = qty;
            this.total = qty * item.getPrice();
            
        }

        public Dish getItem() {
            return item;
        }

        public void setItem(Dish item) {
            this.item = item;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

        public Double getTotal() {
            return total;
        }

        public void setTotal(Double total) {
            this.total = total;
        }
    }

    private ArrayList<ShopCartLine> lines;

    private Map<Dish, ShopCartLine> linesMap;

    /**
     * @deprecated
     */
    private ArrayList<Dish> items;


    public ShopCart(ArrayList<Dish> items) {
//        this.items = items;
//        setLines(items);
        linesMap = new HashMap<>();
        for(Dish dish: items){
            linesMap.put(dish, new ShopCartLine(dish, 1));
        }
    }

    public ArrayList<ShopCartLine> getLines() {
        return lines;
    }

    /**
     * Put the select items to the shop cart
     * @param selectItems
     */
    public void setLines(ArrayList<Dish> selectItems) {
        this.lines = new ArrayList<ShopCartLine>();
        for(Dish dish: selectItems){
            lines.add(new ShopCartLine(dish, 1));
        }
    }

    /**
     * Get the items in the cart.
     * @return Array List of {@link Dish} instances.
     */
    public ArrayList<Dish> getSelectedItems() {
       ArrayList<Dish> selectItems = new ArrayList<>();
       selectItems.addAll(linesMap.keySet());
       return selectItems;
    }

    /**
     * @deprecated
     * @param items
     */
    public void setItems(ArrayList<Dish> items) {
        this.items = items;
    }

    public Map<Dish, ShopCartLine> getLinesMap() {
        return linesMap;
    }

    public void setLinesMap(Map<Dish, ShopCartLine> linesMap) {
        this.linesMap = linesMap;
    }

    /**
     * Adjust the quantity and the total of shop cart line.
     *
     * @param dish {@code Dish} instance to update.
     * @param delta +1 means increasing 1; -1 means decreasing 1.
     * @return updated shop cart line
     */
    public ShopCartLine adjustQty(Dish dish, int delta){
        // find the line
        ShopCartLine line = linesMap.get(dish);
        int newQty = line.getQty() + delta;
        if (newQty < 0)
            newQty = 0;
        line.setQty(newQty);
        line.setTotal( newQty * dish.getPrice());
        return line;
    }

    public ShopCartLine findLine(Dish dish){
        return linesMap.get(dish);
    }
}
