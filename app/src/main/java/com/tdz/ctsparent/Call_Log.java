package com.tdz.ctsparent;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Call_Log extends AppCompatActivity {

    String child_mail;
    TextView tvCallLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call__log);

        Intent i = getIntent();
        child_mail = i.getStringExtra("child_mail");

        tvCallLog = findViewById(R.id.tvCallLog);

        getCallLog();

    }

    private void getCallLog() {


        String type = "login";
        CallTrack callTrack = new CallTrack(this);
        System.out.println(child_mail + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAa");
        callTrack.execute(type, child_mail);

    }

    public void goToFour(View view) {
        Intent i = new Intent();
        i.setClass(Call_Log.this,FourActivity.class);
        i.putExtra("child_mail",child_mail);
        startActivity(i);
    }

    private class CallTrack extends AsyncTask<String, Void, String> {
        AlertDialog alertDialog;
        Context context;

        private CallTrack(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected String doInBackground(String... voids) {
            String type = voids[0];
            String p_login_url = "http://192.168.43.208/cts/parent/c_call_log.php";

            if (type.equals("login")) {
                System.out.println("I'm in doInBackground ");
                try {
                    String child_mail = voids[1];


                    System.out.println(child_mail + " BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");


                    URL url = new URL(p_login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);

                    System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCC");

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    String post_data = URLEncoder.encode("child_mail", "UTF-8") + "=" +
                            URLEncoder.encode(child_mail, "UTF-8");




                    System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDdd");
                    System.out.println("POST_DATA " + post_data + " POST_DATA");


                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();

                    bufferedWriter.close(); //write is close
                    outputStream.close();  // output stream close

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                    String result = "";
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                        System.out.println(" > " + line + " <= is LINE ");
                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    System.out.println(" > " + result + " <= is RESULT");
                    return result;

                } catch (MalformedURLException e) {
                    System.out.println("URL Error  ");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("I/O Error in URL (Network Connection)");
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            //alertDialog.setTitle("Alert");
            System.out.println("I'm onPreExecute() ");
        }

        @Override
        protected void onPostExecute(String result) {
            //alertDialog.setMessage(result);
            //alertDialog.show();
            //System.out.println("Result is AAAAAAAAAAAAAAAAAA " + result + " AAAAAAAAAAAAAA");

            tvCallLog.setText(result.toString());

            if (result.equals("Csuccess")) {
                //System.out.println("OOOOOOOOOOOOOOO" + "OOOOOOOOOOOOOOOO");
            } else {
                //Toast.makeText(getApplicationContext(), "User ID or Password Error", Toast.LENGTH_LONG).show();
            }
            //loginChildActivity.setContentView(R.layout.activity_main);
            System.out.println("I'm onPostExecute() ");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            System.out.println("I'm onProgressUpdate() ");
        }
    }
}