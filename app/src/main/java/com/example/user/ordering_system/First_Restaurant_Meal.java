package com.example.user.ordering_system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class First_Restaurant_Meal extends AppCompatActivity {


    ListView listView;
    String[] name = {"紅茶", "綠茶", "奶茶"};
    String[] cost = {"$20", "$30", "$40"};

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