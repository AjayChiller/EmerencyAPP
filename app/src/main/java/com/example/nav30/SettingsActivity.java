package com.example.nav30;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences=this.getSharedPreferences("com.example.nav30", Context.MODE_PRIVATE);
        String s1 = sharedPreferences.getString("name2","default");
        String s2 = sharedPreferences.getString("name2","default");

        RadioButton p2= findViewById(R.id.p1);
       // p1.setText(s1);
        //p2.setText(s2);
        final RadioButton r1= findViewById(R.id.whatsapp);





        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
            //    .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);






        }
    }
    public void savefun(View v)
    {
        final RadioButton p1= findViewById(R.id.p1);

        RadioButton r1= findViewById(R.id.whatsapp);
        Switch s=findViewById(R.id.s);
        if(s.isChecked())
        {
            sharedPreferences.edit().putString("panicmode", "1").apply();
        }
        else
        {
            sharedPreferences.edit().putString("panicmode", "0").apply();
        }
        if(r1.isChecked())
        {
            sharedPreferences.edit().putString("panicavtivity", "0").apply();
            Log.i("ans","whatsapp mode saved");
        }
        else
        {
            sharedPreferences.edit().putString("panicavtivity", "1").apply();
            Log.i("ans","mail mode saved");
            String met=sharedPreferences.getString("panicavtivity","0");
            int method=Integer.parseInt(met);
            Log.i("ans","panic method"+method);
        }
        if(p1.isChecked())
        {
            String s1 = sharedPreferences.getString("phone2","+919945437198");
            sharedPreferences.edit().putString("mtrust", s1).apply();

        }
        else
        {

            String s1 = sharedPreferences.getString("phone3","+919945437198");

            sharedPreferences.edit().putString("mtrust", s1).apply();

        }
        Toast.makeText(this,"saved",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    // Handle the camera action






}