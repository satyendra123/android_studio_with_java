package com.example.multiscreen;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewItem1, textViewItem2, textViewItem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize TextViews
        textViewItem1 = findViewById(R.id.textViewItem1);
        textViewItem2 = findViewById(R.id.textViewItem2);
        textViewItem3 = findViewById(R.id.textViewItem3);

        // Get data from Intent
        String item1 = getIntent().getStringExtra("ITEM_1");
        String item2 = getIntent().getStringExtra("ITEM_2");
        String item3 = getIntent().getStringExtra("ITEM_3");

        // Display data in TextViews
        textViewItem1.setText("Item 1: " + item1);
        textViewItem2.setText("Item 2: " + item2);
        textViewItem3.setText("Item 3: " + item3);
    }
}
