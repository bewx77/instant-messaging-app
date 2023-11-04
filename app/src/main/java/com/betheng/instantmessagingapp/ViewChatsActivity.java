package com.betheng.instantmessagingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Map;

public class ViewChatsActivity extends AppCompatActivity {
    EditText msgEditText;
    Button sendButton;

    String chatWithUser;

    ArrayList<String> messages;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chats);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        chatWithUser = getIntent().getStringExtra("user");
        setTitle("Chat with " + chatWithUser);

        ParseQuery query = new ParseQuery<>("Message");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, messages);

        msgEditText = findViewById(R.id.msgEditText);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseObject message = new ParseObject("Message");
                message.put("content", msgEditText.getText().toString());
                message.put("from", ParseUser.getCurrentUser().getUsername());
                message.put("to", chatWithUser);
                message.saveInBackground();
            }
        });
    }
}