package com.example.user.ordering_system;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.ordering_system.entities.Dish;
import com.example.user.ordering_system.shopCart.ShopCart;
import com.example.user.ordering_system.shopCart.ShopCartHolder;

import java.util.ArrayList;

public class Shopping_cart extends AppCompatActivity {


    Button btnCheck;
    ArrayList<Dish> selectItems = new ArrayList<>();
    private static final String TAG = "**ShoppingCartActivity";
    TextView numberOfItems, txt_total;
    ListView shopCarList;


    Bundle bundle;

    private ShopCart shopCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        setTitle("購物車");

        bindViewComponents();

        bundle = new Bundle();

        // Bind the action listener.
        //點擊返回icon,關閉購物車頁面


        //按下按鈕,跑出Dialog視窗
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder infoBuilder = new AlertDialog.Builder(Shopping_cart.this);
                infoBuilder.setTitle("聯絡資料");

                View view = getLayoutInflater().inflate(R.layout.form,null);
                final EditText edName = view.findViewById(R.id.Name);
                final EditText edCellPhone = view.findViewById(R.id.CellPhone);
                final EditText edEmail = view.findViewById(R.id.Email);

                //點擊確認按鈕送出填寫人資料
                infoBuilder.setNegativeButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Name = edName.getText().toString();
                        String CellPhone = edCellPhone.getText().toString();
                        String Email = edEmail.getText().toString();
                        bundle.putString("name",Name);
                        bundle.putString("cellphone",CellPhone);
                        bundle.putString("email",Email);
                        Intent intent=new Intent(Shopping_cart.this,Order.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                //點擊取消按鈕關閉Dialog畫面
                infoBuilder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //若取消,則關閉Dialog視窗
                        dialog.dismiss();
                    }
                });

                infoBuilder.setView(view);
                AlertDialog dialog = infoBuilder.create();
                dialog.show();
            }
        });


        //ToDo: 計算總金額

    }

    @Override
    protected void onPause() {
        super.onPause();
        // 返回前，保存購物車的内容。
        StringBuilder sb = new StringBuilder();
        sb.append("**Shopping_cart**:").append("Save to the ShopCartHolder with size:").append(shopCart.getSelectedItems().size());
        Log.d("**Shopping_cart**:", sb.toString());
        ShopCartHolder.getInstance().setShopCart(shopCart);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Activity 顯示在 foreground 時， 取得最新的 shop cart 内容，並更新 shopCartList view.
        //Show the shop cart content.

        Log.d("**onStart()", "ShoppingCart Activity onStart()!!");
        //接收 shop cart 資料

        shopCart = (ShopCart) getIntent().getSerializableExtra("cart");
        selectItems = shopCart.getSelectedItems();
        Log.d(TAG, "# of Items in the cart: " + selectItems.size());



        //將加入購物車的餐點，顯示出來
        CartListAdapter cartListAdapter = new CartListAdapter(this, shopCart.getLines());
        shopCarList = findViewById(R.id.dish_list);
        shopCarList.setAdapter(cartListAdapter);
        cartListAdapter.notifyDataSetChanged();
    }

    private void bindViewComponents() {
        btnCheck=findViewById(R.id.Check);
        txt_total = findViewById(R.id.total);

    }

    /**
     * Adapter for {@link #shopCarList}
     *
     * 使用 ViewHolder Pattern, 減少 findViewById() 的執行次數。
     *
     * 技術參考： https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView
     *
     * Fixme_Done: ArrayAdapter 内的 Object 要爲 ShopCartLine. 更新資料模型後, 使用 notifyDataSetChanged() 自動更新內容.
     */
    public class CartListAdapter extends ArrayAdapter<ShopCart.ShopCartLine> {

        class ViewHolder {
            Button btnReduce;
            TextView txtQty;
            Button btnIncrease;
            TextView txtDishName;
            TextView txtLineTotal;
        }

        public CartListAdapter(Context context, ArrayList<ShopCart.ShopCartLine> object) {
            super(context, 0, object);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // get the current line
            final ShopCart.ShopCartLine selectedLine = getItem(position);
            //

            ViewHolder viewHolder;
            // Create a new or get the existing viewHolder
            if (convertView == null) {
                // this is a new view.
                //
                // inflate the view
                convertView = getLayoutInflater().inflate(R.layout.shoppingcart_item, null);
                // Create a view holder
                viewHolder = new ViewHolder();
                // Save the component in the convertView to the viewHolder
                viewHolder.btnReduce = convertView.findViewById(R.id.reduce);
                viewHolder.btnIncrease = convertView.findViewById(R.id.increase);
                viewHolder.txtQty = convertView.findViewById(R.id.Quantity);
                viewHolder.txtDishName = convertView.findViewById(R.id.dishname);
                viewHolder.txtLineTotal = convertView.findViewById(R.id.dishprice);



                // Attach the event handlers to buttons
                // Event Handler: 點選按鈕減少數量
                viewHolder.btnReduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // codes for clicking the decrease button
                        // get select shop cart line id
                        // update the shop cart model
//                        ShopCart.ShopCartLine line = shopCart.adjustQty(selectedLine, -1);
                        selectedLine.adjustQty(-1);
                        notifyDataSetChanged();
                    }
                });

                //Event: 點選按鈕增加數量
                viewHolder.btnIncrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //clicking the increase button
//                        ShopCart.ShopCartLine line = shopCart.adjustQty(selectedLine, 1);
                        selectedLine.adjustQty(1);
                        notifyDataSetChanged();
                    }
                });

                // store the view holder into the convertView's tag property
                convertView.setTag(viewHolder);

            } else {
                // Case: convertView != null. Use the existing viewHolder.
                viewHolder=(ViewHolder) convertView.getTag();

            }

            // Populate data object to the viewHolder.
            viewHolder.txtDishName.setText(selectedLine.getItem().getTitle());
            viewHolder.txtQty.setText(selectedLine.getQty().toString());
            viewHolder.txtLineTotal.setText(selectedLine.getTotal().toString());
            return convertView;
        }



    }


}

