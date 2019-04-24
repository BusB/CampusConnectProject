package com.example.android.campusconnectproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button logIn = findViewById(R.id.login_button);

        // Set a click listener on that View
        logIn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent logInIntent = new Intent(SignInActivity.this, RecycleView.class);

                // Start the new activity
                startActivity(logInIntent);
            }
        });
    }
}
