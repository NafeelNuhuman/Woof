package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.dogs.addDog;
import com.example.woof.dogs.manageMyDog;
import com.example.woof.stories.mystories;

public class userProfile extends AppCompatActivity {

    private static String email;
    TextView userName,userMail;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        dbHelper = new DBHelper(this);
        userName = findViewById(R.id.userName);
        userMail = findViewById(R.id.userMail);
        String name = dbHelper.getUserName(email);
        userName.setText(name);
        userMail.setText(email);

    }

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
        redirectActivity(userProfile.this,mystories.class);
    }


}