package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.database.sellerModel;

public class registerSeller extends AppCompatActivity {

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_seller);

        final EditText nameET = findViewById(R.id.etSellerName);
        final EditText emailET = findViewById(R.id.etSellerEmail);
        final EditText contactET = findViewById(R.id.etSellerPhone);
        final EditText addressET = findViewById(R.id.etAddress);
        final EditText pwdET = findViewById(R.id.etPwd);
        final EditText conPwdET = findViewById(R.id.etConPwd);
        Button btnReg = findViewById(R.id.btnSellerReg);
        DBHelper dbHelper = new DBHelper(registerSeller.this);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellerModel sm;
                DBHelper dbHelper = new DBHelper(registerSeller.this);
                boolean checkmail = dbHelper.checkmail(emailET.getText().toString());

                if (nameET.getText().toString().equals("")
                        || emailET.getText().toString().equals("") ||
                        addressET.getText().toString().equals("") ||
                        contactET.getText().toString().equals("") ||
                        pwdET.getText().toString().equals("") ||
                        conPwdET.getText().toString().equals("")) {
                    Toast.makeText(registerSeller.this, "Enter required information", Toast.LENGTH_SHORT).show();
                } else {
                    if (emailET.getText().toString().matches(emailPattern)) {
                        if (checkmail) {
                            if (pwdET.getText().toString().equals(conPwdET.getText().toString())) {
                                try {
                                    sm = new sellerModel(-1,
                                            nameET.getText().toString(),
                                            emailET.getText().toString(),
                                            Integer.parseInt(contactET.getText().toString()),
                                            addressET.getText().toString(),
                                            pwdET.getText().toString());
                                    dbHelper.addSeller(sm);
                                    Toast.makeText(registerSeller.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);

                                } catch (Exception e) {
                                    Toast.makeText(registerSeller.this, "Registration Unsuccessfull " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    recreate();
                                }

                            } else {
                                Toast.makeText(registerSeller.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(registerSeller.this, "Email already registered", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(registerSeller.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

    }

}