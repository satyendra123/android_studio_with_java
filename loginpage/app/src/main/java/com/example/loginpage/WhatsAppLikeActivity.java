package com.example.loginpage;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;

public class WhatsAppLikeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ListView drawerList, chatListView;

    String[] drawerItems = {
            "New group", "New broadcast", "Linked devices", "Starred", "Payments", "Read all", "Settings"
    };

    String[] chats = {
            "John Doe", "Alice", "Family Group", "Office", "Bob", "Anita", "Friends"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapplike);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.left_drawer);
        chatListView = findViewById(R.id.chatListView);

        // Set drawer items
        ArrayAdapter<String> drawerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, drawerItems);
        drawerList.setAdapter(drawerAdapter);

        drawerList.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("DEBUG", "Clicked: " + drawerItems[position]);
            Toast.makeText(this, drawerItems[position] + " clicked", Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawers();
        });

        // Set chat list items
        ArrayAdapter<String> chatAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, chats);
        chatListView.setAdapter(chatAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.whatsapp_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_camera) {
            Toast.makeText(this, "Camera clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_message) {
            Toast.makeText(this, "Message clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_more) {
            drawerLayout.openDrawer(GravityCompat.END);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
