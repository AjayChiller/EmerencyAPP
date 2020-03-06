package com.example.nav30;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import static android.app.PendingIntent.getActivity;
import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView t;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        smsActivity sm = new smsActivity();

        checkForSmsPermission();


        Log.i("DEBUG","11111111");
        final Toast smssent=Toast.makeText(this,"Message Sent",Toast.LENGTH_LONG);
        sharedPreferences=this.getSharedPreferences("com.example.nav30", Context.MODE_PRIVATE);
        String s1 = sharedPreferences.getString("name","default");
        final Toast panicdisabledmsg=Toast.makeText(this,"Panic mode is disabled",Toast.LENGTH_SHORT);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

       sharedPreferences=this.getSharedPreferences("com.example.nav30", Context.MODE_PRIVATE);

        if(s1=="default")
        {
            Intent myIntent = new Intent(this, page.class);
            startActivity(myIntent);
        }
        else
        {
            t=(TextView)findViewById(R.id.textVV);
            t.setText("Welcome\n");
            t.append(s1);
        }
        final Intent myIntentsms = new Intent(this, smsActivity.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String mo=sharedPreferences.getString("panicmode","1");
                int mode=Integer.parseInt(mo);
                Log.i("ans",""+mo);

                String met=sharedPreferences.getString("panicavtivity","0");
                int method=Integer.parseInt(met);
                Log.i("ans","panic method"+method);
                startActivity(myIntentsms);
                if(mode == 1)

                {

                    String s = sharedPreferences.getString("name", "");
                    String l1 = sharedPreferences.getString("ln", "Unknown");
                    String l2 = sharedPreferences.getString("lt", "Unknown");
                    String message = "This Message is sent because " + s + " is in an emergency situation and needs your help .\n\n" +
                            "The last known location is : https://www.google.com/maps/search/?api=1&query=" + l1 + "," + l2;

                    String n1 = sharedPreferences.getString("phone2", "9945437198");

                    String n2 = sharedPreferences.getString("phone3", "8553105483");


                    String toNumber = "919945437198";
                    try {
                        Log.i("ans", "Inside mode ==1");


                        PendingIntent sentIntent = null, deliveryIntent = null;
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(n1, NULL, message,
                                sentIntent, deliveryIntent);
                        smsManager.sendTextMessage(n2, NULL, message,
                                sentIntent, deliveryIntent);

                        smssent.show();
                        Log.i("ans", "Message sent");
                        Log.i("ans", "INSIDE panic mode");
                    }
                    catch (Exception e)
                    {
                        Log.i("DEBUG" ,e.toString());


                    }
                    if(method == 1) {
                        Log.i("ans","INSIDE MAIL");

                        String s1 = sharedPreferences.getString("email2", "");

                        String s2 = sharedPreferences.getString("email3", "");

                        String recipientList = s1 + "," + s2;
                        String[] recipients = recipientList.split(",");

                        String subject = "EMERGENCY";

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                        intent.putExtra(Intent.EXTRA_TEXT, message);

                        intent.setType("message/rfc822");
                        startActivity(Intent.createChooser(intent, "Choose an email client"));


                        smsActivity ob=new smsActivity();
                        ob.send();

                    }
                    else
                    {
                        String msg = "This Message is sent because " + s + " is in an emergency situation and needs your help .\n\n to get the last known location" +
                                "\n\nClick here to Open Google Map : https://www.google.com/maps/search/?api\n\n\n copy paste this location= "+ l1 +" "+ l2;
                        StringBuilder trust;
                        String mtrust = sharedPreferences.getString("mtrust", "+918553105483");

                        Log.i("ans",mtrust);

                        Log.i("ans","WHATSAPP");

                        try {


                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            Log.i("DEBUG",msg);
                            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + mtrust + "&text=" + msg ));
                            startActivity(intent);
                            Log.i("ans","HOIIIII");

                        } catch (Exception e) {
                            Log.i("ans","ERROR");
                        e.printStackTrace();
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")));


                        }




                    }

                    Snackbar.make(view, "Panic Mode Activated", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();


                }
                else
                {
                Log.i("ans","Else");
                panicdisabledmsg.show();

                }
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }



    public void hospi(View v)
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q=direction+to+hospital+near+me"));
        startActivity(intent);
    }
    public void ambu(View v)
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q=direction+to+ambulance+near+me"));
        startActivity(intent);
    }
    public void fire(View v)
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q=direction+to+fire+station+near+me"));
        startActivity(intent);
    }
    public void cop(View v)
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q=direction+to+police+station+near+me"));
        startActivity(intent);
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.i("DEBUG","2222222222222");

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //@SuppressWarnings("StatementWithEmptyBody")
/*
    public boolean onNavigationItemSelected() {
        Log.i("DEBUG","36666666666666666666");
        return onNavigationItemSelected();

    }
*/
    //@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.i("DEBUG","3333333");
        int id = item.getItemId();
        Log.i("DEBUG","3333333");

        if (id == R.id.nav_home) {

            Log.i("DEBUG","444444444");

        } else if (id == R.id.nav_Maps) {

            Log.i("DEBUG","55555555555");
            Intent myIntent = new Intent(this, MapsActivity.class);
            startActivity(myIntent);

        } else if (id == R.id.nav_Contacts) {
            Intent myIntent = new Intent(this, ContactActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_Settings) {
            Intent myIntent = new Intent(this, SettingsActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_Login) {
            Intent myIntent = new Intent(this, page.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_Contact) {
            Intent myIntent = new Intent(this, About_Us.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, getString(R.string.permission_not_granted));
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            // Permission already granted. Enable the SMS button.
            //Toast.makeText(this,"Permission granted",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Processes permission request codes.
     *
     * @param requestCode  The request code passed in requestPermissions()
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either PERMISSION_GRANTED or PERMISSION_DENIED. Never null.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        // For the requestCode, check if permission was granted or not.
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (permissions[0].equalsIgnoreCase(Manifest.permission.SEND_SMS)
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted. Enable sms button.

                } else {
                    // Permission denied.
                    Log.d(TAG, getString(R.string.failure_permission));
                    Toast.makeText(this, getString(R.string.failure_permission),
                            Toast.LENGTH_LONG).show();
                    // Disable the sms button.
                    //disableSmsButton();
                }
            }
        }
    }


}

