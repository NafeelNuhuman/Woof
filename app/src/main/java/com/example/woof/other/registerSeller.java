package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.database.sellerModel;

public class registerSeller extends AppCompatActivity {

    EditText name, email, contact, address, pwd, conPwd;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_seller);

        name = findViewById(R.id.etSellerName);
        email = findViewById(R.id.etSellerEmail);
        contact = findViewById(R.id.etSellerPhone);
        address = findViewById(R.id.etAddress);
        pwd = findViewById(R.id.etPwd);
        conPwd = findViewById(R.id.etConPwd);
        btnReg = findViewById(R.id.btnReg);
        DBHelper dbHelper = new DBHelper(registerSeller.this);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellerModel sm;
                DBHelper dbHelper = new DBHelper(registerSeller.this);
                String emaila = email.getText().toString();

                if (pwd.getText().toString().equals(conPwd.getText().toString())) {
                    Boolean checkmail = dbHelper.checkmail(emaila);
                    if (checkmail) {
                        try {
                            sm = new sellerModel(-1,
                                    name.getText().toString(),
                                    email.getText().toString(),
                                    Integer.parseInt(contact.getText().toString()),
                                    address.getText().toString(),
                                    pwd.getText().toString());
                            dbHelper.addSeller(sm);
                            Toast.makeText(registerSeller.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);

                        } catch (Exception e) {
                            Toast.makeText(registerSeller.this, "Enter necessary information with appropriate values " + e, Toast.LENGTH_LONG).show();
                            recreate();
                        }

                    } else {
                        Toast.makeText(registerSeller.this, "Email already registered", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(registerSeller.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    recreate();
                }
            }
        });


    }
}