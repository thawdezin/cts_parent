package com.tdz.ctsparent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

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