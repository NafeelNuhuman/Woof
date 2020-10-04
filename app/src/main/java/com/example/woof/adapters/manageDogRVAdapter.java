package com.example.woof.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.database.DogModel;

import java.util.ArrayList;

public class manageDogRVAdapter extends RecyclerView.Adapter<manageDogRVAdapter.RVViewHolderClass> {
    ArrayList<DogModel> DogModelList;
    DBHelper dbHelper;
    Context context;
    Activity activity;
    private static String id;

    public manageDogRVAdapter(ArrayList<DogModel> dogModelList, Context context, Activity activity) {
        this.DogModelList = dogModelList;
        this.context = context;
        this.activity = activity;
    }


    @NonNull
    @Override
    public RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RVViewHolderClass(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_manage_single_dog, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolderClass Holder, int Position) {
        DogModel dm = DogModelList.get(Position);
        Holder.dogName.setText(dm.getName());
        String age = String.valueOf(dm.getAge());
        Holder.dogAge.setText(age);
        Holder.dogBreed.setText(dm.getBreed());
        Holder.dogImage.setImageBitmap(dm.getImg());
        id  = String.valueOf(dm.getID());
        Holder.DltBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete();
            }
        });
    }

    @Override
    public int getItemCount() {
        try {
            return DogModelList.size();
        }catch (Exception e){
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    public static class RVViewHolderClass extends RecyclerView.ViewHolder {

        private TextView dogName, dogAge, dogBreed;
        private ImageView dogImage;
        Button DltBtn;

        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);
            {
                dogName = itemView.findViewById(R.id.DogName);
                dogAge = itemView.findViewById(R.id.DogAge);
                dogBreed = itemView.findViewById(R.id.DogBreed);
                dogImage = itemView.findViewById(R.id.DogImage);
                DltBtn = itemView.findViewById(R.id.btndelete);
            }
        }
    }

    void confirmDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Details of your pet?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper = new DBHelper(context);
                dbHelper.deleteDog(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
