package com.example.woof.reviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.woof.R;

public class reviewItem extends AppCompatActivity {
    private EditText reviewDesc,reviewRating;
    private Button   Cancelbutton ,Confirmbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize views
        setContentView(R.layout.activity_review_item);
        reviewDesc=findViewById(R.id.etRatingDesc);
        Cancelbutton=findViewById(R.id.Cancelbtn);
        Confirmbutton=findViewById(R.id.Confirmbtn);
    }
}