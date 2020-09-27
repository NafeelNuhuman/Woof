package com.example.woof;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class manage_accessory_single_item extends AppCompatActivity {

    TextView name,desc,price;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_accessory_single_item);

        name = findViewById(R.id.etProductName);
        desc = findViewById(R.id.etProductDesc);
        price = findViewById(R.id.etProdPrice);
        image = findViewById(R.id.prodImage);



    }
}