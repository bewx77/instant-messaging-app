package com.betheng.instantmessagingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ViewChatsActivity extends AppCompatActivity {
    EditText msgEditText;
    Button sendButton;

    String chatWithUser;

    ArrayList<String> messages = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chats);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        chatWithUser = getIntent().getStringExtra("user");
        setTitle("Chat with " + chatWithUser);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, messages);
        ListView chatListView = findViewById(R.id.chatListView);
        chatListView.setAdapter(arrayAdapter);

        ParseQuery<ParseObject> query1 = new ParseQuery("Message");
        query1.whereEqualTo("from",ParseUser.getCurrentUser().getUsername().toString());
        query1.whereEqualTo("to",chatWithUser);

        ParseQuery<ParseObject> query2 = new ParseQuery("Message");
        query2.whereEqualTo("to",ParseUser.getCurrentUser().getUsername().toString());
        query2.whereEqualTo("from",chatWithUser);

        ArrayList<ParseQuery<ParseObject>> queries = new ArrayList<>();
        queries.add(query1);
        queries.add(query2);

        ParseQuery<ParseObject> query = ParseQuery.or(queries);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if ( e == null){
                    for (ParseObject object:objects){
                        String msg;
                        msg = (object.getString("from").equals(chatWithUser)) ? ">   " + object.getString("content") : object.getString("content");
                        Log.i("Info", msg);
                        Log.i("Info", object.getString("from"));
                        messages.add(msg);
                        arrayAdapter.notifyDataSetChanged();
                    }
                } else {
                    e.printStackTrace();
                }
            }
        });

        msgEditText = findViewById(R.id.msgEditText);
        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseObject message = new ParseObject("Message");
                message.put("content", msgEditText.getText().toString());
                message.put("from", ParseUser.getCurrentUser().getUsername());
                message.put("to", chatWithUser);
                message.saveInBackground();
                messages.add(msgEditText.getText().toString());
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}