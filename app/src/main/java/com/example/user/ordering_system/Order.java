package com.example.user.ordering_system;

import android.content.ContentResolver;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Order extends AppCompatActivity {

    TextView txtDate, txtOrderTime, txtTakeTime, txtName, txtPhone, txtEmail;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("訂單明細");

        bindViewComponents();
        setDateTimeFormat();
        getPersonalInfo();
    }

    private void getPersonalInfo() {
        bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        txtName.setText(name);
        String cellphone = bundle.getString("cellphone");
        txtPhone.setText(cellphone);
        String email = bundle.getString("email");
        txtEmail.setText(email);
    }

    private void setDateTimeFormat() {
        SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat TimeFormat = new SimpleDateFormat("hh:mm");

        String date = DateFormat.format(new Date());

        Date orderDateTime = Calendar.getInstance().getTime();
        String time = TimeFormat.format(orderDateTime.getTime());

        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MINUTE,15);
        Date takeDateTime=calendar.getTime();
        String sdsdsd=TimeFormat.format(takeDateTime);

        txtDate.setText(date);
        txtOrderTime.setText(time);
        txtTakeTime.setText(sdsdsd);
    }

    private void bindViewComponents () {
            txtDate = findViewById(R.id.Date);
            txtOrderTime = findViewById(R.id.OrderTime);
            txtTakeTime = findViewById(R.id.TakeMealTime);
            txtName = findViewById(R.id.Name);
            txtPhone = findViewById(R.id.CellPhone);
            txtEmail = findViewById(R.id.Email);
        }
    }

