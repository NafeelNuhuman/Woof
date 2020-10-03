package com.example.woof.accessories;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

public class singleItemView extends AppCompatActivity {

    private TextView priceTV;
    private Context context;
    static double prodPrice;
    private String name,desc,price;
    private byte[] imageInBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);

        TextView nameTV = findViewById(R.id.singleItemprodName);
        TextView descriptionTV = findViewById(R.id.singleItemProdDesc);
        priceTV = findViewById(R.id.singleItemProdPrice);
        ImageView imageView = findViewById(R.id.singleItemProdImage);
        Intent intent = getIntent();
        int prodID = intent.getIntExtra("prodID",0);
        String id = String.valueOf(prodID);

        DBHelper dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.readProductWithID(id);

        if (cursor.getCount() == 0){
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
        }else {
            cursor.moveToNext();
            name = cursor.getString(1);
            desc = cursor.getString(2);
            price = String.valueOf(cursor.getDouble(3));
            imageInBytes = cursor.getBlob(4);

         }

        Bitmap prodImage = BitmapFactory.decodeByteArray(imageInBytes,0,imageInBytes.length);

        nameTV.setText(name);
        descriptionTV.setText(desc);
        priceTV.setText(price);
        imageView.setImageBitmap(prodImage);

    }

   /* private double total = 0;
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
    }*/
}