package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText newName, newPassword;
    Button registerButton;
    TextView loginLink;
    DatabaseHelper dbHelper; // ✅ Declare database helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        newName = findViewById(R.id.newName);
        newPassword = findViewById(R.id.newPassword);
        registerButton = findViewById(R.id.registerButton);
        loginLink = findViewById(R.id.loginLink);

        dbHelper = new DatabaseHelper(this); // ✅ Initialize database helper

        // Handle Register button click
        registerButton.setOnClickListener(v -> {
            String name = newName.getText().toString();
            String pass = newPassword.getText().toString();

            if (name.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dbHelper.registerUser(name, pass)) {
                Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                // Go back to login
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "User already exists or error occurred", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle "Already Registered?" link click
        loginLink.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        });
    }
}
