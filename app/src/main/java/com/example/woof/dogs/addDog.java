package com.example.woof.dogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.woof.R;
import com.example.woof.database.DBHelper;

public class addDog extends AppCompatActivity {

    EditText name, size, breed, gender, age, vacc;
    ImageView dogImage;
    Button confbtn;
    Intent intent = getIntent();
    String email = intent.getStringExtra("email");
    DBHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);
        dbhelper = new DBHelper(this);
        dbhelper.get
        name = findViewById(R.id.etPetName);
        size = findViewById(R.id.etPetSize);
        breed = findViewById(R.id.etPetBreed);
        gender = findViewById(R.id.etPetGender);
        age = findViewById(R.id.etPetAge);
        vacc = findViewById(R.id.etPetVacc);
        dogImage = findViewById(R.id.imageDog);
        confbtn = findViewById(R.id.confbutton);


    }

    public void movePagetomanage(View view){
        Intent intent = new Intent(this, manageMyDog.class);
        startActivity(intent);
    }
}