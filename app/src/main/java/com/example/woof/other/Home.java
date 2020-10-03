package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.woof.accessories.Accesories;
import com.example.woof.R;
import com.example.woof.dogs.viewAllpets;
import com.example.woof.stories.stories;
import com.example.woof.stories.stories2;

public class Home extends AppCompatActivity {

    DrawerLayout drawerLayout;
    static String email;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);

        Intent intent1 = getIntent();
        email = intent1.getStringExtra("email");

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("email", email);
        editor.apply();

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

    public  void  ClickUser(View view){ redirectActivity(this,userProfile.class);}

}