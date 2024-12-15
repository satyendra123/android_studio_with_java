package com.example.homeautomation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ControlActivity extends AppCompatActivity {

    private Button btnTurnOnLight, btnTurnOffLight, btnTurnOnFan, btnTurnOffFan, btnGoToSettings;
    private TextView tvStatus;

    private static final String PREFS_NAME = "ESP_PREFS";
    private static final String KEY_IP_ADDRESS = "IP_ADDRESS";
    private String ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        // Initialize UI components
        btnTurnOnLight = findViewById(R.id.btn_turn_on_light);
        btnTurnOffLight = findViewById(R.id.btn_turn_off_light);
        btnTurnOnFan = findViewById(R.id.btn_turn_on_fan);
        btnTurnOffFan = findViewById(R.id.btn_turn_off_fan);
        tvStatus = findViewById(R.id.tv_status);
        btnGoToSettings = findViewById(R.id.btn_go_to_settings);

        // Load saved IP address
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        ipAddress = sharedPreferences.getString(KEY_IP_ADDRESS, "");

        if (ipAddress.isEmpty()) {
            Toast.makeText(this, "IP Address not set. Go back and set it first.", Toast.LENGTH_LONG).show();
            finish(); // Close the activity
        }

        // Button Logic
        btnTurnOnLight.setOnClickListener(view -> sendRequest("/turn_on", "Light: ON"));
        btnTurnOffLight.setOnClickListener(view -> sendRequest("/turn_off", "Light: OFF"));

        btnTurnOnFan.setOnClickListener(view -> sendRequest("/turn_on_fan", "Fan: ON"));
        btnTurnOffFan.setOnClickListener(view -> sendRequest("/turn_off_fan", "Fan: OFF"));

        // Go to Settings Button
        btnGoToSettings.setOnClickListener(view -> {
            Intent intent = new Intent(ControlActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Optional: Close the current activity
        });

    }

    private void sendRequest(final String endpoint, final String successMessage) {
        new Thread(() -> {
            try {
                URL url = new URL("http://" + ipAddress + endpoint); // Create the URL
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    runOnUiThread(() -> {
                        tvStatus.setText(successMessage);
                        Toast.makeText(ControlActivity.this, "Command Sent Successfully", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    showError("Failed to send request. Response Code: " + responseCode);
                }
            } catch (IOException e) {
                showError("Error: " + e.getMessage());
            }
        }).start();
    }

    private void showError(final String errorMessage) {
        runOnUiThread(() -> Toast.makeText(ControlActivity.this, errorMessage, Toast.LENGTH_SHORT).show());
    }
}
