package com.tdz.ctsparent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.nio.channels.InterruptedByTimeoutException;

public class FourActivity extends AppCompatActivity {

    String child_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        Intent i = getIntent();
        child_mail = i.getStringExtra("child_mail");

    }

    public void viewCallLog(View view) {
        Intent i = new Intent();
        i.setClass(FourActivity.this,Call_Log.class);
        i.putExtra("child_mail",child_mail);
        startActivity(i);

    }

    public void viewSMS(View view) {
        Intent i = new Intent();
        i.setClass(FourActivity.this,SMS.class);
        i.putExtra("child_mail",child_mail);
        startActivity(i);
    }

    public void viewContact(View view) {
        Intent i = new Intent();
        i.setClass(FourActivity.this,PhoneContact.class);
        i.putExtra("child_mail",child_mail);
        startActivity(i);
    }

    public void viewLocation(View view) {
        Intent i = new Intent();
        i.setClass(FourActivity.this,GPS.class);
        i.putExtra("child_mail",child_mail);
        startActivity(i);
    }

    public void backToMain(View view) {
        Intent i = new Intent();
        i.setClass(FourActivity.this, NewChildActivity.class);
        startActivity(i);
    }
}
