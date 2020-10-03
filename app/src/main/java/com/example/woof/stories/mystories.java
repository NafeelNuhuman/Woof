package com.example.woof.stories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.woof.R;
import com.example.woof.accessories.Accesories;
import com.example.woof.adapters.manageStoriesRVAdapter;
import com.example.woof.adapters.storiesRVAdapter;
import com.example.woof.database.DBHelper;
import com.example.woof.dogs.viewAllpets;
import com.example.woof.other.Home;

import java.util.ArrayList;

public class mystories extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private RecyclerView mys;
    private DBHelper dbHelper;
    Button  StoryDeletebtn;
    private String email;
    private String userID;
    ArrayList<Integer> StoryID;
    ArrayList<String> StoryTitle,StoryDesc;
    manageStoriesRVAdapter manageStoriesRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystories);
        StoryDeletebtn=findViewById(R.id.StoryDeleteBtn);

        mys=findViewById(R.id.managemyStoriesV2);

        dbHelper = new DBHelper(this);
        dbHelper = new DBHelper(mystories.this);

        StoryID =new ArrayList<>();
        StoryTitle=new ArrayList<>();
        StoryDesc=new ArrayList<>();

        Intent intent2 = getIntent();
        email = intent2.getStringExtra("email");
        userID = String.valueOf(dbHelper.getUserID(email));

        mystoriesDisplay();
        manageStoriesRVAdapter =new manageStoriesRVAdapter(mystories.this,StoryTitle,StoryDesc,StoryID);
        mys.setAdapter(manageStoriesRVAdapter);
        mys.setLayoutManager(new LinearLayoutManager(mystories.this));


    }
    void mystoriesDisplay(){
        Cursor cursor= dbHelper.readAllMyStories(userID);
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
        Intent intent = new Intent(this, mystories.class);
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
        activity.startActivity(intent);

    }

    public void ClickHome(View view) {
        redirectActivity(this, Home.class);
    }

    public void ClickDog(View view) {
        redirectActivity(this, viewAllpets.class);
    }

    public void ClickAccessories(View view) {
        redirectActivity(this, Accesories.class);
    }

    public void ClickStories(View view) {
        recreate();
    }
}