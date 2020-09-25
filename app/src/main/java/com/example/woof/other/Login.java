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
        btnLogin = findViewById(R.id.btnReg);
        db = new DBHelper(this);

        //sign in
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String password = etPwd.getText().toString();

                boolean check1 = db.checkEmailPwd(email,password,"pet owner");
                boolean check2 = db.checkEmailPwd(email,password,"seller");
                if (check1){
                    Toast.makeText(Login.this, "Welcome", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Home.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }else if (check2){
                    Toast.makeText(Login.this, "Welcome", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),sellerProfile.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    public void movetoSignup(View view) {
        Intent intent = new Intent(this, registerOptions.class);
        startActivity(intent);
    }
}