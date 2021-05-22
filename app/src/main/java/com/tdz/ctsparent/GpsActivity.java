package com.tdz.ctsparent;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class GpsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_gps);

        Uri uri=Uri.parse("geo:12.1212,21.2121"); //("geo:"+lat+","+lon);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}
