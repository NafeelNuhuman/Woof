package com.example.woof.stories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.woof.R;
import com.example.woof.database.DBHelper;

public class stories extends AppCompatActivity {

    private EditText Title,Description;
    private Button ShareButton;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize views
        setContentView(R.layout.activity_stories);
        Title = findViewById(R.id.etStoryTitle);
        Description=findViewById(R.id.StoryDescription);
        ShareButton=findViewById(R.id.ShareBtn);
        dbHelper =new DBHelper(getApplicationContext());


        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        int ID = dbHelper.getUserID(email);




    }
}