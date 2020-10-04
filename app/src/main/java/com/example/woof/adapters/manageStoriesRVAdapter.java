package com.example.woof.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.stories.mystories;

import java.util.ArrayList;

import static android.os.Build.ID;
import static java.lang.String.valueOf;

public class manageStoriesRVAdapter extends RecyclerView.Adapter<manageStoriesRVAdapter.MyViewHolder> {

    private Context context;
    private ArrayList StoryTitle,StoryDesc;
    private  ArrayList StoryID;
    private int storyID;
    private  String userName;
    private Activity activity;
    DBHelper dbHelper;

    public manageStoriesRVAdapter(Context context, ArrayList storyTitle, ArrayList storyDesc, ArrayList storyID) {
        this.context = context;
        this.StoryTitle = storyTitle;
        this.StoryDesc = storyDesc;
        this.StoryID = storyID;
    }


    @NonNull
    @Override
    public manageStoriesRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_single_manage_mystories_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        dbHelper = new DBHelper(context);
        String userID  = dbHelper.getUserID((Integer) StoryID.get(position));
        storyID = (Integer)StoryID.get(position);
        holder.StoryTitle_txt.setText(valueOf(StoryTitle.get(position)));
        holder.StoryDesc_txt.setText(valueOf(StoryDesc.get(position)));

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete();
            }
        });
    }

    @Override
    public int getItemCount() {
        return StoryID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView StoryTitle_txt,StoryDesc_txt;
        CardView card;
        Button deleteBtn;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            StoryTitle_txt=itemView.findViewById(R.id.MyStoryTiTLE);
            StoryDesc_txt=itemView.findViewById(R.id.MyStoryDesc);
            card = itemView.findViewById(R.id.MystorySingleCard);
            deleteBtn = itemView.findViewById(R.id.StoryDeleteBtn);


        }
    }
    void confirmDelete(){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Delete story?");
        builder.setMessage("Are yo sure you want to delete this story?" );
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper dbHelper =new DBHelper(context);
                dbHelper.DeleteOneRowOfStories(String.valueOf(storyID));
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
