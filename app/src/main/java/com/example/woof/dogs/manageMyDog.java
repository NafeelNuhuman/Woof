package com.example.woof.dogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.woof.R;
import com.example.woof.accessories.Accesories;
import com.example.woof.adapters.manageDogRVAdapter;
import com.example.woof.database.DBHelper;
import com.example.woof.other.Home;
import com.example.woof.other.cart;
import com.example.woof.other.userProfile;
import com.example.woof.stories.stories;


public class manageMyDog extends AppCompatActivity {
    private Activity activity;
    private Context context;
    DrawerLayout drawerLayout;
    RecyclerView RV;
    DBHelper dbHelper;
    manageDogRVAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_my_dog);
        activity = manageMyDog.this;
        context = manageMyDog.this;
        drawerLayout = findViewById(R.id.drawer_layout);
        RV = findViewById(R.id.manageDogRV);
        dbHelper = new DBHelper(this);
        this.getData();
    }

    private void getData() {
        Adapter = new manageDogRVAdapter(dbHelper.getDogList(), context, activity);
        RV.setHasFixedSize(true);

        RV.setLayoutManager(new LinearLayoutManager(this));
        RV.setAdapter(Adapter);
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
        redirectActivity(this, stories.class);
    }

    public void ClickUser(View view) {
        redirectActivity(this, userProfile.class);
    }

    public void ClickCart(View view) {
        redirectActivity(this, cart.class);
    }
}