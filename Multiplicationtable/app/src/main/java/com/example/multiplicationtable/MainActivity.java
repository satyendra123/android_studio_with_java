package com.example.multiplicationtable;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText numberEditText;
    private Button generateButton;
    private ListView multiplicationTableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberEditText = findViewById(R.id.numberEditText);
        generateButton = findViewById(R.id.generateButton);
        multiplicationTableListView = findViewById(R.id.multiplicationTableListView);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the number from EditText
                String input = numberEditText.getText().toString();

                // Check if the input is valid
                if (!input.isEmpty()) {
                    int number = Integer.parseInt(input);
                    generateTable(number);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void generateTable(int number) {
        ArrayList<String> tableList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            tableList.add(number + " x " + i + " = " + (number * i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tableList);
        multiplicationTableListView.setAdapter(adapter);
    }
}
