package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.woof.R;
import com.example.woof.dogs.addDog;
import com.example.woof.dogs.manageMyDog;


public class userProfile extends AppCompatActivity {

    private String email;


import com.example.woof.dogs.uploadPet;
import com.example.woof.stories.manageStories;

public class userProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");



    private static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email",email);
        activity.startActivity(intent);

    }

    public void moveToAddDogs(View view){
        Intent intent = new Intent(this, addDog.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    public void moveToMyDogs(View view){
        Intent intent = new Intent(this, manageMyDog.class);
        startActivity(intent);
    }

    public void moveToManageStories(View view){
        redirectActivity(this, manageStories.class);
    }
}