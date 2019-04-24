package com.example.android.campusconnectproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;


public class EventDetail extends AppCompatActivity {

    private CardView eventDetail;
    private TextView dateView;
    private TextView eventNameView;
    private TextView eventDescripView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        eventDetail = findViewById(R.id.card_view);
        dateView = findViewById(R.id.event_date);
        eventNameView = findViewById(R.id.event_name);
        eventDescripView = findViewById(R.id.event_description);


        final Intent intent = getIntent();
        dateView.setText(intent.getStringExtra("date"));
        eventNameView.setText(intent.getStringExtra("name"));
        eventDescripView.setText(Html.fromHtml(intent.getStringExtra("description")));

        Button addToFaves = findViewById(R.id.add_to_faves);
                addToFaves.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(EventDetail.this, ProfileActivity.class);
                        ICalendar ical = Biweekly.parse(intent.getStringExtra("event")).first();
                        intent1.putExtra("event", Biweekly.write(ical).go());
                        startActivity(intent1);
                    }
                });


    }
}
