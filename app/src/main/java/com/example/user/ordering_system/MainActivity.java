package com.example.user.ordering_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Button first,second,third;
    ImageView scoll_view1,scoll_view2,scoll_view3,scoll_view4,scoll_view5,Horizon_view1,Horizon_view2,Horizon_view3,Horizon_view4,Horizon_view5,Horizon_view6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("訂餐系統");

        findview();

        Horizontalimage();

        scrollimage();


        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,First_Restaurant.class);
                startActivity(intent);
            }
        });
    }

    private void findview() {
        first=findViewById(R.id.First_Resuaurant);
        second=findViewById(R.id.Second_Resaurant);
        third=findViewById(R.id.Third_Resaurant);
        scoll_view1=findViewById(R.id.scollview1);
        scoll_view2=findViewById(R.id.scollview2);
        scoll_view3=findViewById(R.id.scollview3);
        scoll_view4=findViewById(R.id.scollview4);
        scoll_view5=findViewById(R.id.scollview5);
        Horizon_view1=findViewById(R.id.scrollimage1);
        Horizon_view2=findViewById(R.id.scrollimage2);
        Horizon_view3=findViewById(R.id.scrollimage3);
        Horizon_view4=findViewById(R.id.scrollimage4);
        Horizon_view5=findViewById(R.id.scrollimage5);
    }
    private void scrollimage() {
        Picasso.get().load("http://static.stheadline.com/stheadline/inewsmedia/20180518/_2018051820005738593_detail.jpg").into(scoll_view1);
        Picasso.get().load("https://images2.gamme.com.tw/news2/2015/77/32/p56Zo56akKSX.jpg").into(scoll_view2);
        Picasso.get().load("http://www.xingyunba.com/editor/attached/image/20160302/2016030210560846846.jpg").into(scoll_view3);
        Picasso.get().load("https://farm2.static.flickr.com/1578/25255445935_911b45953d_z.jpg").into(scoll_view4);
        Picasso.get().load("https://sa.bbkz.net/forum/gallery/images/510542/large/1_IMG_3196.JPG").into(scoll_view5);
    }
    private void Horizontalimage() {
        Picasso.get().load("https://www.otop.tw/uploads/factory/0065/snapshot/600x400/f4ba2985b47e38ff77597b1c1d344029.jpg").into(Horizon_view1);
        Picasso.get().load("https://niniandblue.com/wp-content/uploads/2017/07/1500552710-8380dd7dfe9cb8ca7551202180381250.jpg").into(Horizon_view2);
        Picasso.get().load("https://www.otop.tw/uploads/factory/0309/snapshot/300x200/a674d959381947d7990ea8998f012723.jpg").into(Horizon_view3);
        Picasso.get().load("https://img.orange-dog.com/blogger/c1.staticflickr.com/5/4730/39446867261_23e4d018eb_c.jpg").into(Horizon_view4);
        Picasso.get().load("https://www.beauty321.com/albums_photo/6344-20180919173613149.jpg").into(Horizon_view5);
    }
}
