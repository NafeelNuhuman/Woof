package com.example.woof.dogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.woof.R;

public class uploadPet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pet);
    }

    public void movePagetomanage(View view){
        Intent intent = new Intent(this, manageMyDog.class);
        startActivity(intent);
    }
}