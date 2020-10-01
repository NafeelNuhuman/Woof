package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.woof.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class cart extends AppCompatActivity {

    private FloatingActionButton addToCartBtn;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productPrice, productDescription, productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        addToCartBtn = (FloatingActionButton) findViewById(R.id.add_to_cart_btn);
        numberButton = (ElegantNumberButton)  findViewById(R.id.product_qty_btn);
        productImage = (ImageView) findViewById(R.id.product_image_details);
        productName = (TextView) findViewById(R.id.product_name_details);
        productDescription = (TextView) findViewById(R.id.product_desc_details);
        productPrice = (TextView) findViewById(R.id.product_price_details);
    }
}