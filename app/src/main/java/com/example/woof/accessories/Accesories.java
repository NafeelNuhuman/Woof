package com.example.woof.accessories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.woof.R;
import com.example.woof.adapters.AccRVAdapter;
import com.example.woof.adapters.manageAccRVAdapter;
import com.example.woof.database.DBHelper;

public class Accesories extends AppCompatActivity {

    RecyclerView rv;
    DBHelper dbHelper;
    AccRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesories);
        rv = findViewById(R.id.AccRecyclerView);
        dbHelper = new DBHelper(this);
        this.getData();

    }

    public void getData(){
        adapter = new AccRVAdapter(dbHelper.getProductList(),Accesories.this,this);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(adapter);
    }
}