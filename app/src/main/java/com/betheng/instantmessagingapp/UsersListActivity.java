package com.betheng.instantmessagingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class UsersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        Button logOutButton = findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ParseUser.logOutInBackground(new LogOutCallback() {
                   @Override
                   public void done(ParseException e) {
                       String message;
                       if (e == null){
                           message = "Log out Successful!";
                           Log.i("Info", message);
                           Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                           finish();
                       } else {
                           message = e.getMessage().toString().substring(e.getMessage().indexOf(" "));
                           Log.i("Info", message);
                           Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                       }
                   }
               });
           }
        });
    }
}