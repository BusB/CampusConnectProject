package com.example.android.campusconnectproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button createAccount = findViewById(R.id.create_account);

        // Set a click listener on that View
        createAccount.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent createAccountIntent = new Intent(SignUpActivity.this, RecycleView.class);

                // Start the new activity
                startActivity(createAccountIntent);
            }
        });
    }
}
