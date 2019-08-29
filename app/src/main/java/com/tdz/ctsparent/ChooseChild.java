package com.tdz.ctsparent;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class ChooseChild extends AppCompatActivity {
    String parent_id;
    public int aa = 0;
    EditText etChildID;
    TextView tvChildID;
    public static String sender = "ccc";

    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_child);

        Intent i = getIntent();
        parent_id = i.getStringExtra("parent_id");
        System.out.println("getStringExtra Result is >>>>>>>>>>>>>>>> " + parent_id);
        etChildID = findViewById(R.id.etChildID);
        tvChildID = findViewById(R.id.tvChildID);
        btnOK = findViewById(R.id.btnChooseOK);
        findYourChild();
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aa += aa;
                Toast.makeText(getApplicationContext(),"A",Toast.LENGTH_SHORT).show();
                Intent i = new Intent();
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setClass(ChooseChild.this,FourActivity.class);
                i.putExtra("child_mail",etChildID.getText().toString());
                sender = etChildID.getText().toString();
                sender = "ccc";
                startActivity(i);
                finish();
            }
        });
    }

    private void findYourChild() {

        String type = "find";
        FindYourChild findYourChild = new FindYourChild(this);
        System.out.println(  " and "   + "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
        findYourChild.execute(type, parent_id);

    }

    public void goToMain(View view) {
        Intent intent_name = new Intent();
        intent_name.setClass(ChooseChild.this, WelcomeActivity.class);
        startActivity(intent_name);
    }


    private class FindYourChild extends AsyncTask<String,Void,String> {
        AlertDialog alertDialog;
        Context context;

        private FindYourChild(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected String doInBackground(String... voids) {
            String type = voids[0];
            String p_login_url = "http://192.168.43.208/cts/parent/choose_child.php";

            if(type.equals("find")){
                System.out.println("I'm in doInBackground ");
                try {
                    String parent_id = voids[1];


                    System.out.println(parent_id  + "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");


                    URL url = new URL(p_login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);

                    System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCC");

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                    String post_data = URLEncoder.encode("parent_id","UTF-8")+"="+
                            URLEncoder.encode(parent_id,"UTF-8");


                    System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDdd");
                    System.out.println("POST_DATA " + post_data + " POST_DATA");


                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();

                    bufferedWriter.close(); //write is close
                    outputStream.close();  // output stream close

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

                    String result = "";
                    String line = "";

                    while((line = bufferedReader.readLine()) != null){
                        result += line;
                        System.out.println(" > "+line + " <= is LINE ");
                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    System.out.println(" > "+result + " <= is RESULT");
                    return result;

                } catch (MalformedURLException e) {
                    System.out.println("URL Error  ");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("I/O Error in URL ");
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

            System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            tvChildID.setText(result);


            System.out.println("OOOOOOOOOOOOOOO" + "OOOOOOOOOOOOOOOO");

            System.out.println("I'm onPostExecute() ");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            System.out.println("I'm onProgressUpdate() ");
        }
    }
}