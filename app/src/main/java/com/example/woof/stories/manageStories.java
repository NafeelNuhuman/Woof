package com.example.woof.stories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.woof.R;
import com.example.woof.adapters.manageStoriesRVAdapter;
import com.example.woof.database.DBHelper;

import java.util.ArrayList;

public class manageStories extends AppCompatActivity {

    private RecyclerView rvs;
    private DBHelper dbHelper;
    ArrayList<Integer> StoryID;
    ArrayList<String> StoryTitle,StoryDesc;
    manageStoriesRVAdapter manageStoriesRVAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_story);
        rvs = findViewById(R.id.manageStoriesV);

        dbHelper = new DBHelper(this);

        dbHelper = new DBHelper(manageStories.this);
        StoryID =new ArrayList<>();
        StoryTitle=new ArrayList<>();
        StoryDesc=new ArrayList<>();

        displayData();
        manageStoriesRVAdapter=new manageStoriesRVAdapter(manageStories.this,this,StoryID,StoryTitle,StoryDesc);
        rvs.setAdapter(manageStoriesRVAdapter);
        rvs.setLayoutManager(new LinearLayoutManager(manageStories.this));

    }
    void displayData(){
        Cursor cursor =dbHelper.readAllStories();
        if(cursor.getCount() ==0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                StoryID.add(cursor.getInt(0));
                StoryTitle.add(cursor.getString(1));
                StoryDesc.add(cursor.getString(2));

            }

        }


    }

}
