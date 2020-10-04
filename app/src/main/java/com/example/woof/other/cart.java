package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Adapter;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.woof.R;
import com.example.woof.accessories.Accesories;
import com.example.woof.accessories.manageAccessories;
import com.example.woof.adapters.cartRVAdapter;
import com.example.woof.adapters.manageAccRVAdapter;
import com.example.woof.database.DBHelper;
import com.example.woof.dogs.viewAllpets;
import com.example.woof.stories.stories2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class cart extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private TextView txtTotalAmount;
    private FloatingActionButton addToCartBtn;
    static String email;
    DBHelper dbHelper;
    cartRVAdapter adapter;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        drawerLayout = findViewById(R.id.drawer_layout);
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.cartRecyclerView);
        email = intent.getStringExtra("email");
        dbHelper = new DBHelper(this);
        txtTotalAmount = findViewById(R.id.cartTotalPrice);
        getData();
        double totalCost;
        totalCost = dbHelper.getCartTotal();
        txtTotalAmount.setText(String.valueOf(totalCost));

    }

    public void getData(){
        adapter = new cartRVAdapter(dbHelper.getCart(),cart.this,cart.this);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
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

    public  void  ClickCart(){
        recreate();
    }

}