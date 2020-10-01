package com.example.woof.accessories;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.database.productModel;
import com.example.woof.other.sellerProfile;

import static com.example.woof.other.sellerProfile.PREFS_NAME;

public class uploadAccessory extends AppCompatActivity {

    private EditText prodName, prodDesc, prodPrice;
    private DBHelper dbHelper;
    ImageView image1;
    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize views
        setContentView(R.layout.activity_upload_accessory);
        prodName = findViewById(R.id.etProductName);
        prodDesc = findViewById(R.id.etProductDesc);
        prodPrice = findViewById(R.id.etProdPrice);
        Button btnConfirm = findViewById(R.id.btnProdConfirm);
        image1 = findViewById(R.id.prodImage1);
        dbHelper = new DBHelper(getApplicationContext());

        //get seller email through intent
        Intent intent = getIntent();
        final String sellerEmail = intent.getStringExtra("email");

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sellerID = dbHelper.getSellerID(sellerEmail);
                productModel pm;

                if (prodName.equals("") || prodDesc.equals("") || prodPrice == null) {
                    Toast.makeText(uploadAccessory.this, "Please enter required information", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        pm = new productModel(-1,
                                prodName.getText().toString(),
                                prodDesc.getText().toString(),
                                Float.parseFloat(prodPrice.getText().toString()),
                                imageToStore, sellerID);
                        dbHelper.addProduct(pm);
                        Toast.makeText(uploadAccessory.this, "Product uploaded successfully", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(uploadAccessory.this, "Upload Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    public void chooseImage(View view){
        try {
            Intent intent = new Intent();
            intent.setType("image/*");

            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,PICK_IMAGE_REQUEST);


        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!=null){
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);

                image1.setImageBitmap(imageToStore);

            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }




}