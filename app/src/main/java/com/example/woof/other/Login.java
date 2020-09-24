package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.woof.R;
import com.example.woof.database.DBHelper;

import java.util.List;

public class Login extends AppCompatActivity {

    EditText etEmail, etPwd;
    Button btnLogin;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPwd = findViewById(R.id.etPwd);
        btnLogin = findViewById(R.id.btnLogin);
        db = new DBHelper(this);

        //sign in
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List emails = db.readEmailorPwd("email");
                List passwords = db.readEmailorPwd("password");

                String email = etEmail.getText().toString();
                String password = etPwd.getText().toString();

                if (emails.indexOf(email) >= 0) {
                    if (passwords.get(emails.indexOf(email)).equals(password)) {
                        Toast.makeText(Login.this, "Welcome", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Home.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Email not registered", Toast.LENGTH_SHORT).show();
                    recreate();
                }

            }
        });
    }


    public void movetoSignup(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}