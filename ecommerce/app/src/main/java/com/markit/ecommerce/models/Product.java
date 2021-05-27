package com.markit.ecommerce.models;

import android.os.Parcel;
import android.os.Parcelable;


import androidx.annotation.Nullable;

import java.math.BigDecimal;

public class Product implements Parcelable {

    private String title;
    private int image;
    private String type;
    private BigDecimal price;
    private int serial_number;
    private int photo1;
    private int photo2;
    private int photo3;
    private int photo4;
    private int photo5;
    private int photo6;
    private int photo7;
    private int photo8;
    private int photo9;
    private int photo10;

    public Product(String title, int image, String type, BigDecimal price, int serial_number) {
        this.title = title;
        this.image = image;
        this.type = type;
        this.price = price;
        this.serial_number = serial_number;
    }
    public Product(){

    }

    public Product(String title, int image, String type, BigDecimal price, int serial_number,
                   @Nullable int photo1, @Nullable int photo2,@Nullable  int photo3,@Nullable  int photo4,@Nullable  int photo5,@Nullable  int photo6,
                   @Nullable int photo7,@Nullable  int photo8,@Nullable  int photo9, @Nullable int photo10) {
        this.title = title;
        this.image = image;
        this.type = type;
        this.price = price;
        this.serial_number = serial_number;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.photo4 = photo4;
        this.photo5 = photo5;
        this.photo6 = photo6;
        this.photo7 = photo7;
        this.photo8 = photo8;
        this.photo9 = photo9;
        this.photo10 = photo10;
    }

    public Product(Product product) {
        this.title = product.title;
        this.image = product.image;
        this.type = product.type;
        this.price = product.price;
        this.serial_number = product.serial_number;
        this.photo1 =  product.photo1;
        this.photo2 =  product.photo2;
        this.photo3 =  product.photo3;
        this.photo4 =  product.photo4;
        this.photo5 =  product.photo5;
        this.photo6 =  product.photo6;
        this.photo7 =  product.photo7;
        this.photo8 =  product.photo8;
        this.photo9 =  product.photo9;
        this.photo10 =  product.photo10;
    }


    protected Product(Parcel in) {
        title = in.readString();
        image = in.readInt();
        type = in.readString();
        serial_number = in.readInt();
        photo1=in.readInt();
        photo2=in.readInt();
        photo3=in.readInt();
        photo4=in.readInt();
        photo5=in.readInt();
        photo6=in.readInt();
        photo7=in.readInt();
        photo8=in.readInt();
        photo9=in.readInt();
        photo10=in.readInt();


    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPhoto1() {
        return photo1;
    }

    public void setPhoto1(int photo1) {
        this.photo1 = photo1;
    }

    public int getPhoto2() {
        return photo2;
    }

    public void setPhoto2(int photo2) {
        this.photo2 = photo2;
    }

    public int getPhoto3() {
        return photo3;
    }

    public void setPhoto3(int photo3) {
        this.photo3 = photo3;
    }

    public int getPhoto4() {
        return photo4;
    }

    public void setPhoto4(int photo4) {
        this.photo4 = photo4;
    }

    public int getPhoto5() {
        return photo5;
    }

    public void setPhoto5(int photo5) {
        this.photo5 = photo5;
    }

    public int getPhoto6() {
        return photo6;
    }

    public void setPhoto6(int photo6) {
        this.photo6 = photo6;
    }

    public int getPhoto7() {
        return photo7;
    }

    public void setPhoto7(int photo7) {
        this.photo7 = photo7;
    }

    public int getPhoto8() {
        return photo8;
    }

    public void setPhoto8(int photo8) {
        this.photo8 = photo8;
    }

    public int getPhoto9() {
        return photo9;
    }

    public void setPhoto9(int photo9) {
        this.photo9 = photo9;
    }

    public int getPhoto10() {
        return photo10;
    }

    public void setPhoto10(int photo10) {
        this.photo10 = photo10;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(image);
        parcel.writeString(type);
        parcel.writeInt(serial_number);
        parcel.writeInt(photo1);
        parcel.writeInt(photo2);
        parcel.writeInt(photo3);
        parcel.writeInt(photo4);
        parcel.writeInt(photo5);
        parcel.writeInt(photo6);
        parcel.writeInt(photo10);
        parcel.writeInt(photo7);
        parcel.writeInt(photo8);
        parcel.writeInt(photo9);
    }
}
