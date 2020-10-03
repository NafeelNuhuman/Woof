package com.example.woof.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woof.R;
import com.example.woof.database.DogModel;
import com.example.woof.dogs.selectedDogview;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DogRVAdapter extends RecyclerView.Adapter<DogRVAdapter.RVViewHolderClass> {
    ArrayList<DogModel> DogModelList;
    private Context context;
    Activity activity;

    public DogRVAdapter(ArrayList<DogModel> dogModelList, Context context, Activity activity) {
        DogModelList = dogModelList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RVViewHolderClass(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.activity_single_dog_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RVViewHolderClass Holder, int Position) {
        DogModel dm = DogModelList.get(Position);
        final int id = dm.getID();
        final String name = dm.getName();
        final String breed = dm.getBreed();
        final Bitmap img = dm.getImg();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG, 100, stream);
        final byte[] imageInBytes = stream.toByteArray();

        Holder.dogName.setText(dm.getName());
        Holder.dogBreed.setText(dm.getBreed());
        Holder.dogImage.setImageBitmap(dm.getImg());

        Holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, selectedDogview.class);
                intent.putExtra("dogID", id);
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount(){ return DogModelList.size(); }

    public static class RVViewHolderClass extends RecyclerView.ViewHolder {
        private TextView dogName, dogBreed;
        private ImageView dogImage;
        private CardView card;

        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);
            {
                card = itemView.findViewById(R.id.dogCard);
                dogName = itemView.findViewById(R.id.dogNameCard);
                dogBreed = itemView.findViewById(R.id.dogbreedCard);
                dogImage = itemView.findViewById(R.id.DogImageCard);
            }
        }
    }
}
