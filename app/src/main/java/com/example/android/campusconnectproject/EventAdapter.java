package com.example.android.campusconnectproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;

import java.text.SimpleDateFormat;
import java.util.List;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.util.DateTimeComponents;
import biweekly.util.ICalDateFormat;
import biweekly.util.com.google.ical.values.DateValue;
import biweekly.util.com.google.ical.values.DateValueImpl;


public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<VEvent> events;
    private Context context;
    private SimpleDateFormat dFormat;
    private SimpleDateFormat detailFormat;
    VEvent event;

    public EventAdapter(List<VEvent> events, Context context) {
        this.events = events;
        this.context = context;
    }

    public VEvent getEvent() {
        return event;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        final EventViewHolder viewHolder = new EventViewHolder(view, context);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecycleView eventsListActivity = (RecycleView) context;
                detailFormat = new SimpleDateFormat("E MMM d 'at' h:mm");
                ICalendar ical = new ICalendar();
                ical.addEvent(event);

                Intent intent = new Intent(eventsListActivity, EventDetail.class);

                event = events.get(viewHolder.getAdapterPosition());
                intent.putExtra("event", Biweekly.write(ical).go());
                intent.putExtra("name", event.getSummary().getValue());
                intent.putExtra("date",  detailFormat.format(event.getDateStart().getValue()));
                intent.putExtra("description", Jsoup.parse(event.getDescription().getValue()).text());
                eventsListActivity.startActivity(intent);
            }
        });
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        event = events.get(position);
        dFormat = new SimpleDateFormat("EEE\nMMM\ndd");
        holder.getDateView().setText(dFormat.format(event.getDateStart().getValue()));
        holder.getEventNameView().setText(event.getSummary().getValue());

    }

    @Override
    public int getItemCount() {
        return events.size();
    }


//    private void passData(VEvent event) {
//        Intent i = new Intent(context, EventDetail.class);
//        i.putExtra("name", event.getSummary().getValue());
//        i.putExtra("date", event.getDateStart().getValue());
//        i.putExtra("description", event.getDescription().getValue());
//        context.startActivity(i);
//    }
}

