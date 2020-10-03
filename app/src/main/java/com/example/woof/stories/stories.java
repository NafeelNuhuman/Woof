package com.example.woof.stories;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.database.petOwnerMaster;
import com.example.woof.database.sellerModel;
import com.example.woof.database.storyModel;
import com.example.woof.other.Login;
import com.example.woof.other.registerSeller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class stories extends AppCompatActivity {

    private EditText Title, Description;
    private Button ShareButton;
    DBHelper dbHelper;
    int userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialize views
        setContentView(R.layout.activity_stories);
        Title = findViewById(R.id.etStoryTitle);
        Description = findViewById(R.id.StoryDescription);
        ShareButton = findViewById(R.id.UserStoriesbtn);
        DatePickerDialog.OnDateSetListener setListener;
        dbHelper = new DBHelper(getApplicationContext());


        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        userID = dbHelper.getUserID(email);


        ShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyModel stm;
                dbHelper = new DBHelper(getApplicationContext());

                if (Title.getText().toString().equals("") || Description.getText().toString().equals("")) {
                    Toast.makeText(stories.this, "Enter Values", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        stm = new storyModel(-1,
                                Title.getText().toString(),
                                Description.getText().toString(), userID);

                        dbHelper.addStory(stm);
                        Toast.makeText(stories.this, "Shared successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), stories.class);
                        startActivity(intent);

                    } catch (Exception e) {
                        Toast.makeText(stories.this, "Share unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}
