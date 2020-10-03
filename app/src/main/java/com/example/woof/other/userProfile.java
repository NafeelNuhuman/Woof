package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.dogs.addDog;
import com.example.woof.dogs.manageMyDog;
import com.example.woof.accessories.Accesories;
import com.example.woof.database.DBHelper;
import com.example.woof.dogs.addDog;
import com.example.woof.dogs.manageMyDog;
import com.example.woof.dogs.viewAllpets;

import com.example.woof.stories.manageStories;
import com.example.woof.stories.stories2;

public class userProfile extends AppCompatActivity {

    private static String email;
    TextView userName,userMail;
    DBHelper dbHelper;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        dbHelper = new DBHelper(this);
        userName = findViewById(R.id.userName);
        userMail = findViewById(R.id.userMail);
        String name = dbHelper.getUserName(email);
        drawerLayout = findViewById(R.id.drawer_layout);

        userName.setText(name);
        userMail.setText(email);

    }

    private static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email",email);
        activity.startActivity(intent);

    }

    public void moveToAddDogs(View view){
        Intent intent = new Intent(this, addDog.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    public void moveToMyDogs(View view){
        redirectActivity(userProfile.this,manageMyDog.class);
    }

    public void moveToManageStories(View view){
        redirectActivity(this, manageStories.class);
    }


    //navigation drawer on click methods
    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
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