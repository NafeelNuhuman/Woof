package com.example.woof.accessories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.woof.R;

public class singleItemView extends AppCompatActivity {

    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);

        name= findViewById(R.id.singleItemprodName);
        Intent intent = getIntent();
        String id = String.valueOf(intent.getIntExtra("prodID",0));
        name.setText(id);
    }
}