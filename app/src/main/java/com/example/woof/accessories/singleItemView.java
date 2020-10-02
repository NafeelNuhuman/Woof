package com.example.woof.accessories;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.woof.R;

public class singleItemView extends AppCompatActivity {

    private TextView name,description,price;
    private ImageView image;
    private Button btnAddtoCart;
    private Context context;
    static Double prodPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);

        name = findViewById(R.id.singleItemprodName);
        description = findViewById(R.id.singleItemProdDesc);
        price = findViewById(R.id.singleItemProdPrice);
        image = findViewById(R.id.singleItemProdImage);
        btnAddtoCart = findViewById(R.id.addToCart);



        Intent intent = getIntent();
        int prodID = intent.getIntExtra("prodID",0);
        String id = String.valueOf(prodID);
        String prodName = intent.getStringExtra("prodName");
        String prodDesc = intent.getStringExtra("prodDesc");
        prodPrice = intent.getDoubleExtra("prodPrice",0);

        byte[] prodImageInBytes = intent.getByteArrayExtra("prodImage");
        Bitmap prodImage = BitmapFactory.decodeByteArray(prodImageInBytes,0,prodImageInBytes.length);
        name.setText(prodName);
        description.setText(prodDesc);
        price.setText(String.valueOf(prodPrice));


        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showQuantityDialog();
            }
        });



    }

    private double total = 0;
    private double cost = 0;
    private int quantity = 0;
    private void showQuantityDialog() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_quantity,null);
        ImageButton plusBtn = view.findViewById(R.id.imagePlusButton);
        ImageButton minusBtn = view.findViewById(R.id.imageMinusButton);
        final TextView quantityTV = view.findViewById(R.id.quantity);
        Button addToCartBtn = view.findViewById(R.id.btnAddToCart);
        final TextView priceTV = view.findViewById(R.id.dialogQuantityPrice);

        String priceInString = String.valueOf(prodPrice) ;
        quantity = 1;
        cost = Double.parseDouble(priceInString);
        total = Double.parseDouble(priceInString);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        price.setText(priceInString);
        quantityTV.setText(quantity);

        AlertDialog dialog = builder.create();
        dialog.show();

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total = total + cost;
                quantity++;
                priceTV.setText("Rs." + total);
                quantityTV.setText(String.valueOf(quantity));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity>1){
                    total = total - cost;
                    quantity--;
                    priceTV.setText("Rs." + total);
                    quantityTV.setText(String.valueOf(quantity));
                }
            }
        });
    }
}