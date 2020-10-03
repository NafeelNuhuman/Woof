package com.example.woof.accessories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.woof.R;

public class singleAccessoryCard extends AppCompatActivity {

    TextView name,price;
    ImageView image;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_accessory_card);
        name = findViewById(R.id.prodNameCard);
        price = findViewById(R.id.prodPriceCard);
        image = findViewById(R.id.prodImageCard);


    }
}