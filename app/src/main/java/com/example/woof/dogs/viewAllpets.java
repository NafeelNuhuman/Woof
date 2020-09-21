package com.example.woof.dogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.woof.R;

public class viewAllpets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_allpets);
    }

    public void movePagetoselected(View view) {
        Intent intent = new Intent(this, selectedDogview.class);
        startActivity(intent);
    }
}