package com.tdz.ctsparent;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.tdz.ctsparent.ChooseChild.sender;

public class LogActivity extends AppCompatActivity {

    String child_mail;
    TextView tvCall_Log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_log);

        Intent i = getIntent();
        child_mail = i.getStringExtra("child_mail");

        System.out.println("TAB " + sender + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        tvCall_Log = findViewById(R.id.tvChildID);
        tvCall_Log.setText(child_mail);
    }
}
