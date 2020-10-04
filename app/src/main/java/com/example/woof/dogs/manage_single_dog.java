package com.example.woof.dogs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.woof.R;

public class manage_single_dog extends AppCompatActivity {
    TextView name,breed,age;
    ImageView dogImg;
    Button dltBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_single_dog);

        this.name = findViewById(R.id.etpetName);
        this.breed = findViewById(R.id.etpetBreed);
        this.age =findViewById(R.id.etpetAge);
        this.dltBtn = findViewById(R.id.btndelete);

        dltBtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {

        }
    });
    }
}