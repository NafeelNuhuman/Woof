package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.woof.R;
import com.example.woof.dogs.addDog;
import com.example.woof.dogs.manageMyDog;

public class userProfile extends AppCompatActivity {
    Intent intent = getIntent();
    String email = intent.getStringExtra("email");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    public void moveToAddDogs(View view){
        Intent intent = new Intent(this, addDog.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }

    public void moveToMyDogs(View view){
        Intent intent = new Intent(this, manageMyDog.class);
        startActivity(intent);
    }
}