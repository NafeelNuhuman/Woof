package com.example.woof.dogs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.database.DogModel;

public class addDog extends AppCompatActivity {

    TextView name, breed, size, gender, age, vacc;
    Button cnfrmbtn;
    ImageView DogAdd;
    DBHelper dbHelper;
    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri FileImagePath;
    private Bitmap ImageToStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);

        //get useremail through mail
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        this.name = findViewById(R.id.etpetName);
        this.breed = findViewById(R.id.etpetBreed);
        this.size = findViewById(R.id.etpetSize);
        this.gender = findViewById(R.id.etpetGender);
        this.age = findViewById(R.id.etpetAge);
        this.vacc = findViewById(R.id.etpetVacc);
        this.cnfrmbtn = findViewById(R.id.confrmBtn);
        this.DogAdd = findViewById(R.id.AddDog);

        dbHelper = new DBHelper(this);
        final int userID = dbHelper.getUserID(email);


        cnfrmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            DogModel dm;

            if(name.equals("") || breed.equals("") || size.equals("") || gender.equals("" +
                    "") || age == null || vacc.equals("")){
                Toast.makeText(addDog.this, "Please enter all information",
                        Toast.LENGTH_SHORT).show();}
                else {
                    try {
                        dm = new DogModel(-1,name.getText().toString(),Integer.parseInt(age.getText().toString()),size.getText().toString(),
                                gender.getText().toString()
                                ,breed.getText().toString(),vacc.getText().toString()
                        ,ImageToStore,userID);
                        dbHelper.addDog(dm);
                        Toast.makeText(addDog.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(addDog.this, manageMyDog.class);
                        startActivity(intent);

                    } catch (Exception e){
                        Toast.makeText(addDog.this, "Upload Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
            }
            }
        });
    }//add Image
    public void ChooseDogImage(View view) {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);

            intent.setAction(Intent.ACTION_GET_CONTENT);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            FileImagePath = data.getData();
            ImageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),FileImagePath);
            DogAdd.setImageBitmap(ImageToStore);
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


}