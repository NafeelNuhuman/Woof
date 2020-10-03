package com.example.woof.dogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.woof.R;
import com.example.woof.adapters.DogRVAdapter;
import com.example.woof.database.DBHelper;
import com.example.woof.database.DogMaster;

public class viewAllpets extends AppCompatActivity {

        RecyclerView Rv;
        DBHelper dbHelper;
        DogRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_allpets);
        Rv = findViewById(R.id.DogRecyclerView);
        dbHelper = new DBHelper(this);
        this.getData();
    }
    public void getData(){
        adapter = new DogRVAdapter(dbHelper.getDogList(), viewAllpets.this,this);
        Rv.setHasFixedSize(true);

        Rv.setLayoutManager(new GridLayoutManager(this,2));
        Rv.setAdapter(adapter);

    }

}