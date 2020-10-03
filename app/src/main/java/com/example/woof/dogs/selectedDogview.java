package com.example.woof.dogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.woof.R;
import com.example.woof.database.DBHelper;

public class selectedDogview extends AppCompatActivity {
    private TextView Tvname,Tvage,Tvbreed,Tvsize;
    String name, breed, size;
    int age;
    private byte[] imageInBytes;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_dogview);

        TextView Tvname = findViewById(R.id.selecteddogname);
        TextView Tvbreed = findViewById(R.id.selecteddogbreed);
        TextView Tvage = findViewById(R.id.selecteddogage);
        TextView Tvsize = findViewById(R.id.selecteddogsize);
        ImageView imageView = findViewById(R.id.selecteddogimage);
        Intent intent = getIntent();
        int dogID = intent.getIntExtra("dogID",0);
        String id = String.valueOf(dogID);

        DBHelper dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.readDogWithID(id);

        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            cursor.moveToNext();
            name = cursor.getString(1);
             breed = cursor.getString(2);
             age = cursor.getInt(3);
             size = cursor.getString(4);
            imageInBytes = cursor.getBlob(5);
        }

        Bitmap dogImage = BitmapFactory.decodeByteArray(imageInBytes,0,imageInBytes.length);

        Tvname.setText(name);
        Tvbreed.setText(breed);
        Tvage.setText(age);
        Tvsize.setText(size);
        imageView.setImageBitmap(dogImage);




    }
}