package com.example.user.ordering_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.ordering_system.entities.Dish;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Shopping_cart extends AppCompatActivity {

    ImageButton btnback;
    ArrayList<Dish> shoppingcart=new ArrayList<Dish>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        findview();

        //接收資料
        shoppingcart=getIntent().getParcelableArrayListExtra("dish");






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
