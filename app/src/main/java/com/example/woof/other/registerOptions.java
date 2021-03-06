package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.woof.R;

public class registerOptions extends AppCompatActivity {

    Button btnPetOwner, btnSeller;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_options);

        btnPetOwner = findViewById(R.id.btnPetOwner);
        btnSeller = findViewById(R.id.btnSeller);
        tv = findViewById(R.id.tvAlreadyRegistered);

        btnPetOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerOptions.this, register.class);
                startActivity(intent);
            }
        });

        btnSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerOptions.this, registerSeller.class);
                startActivity(intent);
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerOptions.this,Login.class);
                startActivity(intent);
            }
        });

    }

}