package com.example.user.ordering_system;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.ordering_system.entities.Dish;
import com.example.user.ordering_system.shopCart.ShopCart;
import com.example.user.ordering_system.shopCart.ShopCartHolder;

import java.util.ArrayList;

public class Shopping_cart extends AppCompatActivity {

    ImageButton btnback;
    ArrayList<Dish> selectItems = new ArrayList<>();
    private static final String TAG = "**ShoppingCartActivity";
    TextView numberOfItems, txt_total;
    ListView shopcarlist;

    private ShopCart shopCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        bindViewComponents();

        //接收 shop cart 資料

        shopCart = (ShopCart) getIntent().getSerializableExtra("cart");
        selectItems = shopCart.getSelectedItems();
        Log.d(TAG, "# of Items in the cart: " + selectItems.size());
        numberOfItems = findViewById(R.id.numberOfItems);
        numberOfItems.setText("" + selectItems.size());


        //將加入購物車的餐點，顯示出來
        carlistadapter list = new carlistadapter(this, selectItems);
        shopcarlist = findViewById(R.id.dish_list);
        shopcarlist.setAdapter(list);
        list.notifyDataSetChanged();

        //點擊返回icon,關閉購物車頁面
        btnback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 返回前，保存購物車的内容。
                StringBuilder sb = new StringBuilder();
                sb.append("**Shopping_cart**:").append("Save to the ShopCartHolder with size:").append(shopCart.getSelectedItems().size());
                Log.d("**Shopping_cart**:", sb.toString());
                ShopCartHolder.getInstance().setShopCart(shopCart);

                // Back to the previous activity
                finish();
            }
        });


        //計算總金額


    }


    private void bindViewComponents() {
        btnback = findViewById(R.id.btnbackicon);
        txt_total = findViewById(R.id.total);

    }


    //使用自定義的ArrayAdapter
    public class carlistadapter extends ArrayAdapter<Dish> {

        public carlistadapter(Context context, ArrayList<Dish> object) {
            super(context, 0, object);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // get the current dish
            final Dish selectedDish = getItem(position);
            //
            final ViewHolder viewHolder = new ViewHolder();
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.shoppingcart_item, null);

                // bind the view components.
                viewHolder.btnReduce = convertView.findViewById(R.id.reduce);
                viewHolder.btnIncrease = convertView.findViewById(R.id.increase);

                viewHolder.txtQty = convertView.findViewById(R.id.Quantity);
                viewHolder.txtDishName = convertView.findViewById(R.id.dishname);
                viewHolder.txtLineTotal = convertView.findViewById(R.id.dishprice);
//            viewHolder.txtLineTotal.setText(String.valueOf(selectedDish.getPrice()));

                // TODO: show the current line value.
                ShopCart.ShopCartLine line = shopCart.findLine(selectedDish);
                viewHolder.txtDishName.setText(selectedDish.getTitle());
                viewHolder.txtQty.setText(line.getQty().toString());
                viewHolder.txtLineTotal.setText(line.getTotal().toString());


                //點選按鈕減少數量
                viewHolder.btnReduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO: codes for clicking the decrease button
                        // get select shop cart line id
                        // update the shop cart model
                        ShopCart.ShopCartLine line = shopCart.adjustQty(selectedDish, -1);

//                    int q=Integer.parseInt(viewHolder.txtQty.getText().toString());
//                    q-=1;
//                    if(q<=0){
//                        q=0;
//                    }
                        // update the quantity and line total fields.
                        viewHolder.txtQty.setText(line.getQty().toString());
                        viewHolder.txtLineTotal.setText(line.getTotal().toString());

                        notifyDataSetChanged();

                    }
                });

                //點選按鈕增加數量
                viewHolder.btnIncrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO: codes for clicking the increase button
                        ShopCart.ShopCartLine line = shopCart.adjustQty(selectedDish, 1);
                        viewHolder.txtQty.setText(line.getQty().toString());
                        viewHolder.txtLineTotal.setText(line.getTotal().toString());

//                    int q=Integer.parseInt(viewHolder.txtQty.getText().toString());
//                    q+=1;
//                    viewHolder.txtQty.setText(q+"");
//                    viewHolder.txtLineTotal.setText(q*selectedDish.getPrice()+"");

                        notifyDataSetChanged();
                    }
                });

            } else {
                // Case: convertView != null
                //viewHolder=(ViewHolder)convertView.getTag();
            }

            return convertView;
        }

    }

    class ViewHolder {
        Button btnReduce;
        TextView txtQty;
        Button btnIncrease;
        TextView txtDishName;
        TextView txtLineTotal;
    }

}

