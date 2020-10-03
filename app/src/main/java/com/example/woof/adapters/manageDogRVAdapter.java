package com.example.woof.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woof.R;
import com.example.woof.database.DogModel;

import java.util.ArrayList;

public class manageDogRVAdapter extends RecyclerView.Adapter<manageDogRVAdapter.RVViewHolderClass> {
    ArrayList<DogModel> DogModelList;

    public manageDogRVAdapter(ArrayList<DogModel> dogModelList) {
        DogModelList = dogModelList;
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
    }

    @Override
    public int getItemCount() {
        return DogModelList.size();
    }

    public static class RVViewHolderClass extends RecyclerView.ViewHolder {

        private TextView dogName, dogAge, dogBreed;
        private ImageView dogImage;

        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);
            {
                dogName = itemView.findViewById(R.id.DogName);
                dogAge = itemView.findViewById(R.id.DogAge);
                dogBreed = itemView.findViewById(R.id.DogBreed);
                dogImage = itemView.findViewById(R.id.DogImage);
            }
        }
    }
}
