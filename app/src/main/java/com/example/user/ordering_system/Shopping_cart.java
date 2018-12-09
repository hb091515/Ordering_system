package com.example.user.ordering_system;

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

public class Shopping_cart extends AppCompatActivity {

    ImageButton btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        findview();




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

    class cartadapter extends BaseAdapter{
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=convertView;
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.shoppingcart_item,null);
                Button btnreduce=view.findViewById(R.id.reduce);
                TextView QTY=view.findViewById(R.id.Quantity);
                Button btnincrease=view.findViewById(R.id.increase);
                TextView dishname=view.findViewById(R.id.dishname);
                TextView dishcost=view.findViewById(R.id.dishcost);

            }
            return view;
        }
    }
}
