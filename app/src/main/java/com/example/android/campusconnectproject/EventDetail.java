package com.example.android.campusconnectproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.IOException;

import biweekly.Biweekly;
import biweekly.ICalVersion;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.io.text.ICalWriter;


public class EventDetail extends AppCompatActivity {

    private CardView eventDetail;
    private TextView dateView;
    private TextView eventNameView;
    private TextView eventDescripView;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        setTitle("Event Details");

        eventDetail = findViewById(R.id.card_view);
        dateView = findViewById(R.id.event_date);
        eventNameView = findViewById(R.id.event_name);
        eventDescripView = findViewById(R.id.event_description);
        auth = FirebaseAuth.getInstance();


        final Intent intent = getIntent();
        dateView.setText(intent.getStringExtra("date"));
        eventNameView.setText(intent.getStringExtra("name"));
        eventDescripView.setText(Html.fromHtml(intent.getStringExtra("description")));


        Button addToFaves = findViewById(R.id.add_to_faves);
                addToFaves.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        FirebaseUser user = auth.getCurrentUser();
                        DatabaseReference userFaves = database.getReference(user.getUid());
                        String str = intent.getStringExtra("event");
                        userFaves.child("Favorited-events").push().setValue(str);

                        Intent intent1 = new Intent(EventDetail.this, ProfileActivity.class);
                        startActivity(intent1);
                    }
                });


    }
}
