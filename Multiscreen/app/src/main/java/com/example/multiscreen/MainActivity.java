package com.example.multiscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextItem1, editTextItem2, editTextItem3;
    private Button buttonPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize EditText and Button
        editTextItem1 = findViewById(R.id.editTextText1);
        editTextItem2 = findViewById(R.id.editTextText2);
        editTextItem3 = findViewById(R.id.editTextText3);
        buttonPlaceOrder = findViewById(R.id.button);

        // Set click listener for the button
        buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input data
                String item1 = editTextItem1.getText().toString();
                String item2 = editTextItem2.getText().toString();
                String item3 = editTextItem3.getText().toString();

                // Start the second activity and pass data
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("ITEM_1", item1);
                intent.putExtra("ITEM_2", item2);
                intent.putExtra("ITEM_3", item3);
                startActivity(intent);
            }
        });
    }
}
