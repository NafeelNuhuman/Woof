package com.example.woof.accessories;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.database.cartModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class singleItemView extends AppCompatActivity {


    private TextView priceTV;
    private Context context;
    static double prodPrice;
    private String name, desc, price;
    private int userID;
    private byte[] imageInBytes;
    Bitmap prodImage;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);

        context = this;
        FloatingActionButton btnAddToCart = findViewById(R.id.addToCart);
        TextView nameTV = findViewById(R.id.singleItemprodName);
        TextView descriptionTV = findViewById(R.id.singleItemProdDesc);
        priceTV = findViewById(R.id.singleItemProdPrice);
        ImageView imageView = findViewById(R.id.singleItemProdImage);
        Intent intent = getIntent();
        int prodID = intent.getIntExtra("prodID", 0);
        String prodIdInString = String.valueOf(prodID);
        userID = intent.getIntExtra("userID",0);
        dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.readProductWithID(prodIdInString);

        if (cursor.getCount() == 0) {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
        } else {
            cursor.moveToNext();
            name = cursor.getString(1);
            desc = cursor.getString(2);
            prodPrice = cursor.getDouble(3);
            imageInBytes = cursor.getBlob(4);
        }

        prodImage = BitmapFactory.decodeByteArray(imageInBytes, 0, imageInBytes.length);

        nameTV.setText(name);
        descriptionTV.setText(desc);
        priceTV.setText(String.valueOf(prodPrice));
        imageView.setImageBitmap(prodImage);

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
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
        final TextView totalTV = view.findViewById(R.id.dialogQuantityTotal);
        Button addToCartBtn = view.findViewById(R.id.btnAddToCart);
        final TextView priceTV = view.findViewById(R.id.dialogQuantityPrice);

        String priceInString = String.valueOf(prodPrice) ;
        quantity = 1;
        cost = Double.parseDouble(priceInString);
        total = Double.parseDouble(priceInString);
        String totalInString = String.valueOf(total);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        priceTV.setText(priceInString);
        quantityTV.setText(String.valueOf(quantity));
        totalTV.setText(totalInString);

        final AlertDialog dialog = builder.create();
        dialog.show();

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total = add(total,cost);
                quantity++;
                totalTV.setText(String.valueOf(total));
                quantityTV.setText(String.valueOf(quantity));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity>1){
                    total = substract(total,cost);
                    quantity--;
                    totalTV.setText(String.valueOf(total));
                    quantityTV.setText(String.valueOf(quantity));
                }
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartModel cm;
                try {
                    cm = new cartModel(-1,name,quantity,prodImage,cost,total,userID);
                    dbHelper.addProductToCart(cm);
                    Toast.makeText(singleItemView.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }catch (Exception e){
                    Toast.makeText(singleItemView.this, "Unable to add to cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private double substract(double total, double cost) {
        return total - cost;
    }

    public double add(double num1,double num2){
        return  num1 + num2;
    }
}