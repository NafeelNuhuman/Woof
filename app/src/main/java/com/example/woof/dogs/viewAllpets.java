package com.example.woof.dogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.woof.R;
import com.example.woof.accessories.Accesories;
import com.example.woof.other.userProfile;
import com.example.woof.stories.stories2;

public class viewAllpets extends AppCompatActivity {

    static  String email;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_allpets);
        Intent intent = getIntent();
        intent.getStringExtra("email");
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void movePagetoselected(View view) {
        Intent intent = new Intent(this, selectedDogview.class);
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

