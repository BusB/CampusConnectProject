package com.example.android.campusconnectproject;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import biweekly.Biweekly;
import biweekly.ICalendar;

public class MainActivity extends AppCompatActivity {

    private TextView mainText;
    private List<ICalendar> icals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button action = findViewById(R.id.test_recycler);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecycleView.class);
                startActivity(intent);
            }
        });
    }

    public void enableStrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    public void writeFeed(View view) throws Exception {
        enableStrictMode();
        URL tcEventURL = new URL("https://www.tc.columbia.edu/events/ics-feed/");
        InputStream tcEventStream = tcEventURL.openStream();
        icals = Biweekly.parse(tcEventStream).all();

//        String filePath = this.getFilesDir().getPath() + "/feed.ics";

        String feed = Biweekly.write(icals).go();

        mainText = findViewById(R.id.main_text);
        mainText.setText(feed);
    }


}
