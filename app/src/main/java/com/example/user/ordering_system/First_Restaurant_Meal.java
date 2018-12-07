package com.example.user.ordering_system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.ordering_system.entities.Dish;

import java.util.ArrayList;
import java.util.List;

/**
 * 校內第一餐廳內的餐點
 */
public class First_Restaurant_Meal extends AppCompatActivity {


    ListView listView;
    String[] name = {"紅茶", "綠茶", "奶茶"};
    String[] cost = {"$20", "$30", "$40"};
    double [] price = {20, 30, 40};
    List<Dish> shopCart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first__restaurant__meal);
        setTitle("手作茶坊");
        listView = findViewById(R.id.meal_list);
        meal_list adapter=new meal_list();
        listView.setAdapter(adapter);
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
                shopCart.add(dish);

                Toast.makeText(First_Restaurant_Meal.this, dish.getTitle() + " has added to cart.", Toast.LENGTH_LONG)
                        .show();
            }
        });

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
            View v = convertView;
            if (v == null) {
                v = getLayoutInflater().inflate(R.layout.first_restaurant_meal_item, null);
                TextView tname = v.findViewById(R.id.mealname);
                TextView tcost = v.findViewById(R.id.mealcost);
                tname.setText(name[position]);
                tcost.setText(cost[position]);

            }
            return v;
        }
    }
}