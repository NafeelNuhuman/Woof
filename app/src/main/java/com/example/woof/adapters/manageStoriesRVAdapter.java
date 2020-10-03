package com.example.woof.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woof.R;
import com.example.woof.database.DBHelper;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class manageStoriesRVAdapter extends RecyclerView.Adapter<manageStoriesRVAdapter.MyViewHolder> {

    private Context context;
    private ArrayList StoryTitle,StoryDesc;
    private  ArrayList StoryID;
    private  String userName;
    private Activity activity;
    DBHelper dbHelper;

    public manageStoriesRVAdapter(Context context, ArrayList storyTitle, ArrayList storyDesc, ArrayList storyID) {
        this.context = context;
        StoryTitle = storyTitle;
        StoryDesc = storyDesc;
        StoryID = storyID;
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

        holder.StoryTitle_txt.setText(valueOf(StoryTitle.get(position)));
        holder.StoryDesc_txt.setText(valueOf(StoryDesc.get(position)));
    }

    @Override
    public int getItemCount() {
        return StoryID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView StoryTitle_txt,StoryDesc_txt;
        CardView card;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            StoryTitle_txt=itemView.findViewById(R.id.MyStoryTiTLE);
            StoryDesc_txt=itemView.findViewById(R.id.MyStoryDesc);
            card = itemView.findViewById(R.id.MystorySingleCard);


        }
    }
}
