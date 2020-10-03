package com.example.woof.other;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.woof.R;
import com.example.woof.accessories.Accesories;
import com.example.woof.dogs.viewAllpets;
import com.example.woof.stories.stories2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class cart extends AppCompatActivity {


    private RecyclerView recyclerView;
    private TextView txtTotalAmount;
    private FloatingActionButton addToCartBtn;
    static String email;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");


    }


}