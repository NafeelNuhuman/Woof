package com.example.woof.accessories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.database.productModel;
import com.example.woof.other.sellerProfile;

import static com.example.woof.other.sellerProfile.PREFS_NAME;

public class uploadAccessory extends AppCompatActivity {

    private EditText prodName, prodDesc, prodPrice;
    private DBHelper dbHelper;
    private String sellerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_accessory);
        prodName = findViewById(R.id.etProductName);
        prodDesc = findViewById(R.id.etProductDesc);
        prodPrice = findViewById(R.id.etProdPrice);
        Button btnConfirm = findViewById(R.id.btnProdConfirm);
        dbHelper = new DBHelper(getApplicationContext());
        Intent intent = getIntent();
        final String sellerEmail = intent.getStringExtra("email");
        /*final SharedPreferences settings = getSharedPreferences(sellerProfile.PREFS_NAME, Context.MODE_PRIVATE);
        sellerEmail = settings.getString("email",null);
*/

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = prodName.getText().toString();
                String desc = prodDesc.getText().toString();
                float price = Float.parseFloat(prodPrice.getText().toString());
                int sellerID = dbHelper.getSellerID(sellerEmail);

                productModel pm;

                if (name.equals("") || desc.equals("") || price == 0) {
                    Toast.makeText(uploadAccessory.this, "Please enter required information", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        pm = new productModel(-1, name, desc, price, sellerID);
                        dbHelper.addProduct(pm);
                        Toast.makeText(uploadAccessory.this, "Product uploaded successfully", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(uploadAccessory.this, "Upload Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}