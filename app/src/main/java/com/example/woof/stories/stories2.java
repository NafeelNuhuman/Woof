package com.example.woof.stories;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woof.R;
import com.example.woof.accessories.Accesories;
import com.example.woof.adapters.storiesRVAdapter;
import com.example.woof.database.DBHelper;
import com.example.woof.dogs.viewAllpets;
import com.example.woof.other.userProfile;

import java.util.ArrayList;

public class stories2 extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Button shareStory;
    static  String email;
    private RecyclerView rvs;
    private DBHelper dbHelper;
    ArrayList<Integer> StoryID;
    ArrayList<String> StoryTitle,StoryDesc;
    storiesRVAdapter storiesRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories2);
        shareStory = findViewById(R.id.btnShareStory);
        drawerLayout = findViewById(R.id.drawer_layout);



        rvs = findViewById(R.id.manageStoriesV2);

        dbHelper = new DBHelper(this);

        dbHelper = new DBHelper(stories2.this);
        StoryID =new ArrayList<>();
        StoryTitle=new ArrayList<>();
        StoryDesc=new ArrayList<>();

        Intent intent1 = getIntent();
        email = intent1.getStringExtra("email");
        String userName = dbHelper.getUserName(email);


        displayData();
        storiesRVAdapter =new storiesRVAdapter(stories2.this,this,StoryID,StoryTitle,StoryDesc);
        rvs.setAdapter(storiesRVAdapter);
        rvs.setLayoutManager(new LinearLayoutManager(stories2.this));



    }



    void displayData() {
        Cursor cursor = dbHelper.readAllStories();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                StoryID.add(cursor.getInt(0));
                StoryTitle.add(cursor.getString(1));
                StoryDesc.add(cursor.getString(2));

            }
        }
    }

    public void movePage(View view){
        Intent intent = new Intent(this, stories.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }

    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email",email);
        activity.startActivity(intent);

    }

    public void ClickHome(View view) {
        recreate();
    }

    public void ClickDog(View view) {
        redirectActivity(this, viewAllpets.class);
    }

    public void ClickAccessories(View view) {
        redirectActivity(this, Accesories.class);
    }

    public void ClickStories(View view) {
        redirectActivity(this, stories2.class);
    }

    public  void  ClickUser(View view){ redirectActivity(this, userProfile.class);}


}