package com.example.android.campusconnectproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;

public class ProfileActivity extends RecycleView {

    private List<VEvent> events = new ArrayList<>();
    private VEvent eventNew;
    private EventAdapter adapter;
    private android.support.v7.widget.RecyclerView recyclerView;
    TextView favoritesHead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setTitle("Profile");

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.recycler_view_profile:
                        break;
                    case R.id.recycler_view:
                        Intent recycler = new Intent (ProfileActivity.this, RecycleView.class);
                        startActivity(recycler);
                        break;
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.recycler_view_profile);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        favoritesHead = findViewById(R.id.favorite_heading);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userFaves = database.getReference("Favorited-events");
            userFaves.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot Q : dataSnapshot.getChildren()){
                        String str = Q.getValue(String.class);
                        ICalendar ical = Biweekly.parse(str).first();
                        VEvent event = ical.getEvents().get(0);
                        events.add(event);
                        adapter = new EventAdapter(events, ProfileActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(ProfileActivity.this, "Error loading Firebase", Toast.LENGTH_SHORT).show();
                }
            });

        adapter = new EventAdapter(events, this);
        recyclerView.setAdapter(adapter);

    }

    public void favoritesButton(MenuItem item) {
//        Intent recycler = new Intent(ProfileActivity.this, RecycleView.class);
//        startActivity(recycler);
    }
}
