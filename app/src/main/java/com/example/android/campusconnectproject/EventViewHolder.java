package com.example.android.campusconnectproject;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import biweekly.component.VEvent;

public class EventViewHolder extends RecyclerView.ViewHolder{

    private CardView cardView;
    private TextView dateView;
    private TextView eventNameView;
    private VEvent event;

    public TextView getDateView() {
        return dateView;
    }

    public TextView getEventNameView() {
        return eventNameView;
    }

    private Context context;

    public EventViewHolder (View itemView, final Context context) {
        super(itemView);
        cardView = itemView.findViewById(R.id.card_view);
        dateView = itemView.findViewById(R.id.event_date);
        eventNameView = itemView.findViewById(R.id.event_name);
        this.context = context;

    }

}
