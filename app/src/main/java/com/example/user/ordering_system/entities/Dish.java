package com.example.user.ordering_system.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 餐點
 */
public class Dish implements Parcelable {
    private String mTitle;
    private double mPrice;

    public Dish(String title, double price) {
        mTitle = title;
        mPrice = price;
    }

    /**
     * 餐點名稱
     * @return
     */
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    /**
     * 餐點價格
     * @return
     */
    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeDouble(this.mPrice);
    }

    protected Dish(Parcel in) {
        this.mTitle = in.readString();
        this.mPrice = in.readDouble();
    }

    public static final Parcelable.Creator<Dish> CREATOR = new Parcelable.Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel source) {
            return new Dish(source);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };
}
