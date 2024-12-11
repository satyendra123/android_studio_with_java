package com.example.homeautomation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText etIpAddress;
    private Button btnTurnOn, btnTurnOff;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etIpAddress = findViewById(R.id.et_ip_address);
        btnTurnOn = findViewById(R.id.btn_turn_on);
        btnTurnOff = findViewById(R.id.btn_turn_off);
        tvStatus = findViewById(R.id.tv_status);

        btnTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipAddress = etIpAddress.getText().toString();
                if (!ipAddress.isEmpty()) {
                    sendRequest(ipAddress, "/turn_on");
                } else {
                    Toast.makeText(MainActivity.this, "Please enter the IP address", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnTurnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipAddress = etIpAddress.getText().toString();
                if (!ipAddress.isEmpty()) {
                    sendRequest(ipAddress, "/turn_off");
                } else {
                    Toast.makeText(MainActivity.this, "Please enter the IP address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendRequest(final String ipAddress, final String endpoint) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Construct the URL for the ESP32 endpoint
                    URL url = new URL("http://" + ipAddress + endpoint);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Handle success
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvStatus.setText("Status: " + (endpoint.equals("/turn_on") ? "ON" : "OFF"));
                            }
                        });
                    } else {
                        // Handle failure
                        showError("Failed to send request");
                    }
                } catch (IOException e) {
                    showError("Error: " + e.getMessage());
                }
            }
        }).start();
    }

    private void showError(final String errorMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
