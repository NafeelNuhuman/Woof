package com.example.woof.accessories;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woof.R;
import com.example.woof.adapters.AccRVAdapter;
import com.example.woof.database.DBHelper;
import com.example.woof.dogs.viewAllpets;
import com.example.woof.other.Home;
import com.example.woof.other.cart;
import com.example.woof.other.userProfile;
import com.example.woof.stories.stories2;

public class Accesories extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    RecyclerView rv;
    DBHelper dbHelper;
    AccRVAdapter adapter;
    static String email;
    static  int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesories);
        rv = findViewById(R.id.AccRecyclerView);
        drawerLayout = findViewById(R.id.drawer_layout);
        dbHelper = new DBHelper(this);
        this.getData();
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        userID = dbHelper.getUserID(email);

    }

    public void getData(){
        adapter = new AccRVAdapter(dbHelper.getProductList(),Accesories.this,Accesories.this,userID);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(adapter);
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
    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }


    public void ClickHome(View view) {
        redirectActivity(this, Home.class);
    }

    public void ClickDog(View view) {
        redirectActivity(this, viewAllpets.class);
    }

    public void ClickAccessories(View view) {
        recreate();
    }

    public void ClickStories(View view) {
        redirectActivity(this, stories2.class);
    }

    public  void  ClickUser(View view){ redirectActivity(this, userProfile.class);}

    public  void  ClickCart(View view){
        redirectActivity(this, cart.class);
    }
}