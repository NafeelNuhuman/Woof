package com.example.woof.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woof.R;
import com.example.woof.accessories.singleItemView;
import com.example.woof.database.productModel;

import java.util.ArrayList;

public class AccRVAdapter extends RecyclerView.Adapter<AccRVAdapter.RVViewHolderClass> {
    ArrayList<productModel> productModelList;
    private Context context;
    Activity activity;

    public AccRVAdapter(ArrayList<productModel> productModelList,Activity activity,Context context) {
        this.productModelList = productModelList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_single_accessory_card,parent,false);*/
        return new RVViewHolderClass(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_single_accessory_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RVViewHolderClass holder, int position) {
        productModel pm = productModelList.get(position);
        holder.prodName.setText(pm.getName());
        String price = Float.toString(pm.getPrice());
        holder.prodPrice.setText(price);
        holder.prodImage.setImageBitmap(pm.getImage());

        final int id = pm.getID();
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, singleItemView.class);
                intent.putExtra("prodID",id);
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public  static  class RVViewHolderClass extends RecyclerView.ViewHolder
    {
        private TextView prodName,prodPrice;
        private CardView card;
        private ImageView prodImage;

        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.prodCard);
            prodName = itemView.findViewById(R.id.prodNameCard);
            prodPrice = itemView.findViewById(R.id.prodPriceCard);
            prodImage = itemView.findViewById(R.id.prodImageCard);

        }
    }
}
