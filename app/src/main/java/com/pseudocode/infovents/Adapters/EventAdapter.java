package com.pseudocode.infovents.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pseudocode.infovents.Classes.Event;
import com.pseudocode.infovents.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Baymax on 10/10/2015.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.CustomViewHolder> {
    private List<Event> mDataset;
    private SparseBooleanArray selectedItems;
    private Context mContext;


    public EventAdapter(Context context, List<Event> myDataset) {
        mContext = context;
        mDataset = myDataset;
        selectedItems = new SparseBooleanArray();

    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Event event = mDataset.get(position);
        Date ds =  new Date();
        Date de = new Date();
        try {
            ds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(event.getStartDate().toString());
            de = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(event.getEndDate().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String day = new SimpleDateFormat("dd").format(ds);
        String month = new SimpleDateFormat("MMM").format(ds);
        String start = new SimpleDateFormat("MMM dd HH:mm").format(ds);


        Picasso.with(mContext).load(event.getEventImage())
                .error(R.drawable.side_nav_bar)
                .placeholder(R.drawable.side_nav_bar)
                .into(holder.imageView);

        Picasso.with(mContext).load(event.getEventCover())
                .error(R.drawable.side_nav_bar)
                .placeholder(R.drawable.side_nav_bar)
                .into(holder.imageCover);

        holder.textViewEvent.setOnClickListener(clickListener);
        holder.imageView.setOnClickListener(clickListener);


        holder.textViewEvent.setTag(holder);
        holder.textViewDate.setTag(holder);
        holder.textViewVenue.setTag(holder);
        holder.textViewDay.setTag(holder);
        holder.textViewMonth.setTag(holder);


        holder.imageView.setTag(holder);
        holder.imageCover.setTag(holder);

        //Setting text view title
        holder.textViewEvent.setText(event.getEventName());
        holder.textViewDate.setText(start);
        holder.textViewVenue.setText(event.getEventLocation());
        holder.textViewDay.setText(day);
        holder.textViewMonth.setText(month);


    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView imageCover;
        TextView textViewEvent;
        TextView textViewDate;
        TextView textViewVenue;
        TextView textViewDay;
        TextView textViewMonth;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.imageCover = (ImageView) view.findViewById(R.id.event_cover);
            this.textViewEvent = (TextView) view.findViewById(R.id.event);
            this.textViewDate = (TextView) view.findViewById(R.id.time);
            this.textViewVenue = (TextView) view.findViewById(R.id.location);
            this.textViewDay = (TextView) view.findViewById(R.id.eventDay);
            this.textViewMonth = (TextView) view.findViewById(R.id.eventMonth);
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomViewHolder holder = (CustomViewHolder) view.getTag();
            int position = holder.getAdapterPosition();
            Event event = mDataset.get(position);

        }
    };
}