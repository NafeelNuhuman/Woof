package com.example.woof.dogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.woof.R;
import com.example.woof.adapters.DogRVAdapter;
import com.example.woof.database.DBHelper;
import com.example.woof.database.DogMaster;
import com.example.woof.accessories.Accesories;
import com.example.woof.other.Home;
import com.example.woof.other.cart;
import com.example.woof.other.userProfile;
import com.example.woof.stories.stories2;

public class viewAllpets extends AppCompatActivity {

    RecyclerView Rv;
    DBHelper dbHelper;
    DogRVAdapter adapter;
    static  String email;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_allpets);
        Rv = findViewById(R.id.DogRecyclerView);
        dbHelper = new DBHelper(this);
        this.getData();
        Intent intent = getIntent();
        intent.getStringExtra("email");
        drawerLayout = findViewById(R.id.drawer_layout);

    }
    public void getData(){
        adapter = new DogRVAdapter(dbHelper.getDogList(), viewAllpets.this,this);
        Rv.setHasFixedSize(true);

        Rv.setLayoutManager(new GridLayoutManager(this,2));
        Rv.setAdapter(adapter);

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
        redirectActivity(this, Home.class);
    }

    public void ClickDog(View view) {
        recreate();
    }

    public void ClickAccessories(View view) {
        redirectActivity(this, Accesories.class);
    }

    public void ClickStories(View view) {
        redirectActivity(this, stories2.class);
    }

    public  void  ClickUser(View view){ redirectActivity(this, userProfile.class);}

    public  void  ClickCart(View view){
        redirectActivity(this, cart.class);
    }
}


