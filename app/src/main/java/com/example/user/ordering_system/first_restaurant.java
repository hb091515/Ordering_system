package com.example.user.ordering_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class first_restaurant extends AppCompatActivity {

    ImageButton backicon;
    ListView listView;
    String[] title = {"手作茶坊", "Thousand leaves", "食在", "亨廷餐館"};
    String[] aaa = {"茶飲", "飲料", "早午餐", "buffer"};
    int[] icons = {R.drawable.drink3,
            R.drawable.drink1,
            R.drawable.food2,
            R.drawable.food3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_restaurant);

        setTitle("一餐");
        findview();

        itemadapter adapter = new itemadapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent intent=new Intent(first_restaurant.this,First_Restaurant_Meal.class);
                        startActivity(intent);
                        break;
                }
            }
        });


        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void findview() {
        listView = findViewById(R.id.itemlist);
        backicon = findViewById(R.id.backicon);
    }

    class itemadapter extends BaseAdapter {
        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int position) {
            return title[position];
        }

        @Override
        public long getItemId(int position) {
            return icons[position];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                v = getLayoutInflater().inflate(R.layout.first_restaurant_item, null);
                ImageView ima = v.findViewById(R.id.item_image);
                TextView tit = v.findViewById(R.id.item_text);
                TextView type = v.findViewById(R.id.storetype);
                ima.setImageResource(icons[position]);
                tit.setText(title[position]);
                type.setText(aaa[position]);
            }
            return v;
        }
    }
}
