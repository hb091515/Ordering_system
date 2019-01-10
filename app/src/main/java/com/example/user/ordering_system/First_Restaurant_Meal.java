package com.example.user.ordering_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.ordering_system.entities.Dish;
import com.example.user.ordering_system.shopCart.ShopCart;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

/**
 * 校內第一餐廳內的餐點
 */
public class First_Restaurant_Meal extends AppCompatActivity {


    ListView listView;
    String[] name = {"紅茶", "綠茶", "奶茶"};
    String[] cost = {"$20", "$30", "$40"};
    double [] price = {20, 30, 40};
    ArrayList<Dish> selectItems = new ArrayList<>();
    ImageButton btnshopcart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first__restaurant__meal);
        setTitle("手作茶坊");
        listView = findViewById(R.id.meal_list);
        meal_list adapter=new meal_list();
        listView.setAdapter(adapter);
        btnshopcart=findViewById(R.id.shopcart);





        // Call Shopping_cart activity with the current cart.
        // Pass current cart to
        btnshopcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 建立 intent, 第一個參數為目前的 context, 第二個參數為要啟動的 Activity Class
                Intent intentShopCartActivity=new Intent(First_Restaurant_Meal.this, Shopping_cart.class);
                ShopCart shopCart = new ShopCart(selectItems);
                intentShopCartActivity.putExtra("cart", shopCart);
//                exeShopping_cart_activity.setClass(First_Restaurant_Meal.this,Shopping_cart.class);


//                bundle.putStringArrayList("dishes", selectItems);
//                exeShopping_cart_activity.put
                startActivity(intentShopCartActivity);
            }
        });

        // 點選清單上的項目加入購物車
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Add the meal to the shopping cart
                String tag = "ListViewClick: ";
                StringBuilder sb = new StringBuilder();
                sb.append("Position: " + position)
                        .append(" Row ID: " + id);
                Log.e(tag + "Position", sb.toString() );
                Dish dish = new Dish(name[position], price[position]);
                selectItems.add(dish);




                Toast.makeText(First_Restaurant_Meal.this, dish.getTitle() + " has added to cart.", Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    private static class ViewHolder{
        TextView dname;
        TextView dprice;
    }

    class meal_list extends BaseAdapter {
        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int position) {
            return name[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.first_restaurant_meal_item, null);

                viewHolder=new ViewHolder();
                viewHolder.dname=convertView.findViewById(R.id.mealname);
                viewHolder.dprice=convertView.findViewById(R.id.mealcost);

                viewHolder.dname.setText(name[position]);
                viewHolder.dprice.setText(cost[position]);
                convertView.setTag(viewHolder);


            }else{
                viewHolder=(ViewHolder)convertView.getTag();
            }

            return convertView;
        }
    }
}