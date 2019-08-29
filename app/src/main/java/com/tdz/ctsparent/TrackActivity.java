package com.tdz.ctsparent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

public class TrackActivity extends AppCompatActivity {

    String child_mail;
    TabHost tabhost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        tabhost = (TabHost) findViewById(R.id.tabhost);
        tabhost.setup();
        TabHost.TabSpec tabspec;

        tabspec = tabhost.newTabSpec("screen1");
        tabspec.setContent(R.id.tab1);
        tabspec.setIndicator("Call Log", getResources().getDrawable(R.drawable.f1_resize));
        tabhost.addTab(tabspec);

        tabspec = tabhost.newTabSpec("screen2");
        tabspec.setContent(R.id.tab2);
        tabspec.setIndicator("Contact", null);

        tabhost.addTab(tabspec);

        tabspec = tabhost.newTabSpec("screen3");
        tabspec.setContent(R.id.tab3);
        tabspec.setIndicator("SMS", null);

        tabhost.addTab(tabspec);

        tabspec = tabhost.newTabSpec("screen4");
        tabspec.setContent(R.id.tab4);
        tabspec.setIndicator("GPS", null);

        tabhost.addTab(tabspec);

        tabhost.setCurrentTabByTag("screen1");
    }
}