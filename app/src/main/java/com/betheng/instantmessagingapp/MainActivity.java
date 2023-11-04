package com.betheng.instantmessagingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.w3c.dom.Text;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    boolean isLogin = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigning ID of the toolbar to a variable
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // using toolbar as ActionBar
        setSupportActionBar(toolbar);

        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView questionTextView = findViewById(R.id.questionTextView);
        Button logInButton = findViewById(R.id.logInButton);
        TextView changeStateTextView = findViewById(R.id.changeStateTextView);
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (isLogin){
                    // Log in user
                    ParseUser.logInInBackground(username,password , new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            String message;
                            if (e == null){
                                message = "Sign in Successful!";
                                Log.i("Info", message);
                                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), UsersListActivity.class);
                                startActivity(intent);
                            } else {
                                message = e.getMessage().toString();
                                Log.i("Info", message);
                                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    // Sign up user
                    ParseUser user = new ParseUser();
                    user.setUsername(username);
                    user.setPassword(password);

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            String message;
                            if (e == null){
                                message = "Sign up Successful!";
                                Log.i("Info", message);
                                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), UsersListActivity.class);
                                startActivity(intent);
                            } else {
                                message = e.getMessage().toString();
                                Log.i("Info", message);
                                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        changeStateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLogin) {
                    isLogin = false;
                    titleTextView.setText("Sign Up for Twitter");
                    questionTextView.setText("Already have an account?");
                    changeStateTextView.setText("Log In >");
                    logInButton.setText("Sign Up");
                } else {
                    isLogin = true;
                    titleTextView.setText("Log in to Twitter");
                    questionTextView.setText("Don't have an account?");
                    changeStateTextView.setText("Sign Up >");
                    logInButton.setText("Log in");
                }
            }
        });

//        ParseObject soccerPlayer = new ParseObject("SoccerPlayer");
//        soccerPlayer.put("playerName", "A. Wed");
//        soccerPlayer.put("yearOfBirth", 1997);
//        soccerPlayer.put("emailContact", "a.wed@email.io");
//        soccerPlayer.saveInBackground();

    }
}