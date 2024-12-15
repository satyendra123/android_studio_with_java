package com.example.homeautomation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "ESP_PREFS";
    private static final String KEY_IP_ADDRESS = "IP_ADDRESS";

    private EditText etIpAddress;
    private Button btnSaveIp, btnNext;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etIpAddress = findViewById(R.id.et_ip_address);
        btnSaveIp = findViewById(R.id.btn_set_ip);
        btnNext = findViewById(R.id.btn_go_to_control);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Load previously saved IP address
        String savedIpAddress = sharedPreferences.getString(KEY_IP_ADDRESS, "");
        if (!savedIpAddress.isEmpty()) {
            etIpAddress.setText(savedIpAddress);
            Toast.makeText(this, "IP Address loaded", Toast.LENGTH_SHORT).show();
        }

        // Save IP Address
        btnSaveIp.setOnClickListener(v -> {
            String ipAddress = etIpAddress.getText().toString();
            if (!ipAddress.isEmpty()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_IP_ADDRESS, ipAddress);
                editor.apply();
                Toast.makeText(MainActivity.this, "IP Address saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Please enter a valid IP address", Toast.LENGTH_SHORT).show();
            }
        });

        // Move to the control screen
        btnNext.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ControlActivity.class)));
    }
}
