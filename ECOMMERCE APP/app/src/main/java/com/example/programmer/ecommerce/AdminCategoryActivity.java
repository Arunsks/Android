package com.example.programmer.ecommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminCategoryActivity extends AppCompatActivity {

  private ImageView Tshirts,SportstShirts,FemaleDresses,Sweather,
          Glasses,HandBags,Hats,Shoes,
          Watches,Laptop,Mobile,Headphones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        Tshirts = (ImageView)findViewById(R.id.t_shirts);
        SportstShirts = (ImageView)findViewById(R.id.sports_t_shirts);
        FemaleDresses = (ImageView)findViewById(R.id.female_dress);
        Sweather = (ImageView)findViewById(R.id.sweather);
        Glasses = (ImageView)findViewById(R.id.glasses);
        HandBags = (ImageView)findViewById(R.id.bags);
        Hats = (ImageView)findViewById(R.id.hats);
        Shoes = (ImageView)findViewById(R.id.shoes);
        Watches = (ImageView)findViewById(R.id.watches);
        Laptop = (ImageView)findViewById(R.id.laptop);
        Mobile = (ImageView)findViewById(R.id.mobiles);
        Headphones = (ImageView)findViewById(R.id.headphone);

        Tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent (AdminCategoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Category","Tshirts");
                startActivity(intent);

            }
        });

        SportstShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent (AdminCategoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Category","sportsTshirts");
                startActivity(intent);

            }
        });

        FemaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent (AdminCategoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Category","femaleDress");
                startActivity(intent);

            }
        });

        Sweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent (AdminCategoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Category","sweathers");
                startActivity(intent);

            }
        });

        Glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent (AdminCategoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Category","glasses");
                startActivity(intent);

            }
        });

        Hats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent (AdminCategoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Category","hats");
                startActivity(intent);

            }
        });

        HandBags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent (AdminCategoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Category","handbags");
                startActivity(intent);

            }
        });

        Shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent (AdminCategoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Category","shoes");
                startActivity(intent);

            }
        });

        Laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent (AdminCategoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Category","laptop");
                startActivity(intent);

            }
        });

        Mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent (AdminCategoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Category","mobile");
                startActivity(intent);

            }
        });

        Headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent (AdminCategoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Category","headphones");
                startActivity(intent);

            }
        });

        Watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent (AdminCategoryActivity.this,AdminAddProductActivity.class);
                intent.putExtra("Category","watches");
                startActivity(intent);

            }
        });



    }
}
