package com.example.nav30;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;




public class ContactActivity extends AppCompatActivity {
    String s1,s2,s3,s4,s5,s6,s7,s8,s9;

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

    t=(TextView) findViewById(R.id.TV);
        SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.nav30", Context.MODE_PRIVATE);

        String s4 = sharedPreferences.getString("name2","");
        String s5 = sharedPreferences.getString("phone2","");
        String s6 = sharedPreferences.getString("email2","");
        String s7 = sharedPreferences.getString("name3","");
        String s8 = sharedPreferences.getString("phone3","");
        String s9 = sharedPreferences.getString("email3","");

        t.setText("Person 1 :");
        t.append("\n\n"+s4);
        t.append("\n"+s5);
        t.append("\n"+s6);
        t.append("\n\n\n Person 2");
        t.append("\n\n"+s7);
        t.append("\n"+s8);
        t.append("\n"+s9);

    }
}
