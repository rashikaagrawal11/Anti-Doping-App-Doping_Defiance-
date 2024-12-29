package com.example.doping_defiance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class community extends AppCompatActivity {

    private EditText usernameEditText;
    private Spinner roomSpinner;
    private Button joinChatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        ArrayList<String> roomList = new ArrayList<>();
        roomList.add("Select a room"); // Default selection option
        roomList.add("Latest News");
        roomList.add("Opportunities");
        roomList.add("Anti-doping");
        roomList.add("General queries");

        ArrayAdapter<String> roomAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomList);
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usernameEditText = findViewById(R.id.username);
        roomSpinner = findViewById(R.id.room_spinner);
        roomSpinner.setAdapter(roomAdapter);
        joinChatButton = findViewById(R.id.btn_join_chat);

        // Set up the Join Chat button click listener
        joinChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();

                // Validation: Ensure both fields are filled
                if (username.isEmpty()) {
                    Toast.makeText(community.this, "Please enter a username.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String selectedRoom = roomSpinner.getSelectedItem().toString();

                Log.d("MainActivity2", "Selected Room: " + selectedRoom);

                if (selectedRoom.equals("Select a room")) {
                    Toast.makeText(community.this, "Please select a room.", Toast.LENGTH_SHORT).show();
                    return; 
                } else {
                    Intent intent = new Intent(community.this, community2.class);
                    intent.putExtra("username", username); 
                    intent.putExtra("ROOM_NAME", selectedRoom); 

                    startActivity(intent);
                }
            }
        });
    }
}
