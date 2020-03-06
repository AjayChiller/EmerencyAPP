package com.example.nav30;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class page extends AppCompatActivity {

    ImageButton b1;
    TextView t;
    String s1, s2, s3;
    int flag = 0;
    Intent myIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("DEBUG", "page Activity");
        setContentView(R.layout.activity_page);



    }


    public void fun(View v) {
        myIntent = new Intent(this, trusts.class);
        t = findViewById(R.id.textView10);
        final SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.nav30", Context.MODE_PRIVATE);
        Log.i("DEBUG", "updating");
        t.setText("");
        flag = 0;
        s1 = ((EditText) findViewById(R.id.t1)).getText().toString().trim();
        s2 = ((EditText) findViewById(R.id.t2)).getText().toString().trim();
        s3 = ((EditText) findViewById(R.id.t3)).getText().toString().trim();

        if (s1.isEmpty()) {
            flag = 1;
            t.setText("Enter Username");
        }
        if (s2.isEmpty()) {
            flag = 1;
            t.append("\nEnter Phone number");
        } else if (s2.length() < 10) {
            flag = 1;
            t.append("\nIncorrect Phone Number");
        }
        if (s3.isEmpty()) {
            flag = 1;
            t.append("\nEnter Email");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(s3).matches()) {
            flag = 1;
            t.append("\n Incorrect Email ");
        }


        if (flag != 1) {

            sharedPreferences.edit().putString("name", s1).apply();
            sharedPreferences.edit().putString("phone", s2).apply();
            sharedPreferences.edit().putString("email", s3).apply();
            Log.i("DEBUG", "page Activity done");

            // String u = sharedPreferences.getString("name", "");

            startActivity(myIntent);

        }
    }
}
