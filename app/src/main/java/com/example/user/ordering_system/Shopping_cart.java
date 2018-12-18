package com.example.user.ordering_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.ordering_system.entities.Dish;
import com.example.user.ordering_system.shopCart.ShopCart;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Shopping_cart extends AppCompatActivity {

    ImageButton btnback;
    ArrayList<Dish> selectItems=new ArrayList<>();
    private static final String TAG = "ShoppingCartActivity";
    TextView numberOfItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        findview();

        //接收資料

        ShopCart shopCart  = (ShopCart) getIntent().getSerializableExtra("cart");
        selectItems = shopCart.getItems();
        Log.d(TAG, "# of Items in the cart: " + selectItems.size());
        numberOfItems = findViewById(R.id.numberOfItems);
        numberOfItems.setText("" + selectItems.size());

        //點擊icon,關閉購物車頁面
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findview() {
        btnback=findViewById(R.id.btnbackicon);

    }


}
