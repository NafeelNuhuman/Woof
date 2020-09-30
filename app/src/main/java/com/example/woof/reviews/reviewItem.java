package com.example.woof.reviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.other.Home;

public class reviewItem extends AppCompatActivity {
    private EditText reviewDesc,reviewRating;
    private Button   Cancelbutton ,Confirmbutton;
    DBHelper dbHelper;
    private  int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize views
        setContentView(R.layout.activity_review_item);
        reviewDesc=findViewById(R.id.etRatingDesc);
        Cancelbutton=findViewById(R.id.Cancelbtn);
        Confirmbutton=findViewById(R.id.Confirmbtn);
        dbHelper =new DBHelper(getApplicationContext());

        SharedPreferences prefs = getSharedPreferences(Home.MY_PREFS_NAME, MODE_PRIVATE);
        String userEmail = prefs.getString("email", null);
        userID = dbHelper.getUserID(userEmail);
        
    }
}