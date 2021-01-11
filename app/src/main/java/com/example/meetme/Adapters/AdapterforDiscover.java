package com.example.meetme.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetme.DiscoverActivity;
import com.example.meetme.EventDetailActivity;
import com.example.meetme.Models.Event;
import com.example.meetme.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterforDiscover  extends RecyclerView.Adapter<AdapterforDiscover.MyViewHolder>  {
    String event_names[]={"Discussion about LOTR","Mythology of Ancient Greece","Comments on CyberPunk","Corona Virus","Developments on AI","Discussion about Pride and Prejudice"};
    String tv_release_date_comments_item[]={"05/01/2021","06/01/2021","06/01/2021","07/01/2021","07/01/2021","10/01/2021","11/01/2021","15/01/2021"};
    String event_description[]={"Comments on the Lord of the Rings","The story of Hades","Comments on new game called CyberPunk","Last developments about vaccines","Discussion about GPT-3 and neuralink  brain chip","Tartışma etkinliği","Tartışma etkinliği","Jane Austen is a feminist or not?"};
    Boolean favorite_comments_item[]={false,true,true,true,false,false,true,true};


    private Context context;
    List<Event> Data;

    public AdapterforDiscover(Context context, List<Event> data){
        this.context = context;
        this.Data = data;
    }
    @NonNull
    @Override
    public AdapterforDiscover.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =  LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new AdapterforDiscover.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterforDiscover.MyViewHolder holder, final int position) {
        Picasso.get().load( R.drawable.logoaction).into(holder.poster_movie_item_comments);
        holder.event_name.setText(Data.get(position).getEventName());
        holder.tv_release_date_comments_item.setText(Data.get(position).getEventTime());
        holder.event_description.setText(Data.get(position).getEventDescription());
        holder.favorite_comments_item.setChecked(false);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("event_name", Data.get(position).getEventName());
                intent.putExtra("event_description", Data.get(position).getEventDescription());
                intent.putExtra("event_time", Data.get(position).getEventTime());
                intent.putExtra("event_link", Data.get(position).getEventLink());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Data.size();
    }
/*
    public AdapterforDiscover(Context context) {
        this.context = context;
    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView poster_movie_item_comments;
        public TextView event_name;
        public TextView tv_release_date_comments_item;
        public TextView event_description;
        public CheckBox favorite_comments_item;
        public CardView cardView;


        public MyViewHolder (@NonNull View itemView){
            super(itemView);
            poster_movie_item_comments =(ImageView) itemView.findViewById(R.id.poster_movie_item_comments);
            event_name =(TextView) itemView.findViewById(R.id.event_name);
            tv_release_date_comments_item = (TextView)itemView.findViewById(R.id.tv_release_date_comments_item);
            event_description= (TextView)itemView.findViewById(R.id.event_description);
            cardView = itemView.findViewById(R.id.cardView);
            favorite_comments_item=(CheckBox) itemView.findViewById(R.id.favorite_comments_item);
            context = itemView.getContext();
            itemView.setClickable(true);
           // itemView.setOnClickListener(this);



        }
    }
}
