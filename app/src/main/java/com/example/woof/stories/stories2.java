package com.example.woof.stories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.woof.R;
import com.example.woof.accessories.Accesories;
import com.example.woof.dogs.viewAllpets;
import com.example.woof.other.Home;

public class stories2 extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Button shareStory;
    static  String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories2);
        shareStory = findViewById(R.id.btnShareStory);
        drawerLayout = findViewById(R.id.drawer_layout);

        Intent intent1 = getIntent();
        email = intent1.getStringExtra("email");


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