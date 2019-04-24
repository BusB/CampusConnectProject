package com.example.android.campusconnectproject;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;

public class ProfileActivity extends AppCompatActivity {

    private ArrayList<VEvent> events;
    private VEvent event;
    private EventAdapter adapter;
    private android.support.v7.widget.RecyclerView recyclerView;
    private ICalendar ical;
    TextView favoritesHead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setTitle("Profile");

        recyclerView = findViewById(R.id.recycler_view_profile);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        favoritesHead = findViewById(R.id.favorite_heading);

        Intent intent = getIntent();
        ical = Biweekly.parse(intent.getStringExtra("event")).first();
        event = ical.getEvents().get(0);
        if(events==null){
            events = new ArrayList<>();
            events.add(event);} else {
            events.add(event);
        }

        adapter = new EventAdapter(events, this);
        recyclerView.setAdapter(adapter);

    }

}
