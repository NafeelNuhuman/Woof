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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woof.R;
import com.example.woof.accessories.singleItemView;
import com.example.woof.database.productModel;

import java.io.ByteArrayOutputStream;
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
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_single_accessory_card,parent,false);
        return new RVViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RVViewHolderClass holder, int position) {
        final productModel pm = productModelList.get(position);
        final String name = pm.getName();
        final String desc = pm.getDesc();
        final int id = pm.getID();
        final Bitmap image = pm.getImage();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100,stream);
        final byte[] imageInBytes = stream.toByteArray();

        holder.prodName.setText(pm.getName());
        final String price = Double.toString(pm.getPrice());
        holder.prodPrice.setText(price);
        holder.prodImage.setImageBitmap(pm.getImage());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                Intent intent = new Intent(context, singleItemView.class);
                intent.putExtra("prodID",id);
                activity.startActivityForResult(intent,1);
                }catch (Exception e){
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        int size = productModelList.size();
        return size;
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
