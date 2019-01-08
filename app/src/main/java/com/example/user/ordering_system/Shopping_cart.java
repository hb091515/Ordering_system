package com.example.user.ordering_system;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.ordering_system.entities.Dish;
import com.example.user.ordering_system.shopCart.ShopCart;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Shopping_cart extends AppCompatActivity {

    ImageButton btnback;
    ArrayList<Dish> selectItems=new ArrayList<>();
    private static final String TAG = "ShoppingCartActivity";
    TextView numberOfItems,txt_total;
    ListView shopcarlist;


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


        //將加入購物車的餐點，顯示出來
        carlistadapter list=new carlistadapter(this,selectItems);
        shopcarlist=findViewById(R.id.dish_list);
        shopcarlist.setAdapter(list);
        list.notifyDataSetChanged();

        //點擊返回icon,關閉購物車頁面
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //計算總金額




    }


    private void findview() {
        btnback=findViewById(R.id.btnbackicon);
        txt_total=findViewById(R.id.total);

    }



    //使用自訂義的ArrayAdapter
    public class carlistadapter extends ArrayAdapter<Dish>{
        public carlistadapter(Context context,ArrayList<Dish> object){
            super(context,0,object);
        }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
            final Dish dish=getItem(position);
            final ViewHolder viewHolder=new ViewHolder();
            if(convertView==null){
                convertView=getLayoutInflater().inflate(R.layout.shoppingcart_item,null);

            viewHolder.btnreduce=convertView.findViewById(R.id.reduce);
            viewHolder.txtqty=convertView.findViewById(R.id.Quantity);
            viewHolder.btnincrease=convertView.findViewById(R.id.increase);
            viewHolder.txtdishname=convertView.findViewById(R.id.dishname);
            viewHolder.txtdishprice=convertView.findViewById(R.id.dishprice);


            viewHolder.txtdishname.setText(dish.getTitle());
            viewHolder.txtdishprice.setText(String.valueOf(dish.getPrice()));



            //點選按鈕減少數量
            viewHolder.btnreduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int q=Integer.parseInt(viewHolder.txtqty.getText().toString());
                    q-=1;
                    if(q<=0){
                        q=0;
                    }
                    viewHolder.txtqty.setText(q+"");
                    viewHolder.txtdishprice.setText(q*dish.getPrice()+"");

                    notifyDataSetChanged();

                }
            });

            //點選按鈕增加數量
            viewHolder.btnincrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int q=Integer.parseInt(viewHolder.txtqty.getText().toString());
                    q+=1;
                    viewHolder.txtqty.setText(q+"");
                    viewHolder.txtdishprice.setText(q*dish.getPrice()+"");

                    notifyDataSetChanged();
                }
            });


            }else {
                //viewHolder=(ViewHolder)convertView.getTag();
            }

        return convertView;
    }

}

class ViewHolder{
        Button btnreduce;
        TextView txtqty;
        Button btnincrease;
        TextView txtdishname;
        TextView txtdishprice;
}

}

