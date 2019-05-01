package com.example.android.campusconnectproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;

public class RecycleView extends AppCompatActivity {

    private List<VEvent> events;
    private EventAdapter adapter;
    private android.support.v7.widget.RecyclerView recyclerView;
    private ICalendar ical;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

//    private static final String SELECTED_ITEM = "arg_selected_item";
//
//    private BottomNavigationView mBottomNav;
//    private int mSelectedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();


        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null)
                    startActivity(new Intent(RecycleView.this, LoginActivity.class));
            }
        };




        setTitle("TC Events");



        try {initialData();} catch (Exception e) {
            Toast.makeText(this, "Could not initialize data", Toast.LENGTH_SHORT).show();
        }

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EventAdapter(events, this);
        recyclerView.setAdapter(adapter);

    }

    public void enableStrictMode ()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    private void initialData() throws Exception {
        enableStrictMode();
        URL tcEventURL = new URL("https://www.tc.columbia.edu/events/ics-feed/");
        InputStream tcEventStream = tcEventURL.openStream();
        ical = Biweekly.parse(tcEventStream).first();
        events = ical.getEvents();

    }

    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override

    public void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authListener);
    }

}
