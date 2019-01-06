package com.example.user.ordering_system.shopCart;

/**
 * 儲存 ShopCart 的地方, 供不同的 Activity 分享資料.
 * 使用 Singleton (Single Instance) Pattern  實作.
 *
 * 例如, 當由一個 <b> B </b> Activity 返回( finish()) 至上一個 <b> A </b> Activity 時, 兩個 Activities 使用相同現有的 ShopCart
 * 資料. 如此, 當再由 <b>A</b> Activity 進到 <b>B</b> Activity 時, 便可以保有先前的 ShopCart 資料.
 *
 * 技術參考來源: {@link https://stackoverflow.com/a/4878259}
 *
 * @author hychen39@gm.cyut.edu.tw
 * @since 1/6/2019
 */
public class ShopCartHolder {
    //non-static fields
    private ShopCart shopCart;

    public ShopCart getShopCart() {
        return shopCart;
    }

    public void setShopCart(ShopCart shopCart) {
        this.shopCart = shopCart;
    }

    // static field to store the instance
    private final static ShopCartHolder shopCartHolder = new ShopCartHolder();

    public static ShopCartHolder getInstance() {
        return shopCartHolder;
    }
}
