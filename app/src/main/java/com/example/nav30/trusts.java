package com.example.nav30;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class trusts extends AppCompatActivity {

    String s1, s2, s3, s4, s5, s6;
    Button b;
    TextView t;
    Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               setContentView(R.layout.activity_trusts);

    }

    public void fun2(View view) {
        myIntent = new Intent(this, MainActivity.class);
        Log.i("DEBUG", "Trusted Activity3");
        final SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.nav30", Context.MODE_PRIVATE);
        t = (TextView) findViewById(R.id.textVie);
        s1 = ((EditText) findViewById(R.id.t1)).getText().toString().trim();
        s2 = ((EditText) findViewById(R.id.t2)).getText().toString().trim();
        s3 = ((EditText) findViewById(R.id.t3)).getText().toString().trim();
        s4 = ((EditText) findViewById(R.id.t4)).getText().toString().trim();
        s5 = ((EditText) findViewById(R.id.t5)).getText().toString().trim();
        s6 = ((EditText) findViewById(R.id.t6)).getText().toString().trim();
        if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty() || s5.isEmpty() || s6.isEmpty()) {
            t.setText("Enter all the details");
        }
        else {
            sharedPreferences.edit().putString("name2", s1).apply();
            sharedPreferences.edit().putString("phone2", s2).apply();
            sharedPreferences.edit().putString("email2", s3).apply();
            sharedPreferences.edit().putString("name3", s4).apply();
            sharedPreferences.edit().putString("phone3", s5).apply();
            sharedPreferences.edit().putString("email3", s6).apply();

            Log.i("DEBUG", "Updated .  trusted activity");
            startActivity(myIntent);

        }
    }
}
