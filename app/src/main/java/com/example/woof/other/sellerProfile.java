package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.woof.R;
import com.example.woof.accessories.manageAccessories;
import com.example.woof.accessories.uploadAccessory;
import com.example.woof.database.DBHelper;

public class sellerProfile extends AppCompatActivity {

    TextView sellerName,uploadItem,manageItems;
    private String email = "";
    private int sellerID;
    DBHelper dbHelper;

    public static final String PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);
        dbHelper = new DBHelper(getApplicationContext());

        final Intent intent = getIntent();
        email  = intent.getStringExtra("email");
        sellerID = dbHelper.getSellerID(email);

        /*SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("email",email);
        editor.putInt("sellerID",sellerID);
        editor.commit();*/

        final DBHelper dbHelper = new DBHelper(getApplicationContext());
        sellerName = findViewById(R.id.tvSellerName);
        uploadItem = findViewById(R.id.tvUploadItem);
        manageItems = findViewById(R.id.tvManageItems);

        String seller = dbHelper.getSellerName(email);
        sellerName.setText(seller);


        uploadItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(sellerProfile.this, uploadAccessory.class);
                intent1.putExtra("email",email);
                startActivity(intent1);
            }
        });

        manageItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(sellerProfile.this, manageAccessories.class);
                intent2.putExtra("email",email);
                startActivity(intent2);
            }
        });

    }

}