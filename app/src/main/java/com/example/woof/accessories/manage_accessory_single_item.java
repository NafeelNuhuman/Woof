package com.example.woof.accessories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.woof.R;

public class manage_accessory_single_item extends AppCompatActivity {

    TextView name,desc,price;
    ImageView image;
    Button btnUpdate,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_accessory_single_item);

        this.name = findViewById(R.id.etProductName);
        this.desc = findViewById(R.id.etProductDesc);
        this.price = findViewById(R.id.etProdPrice);
        this.image = findViewById(R.id.prodImage);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}