package com.example.nav30;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class smsActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms2);

        checkForSmsPermission();

        smsSent();
    }
    public void send()
    {
        checkForSmsPermission();

        smsSent();
    }   public void checkForSmsPermission() {
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
    public void smsSent() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.nav30", Context.MODE_PRIVATE);

        String n1 = sharedPreferences.getString("phone2", "");
        String n2 = sharedPreferences.getString("phone3", "");
        String s= sharedPreferences.getString("name","");
        String l1 = sharedPreferences.getString("ln", "Unknown");
        String l2 = sharedPreferences.getString("lt", "Unknown");

        Toast.makeText(this,"Message Sent",Toast.LENGTH_LONG).show();
        Log.i("Debug","Messahe sent");
        // Find the sms_message view.
        String smsMessage = "EMERGENCY"+"   Please HELP!!"+
                "This Message is sent because "+s+" is in an emergency situation and needs your help " +
                "The last known location is :"+
                l1+" "+l2;



        // Set the service center address if needed, otherwise null.
        String scAddress = null;
        // Set pending intents to broadcast
        // when message sent and when delivered, or set to null.
        PendingIntent sentIntent = null, deliveryIntent = null;
        // Check for permission first.
        checkForSmsPermission();
        // Use SmsManager.
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(n1, scAddress, smsMessage,
                sentIntent, deliveryIntent);
        smsManager.sendTextMessage(n2, scAddress, smsMessage,
                sentIntent, deliveryIntent);



        Toast.makeText(this,"Message Sent",Toast.LENGTH_LONG).show();
        Log.i("Debug","Messahe sent");

    }






    /**
     * Sends an intent to start the activity.
     *
     * @param view  View (Retry button) that was clicked.
     */
    public void retryApp(View view) {
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        startActivity(intent);
    }
}
