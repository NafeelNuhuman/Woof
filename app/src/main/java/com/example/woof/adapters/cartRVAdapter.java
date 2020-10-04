package com.example.woof.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
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
import com.example.woof.database.cartModel;

import java.util.ArrayList;

public class cartRVAdapter extends RecyclerView.Adapter<cartRVAdapter.RVViewHolderClass> {
    ArrayList<cartModel> cartModelList;
    private Context context;
    Activity activity;
    DBHelper dbhelper;
    String id;

    public cartRVAdapter(ArrayList<cartModel> cartModelList, Context context, Activity activity) {
        this.cartModelList = cartModelList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public cartRVAdapter.RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_single_item_card,parent,false);
        return new RVViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cartRVAdapter.RVViewHolderClass holder, int position) {
        cartModel cm = cartModelList.get(position);
        String name = cm.getName();
        double total = cm.getTotal();
        double cost = cm.getCost();
        int quantity = cm.getQuantity();
        Bitmap image = cm.getImage();
        id = String.valueOf(cm.getItemID());
        holder.nameTV.setText(name);
        holder.costTV.setText(String.valueOf(cost));
        holder.totalTV.setText(String.valueOf(total));
        holder.quantityTV.setText(String.valueOf(quantity));
        holder.imageTV.setImageBitmap(image);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmRemoveDialog();
            }
        });
    }

    @Override
    public int getItemCount() {
        try {
            return cartModelList.size();
        }catch (Exception e){
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    public static class RVViewHolderClass extends RecyclerView.ViewHolder{
        private TextView costTV,totalTV,quantityTV,nameTV;
        private ImageView imageTV;
        private Button btn;

        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);
            costTV = itemView.findViewById(R.id.cartCardProdPrice);
            totalTV = itemView.findViewById(R.id.cartCardItemTotal);
            quantityTV = itemView.findViewById(R.id.cartCardQuantity);
            nameTV = itemView.findViewById(R.id.cartCardItemName);
            imageTV = itemView.findViewById(R.id.cartCardProdImage);
            btn = itemView.findViewById(R.id.btnRemoveFromCart);
        }
    }

    void confirmRemoveDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Remove Item From Cart?");
        builder.setMessage("Are you sure you want to remove this item from the cart?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbhelper = new DBHelper(context);
                dbhelper.deleteCartItem(id);
                activity.recreate();
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
