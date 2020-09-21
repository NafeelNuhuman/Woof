package com.example.woof.dogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.woof.R;

public class addDog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);
    }

    public void movePage(View view){
        Intent intent = new Intent(this, uploadPet.class);
        startActivity(intent);
    }
}