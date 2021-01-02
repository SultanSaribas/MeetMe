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
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetme.DiscoverActivity;
import com.example.meetme.EventDetailActivity;
import com.example.meetme.R;
import com.squareup.picasso.Picasso;

public class AdapterforDiscover  extends RecyclerView.Adapter<AdapterforDiscover.MyViewHolder>  {
    String event_names[]={"Event A","Event B","Event C","Event D","Event E","Event F","Event G","Event H"};
    String tv_release_date_comments_item[]={"27/01/2021","27/01/2021","27/01/2021","27/01/2021","27/01/2021","27/01/2021","27/01/2021","27/01/2021"};
    String event_description[]={"Tartışma etkinliği","Tartışma etkinliği","Tartışma etkinliği","Tartışma etkinliği","Tartışma etkinliği","Tartışma etkinliği","Tartışma etkinliği","Tartışma etkinliği"};
    Boolean favorite_comments_item[]={false,true,true,true,false,false,true,true};


    private Context context;
    @NonNull
    @Override
    public AdapterforDiscover.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new AdapterforDiscover.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterforDiscover.MyViewHolder holder, int position) {
        Picasso.get().load( R.drawable.logoaction).into(holder.poster_movie_item_comments);
        holder.event_name.setText(event_names[position]);
        holder.tv_release_date_comments_item.setText(tv_release_date_comments_item[position]);
        holder.event_description.setText(event_description[position]);
        holder.favorite_comments_item.setChecked(favorite_comments_item[position]);

    }

    @Override
    public int getItemCount() {
        return event_names.length;
    }

    public AdapterforDiscover(Context context) {
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public ImageView poster_movie_item_comments;
        public TextView event_name;
        public TextView tv_release_date_comments_item;
        public TextView event_description;
        public CheckBox favorite_comments_item;


        public MyViewHolder (@NonNull View itemView){
            super(itemView);
            poster_movie_item_comments =(ImageView) itemView.findViewById(R.id.poster_movie_item_comments);
            event_name =(TextView) itemView.findViewById(R.id.event_name);
            tv_release_date_comments_item = (TextView)itemView.findViewById(R.id.tv_release_date_comments_item);
            event_description= (TextView)itemView.findViewById(R.id.event_description);
            favorite_comments_item=(CheckBox) itemView.findViewById(R.id.favorite_comments_item);
            context = itemView.getContext();
            itemView.setClickable(true);
            itemView.setOnClickListener(this);



        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, EventDetailActivity.class);
            context.startActivity(intent);
        }
    }
}
