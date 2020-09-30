package com.example.woof.accessories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.woof.R;

public class manage_accessory_single_item extends AppCompatActivity {

    TextView name,desc,price;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_accessory_single_item);

        this.name = findViewById(R.id.etProductName);
        this.desc = findViewById(R.id.etProductDesc);
        this.price = findViewById(R.id.etProdPrice);
        this.image = findViewById(R.id.prodImage);

    }
}