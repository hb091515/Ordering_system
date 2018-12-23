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

/**
 * 第一餐廳內的店家
 * 
 * @author ??
 */
public class First_Restaurant extends AppCompatActivity {

    // 返回圖示??
    ImageButton backIcon;
    // 顯示餐廳內的店家
    ListView shopListView;

    String[] titles = {"手作茶坊", "Thousand leaves", "食在", "亨廷餐館"};
    String[] shopTypes = {"茶飲", "飲料", "早午餐", "buffer"};

    // 四家餐廳的 icons
    int[] icons = {R.drawable.drink3,
            R.drawable.drink1,
            R.drawable.food2,
            R.drawable.food3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_restaurant);

        setTitle("一餐");
        initUIFields();

        ShopListViewAdapter adapter = new ShopListViewAdapter();
        shopListView.setAdapter(adapter);
        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent intent=new Intent(First_Restaurant.this,First_Restaurant_Meal.class);
                        startActivity(intent);
                        break;
                }
            }
        });


        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void initUIFields() {
        shopListView = findViewById(R.id.itemlist);
        backIcon = findViewById(R.id.backicon);
    }


    class ShopListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return titles[position];
        }

        @Override
        public long getItemId(int position) {
            return icons[position];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            View v = convertView;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.first_restaurant_item, null);
                ImageView image = convertView.findViewById(R.id.item_image);
                TextView title = convertView.findViewById(R.id.item_text);
                TextView shopType = convertView.findViewById(R.id.storetype);
                image.setImageResource(icons[position]);
                title.setText(First_Restaurant.this.titles[position]);
                shopType.setText(shopTypes[position]);
            }
            return convertView;
        }
    }
}
