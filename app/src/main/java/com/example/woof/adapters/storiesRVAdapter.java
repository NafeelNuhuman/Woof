package com.example.woof.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.woof.R;
import com.example.woof.database.DBHelper;
import com.example.woof.stories.singleStoryView;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class storiesRVAdapter extends RecyclerView.Adapter<storiesRVAdapter.MyViewHolder>{

    private Context context;
    private ArrayList StoryTitle,StoryDesc;
    private  ArrayList StoryID;
    private  String userName;
    private Activity activity;
    DBHelper dbHelper;

    public storiesRVAdapter(Context context, Activity activity, ArrayList StoryID, ArrayList StoryTitle, ArrayList StoryDesc){
        this.context=context;
        this.activity = activity;
        this.StoryID=StoryID;
        this.StoryTitle=StoryTitle;
        this.StoryDesc=StoryDesc;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_story_single_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        dbHelper = new DBHelper(context);
        String userID  = dbHelper.getUserID((Integer) StoryID.get(position));
        String userName =dbHelper.getUserNameUsingID(userID);

    holder.StoryTitle_txt.setText(valueOf(StoryTitle.get(position)));
    holder.StoryDesc_txt.setText(valueOf(StoryDesc.get(position)));
    holder.userName.setText(userName);



        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, singleStoryView.class);
                intent.putExtra("prodID",valueOf(StoryID.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  StoryID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView StoryTitle_txt,StoryDesc_txt,userName;
        CardView card;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            StoryTitle_txt=itemView.findViewById(R.id.StoryTitleLabel);
            StoryDesc_txt=itemView.findViewById(R.id.StoryDescView);
            card = itemView.findViewById(R.id.storySingleCard);
            userName = itemView.findViewById(R.id.storySingleCardUserName);

        }
    }
}
