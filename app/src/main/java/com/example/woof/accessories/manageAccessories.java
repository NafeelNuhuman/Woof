package com.example.woof.accessories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.woof.R;
import com.example.woof.adapters.manageAccRVAdapter;
import com.example.woof.database.DBHelper;

import java.util.ArrayList;

public class manageAccessories extends AppCompatActivity {

    private RecyclerView rv;
    private DBHelper dbHelper;
    private manageAccRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_accessories);
        rv = findViewById(R.id.manageAccRV);
        dbHelper = new DBHelper(this);
        this.getData();
    }

    public void getData(){
        adapter = new manageAccRVAdapter(dbHelper.getProductList());
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}