package com.example.woof.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woof.R;
import com.example.woof.accessories.manageAccessories;
import com.example.woof.database.DBHelper;
import com.example.woof.database.productModel;

import java.util.ArrayList;

public class manageAccRVAdapter extends RecyclerView.Adapter<manageAccRVAdapter.RVViewHolderClass> {
    ArrayList<productModel> productModelList;
    DBHelper dbHelper;
    Context context;
    Activity activity;
    private static String id;

    public manageAccRVAdapter(ArrayList<productModel> productModelList,Context context,Activity activity) {
        this.productModelList = productModelList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RVViewHolderClass(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_manage_accessory_single_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolderClass holder, int position) {
        productModel pm = productModelList.get(position);
        holder.prodName.setText(pm.getName());
        holder.proDesc.setText(pm.getDesc());
        String price = Double.toString(pm.getPrice());
        holder.prodPrice.setText(price);
        holder.prodImage.setImageBitmap(pm.getImage());
        id  = String.valueOf(pm.getID());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDeleteDialog();
            }
        });
    }


    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public  static  class RVViewHolderClass extends RecyclerView.ViewHolder
    {
        private TextView prodName,proDesc,prodPrice;
        private ImageView prodImage;
        private Button update,delete;


        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);
            prodName = itemView.findViewById(R.id.itemName);
            proDesc = itemView.findViewById(R.id.prodDesc);
            prodPrice = itemView.findViewById(R.id.prodPrice);
            prodImage = itemView.findViewById(R.id.prodImage);
            update = itemView.findViewById(R.id.btnUpdate);
            delete = itemView.findViewById(R.id.btnDelete);

        }
    }

    void confirmDeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Product?");
        builder.setMessage("Are you sure you want to delete this product?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper = new DBHelper(context);
                dbHelper.deleteAccessory(id);
                activity.finish();
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
