package com.tdz.ctsparent;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etMail, etPass;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etMail = findViewById(R.id.etMail);
        etPass = findViewById(R.id.etPass);

        btnReg = findViewById(R.id.btnReg);

        btnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String name = etName.getText().toString();
        String mail = etMail.getText().toString();
        String password = etPass.getText().toString();

        String type = "login";
        ParentRegister parentRegister = new ParentRegister(this);
        parentRegister.execute(type, name, mail, password);

    }

    private class ParentRegister extends AsyncTask<String,Void,String> {

        AlertDialog alertDialog;
        Context context;

        private ParentRegister(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected String doInBackground(String... voids) {
            String type = voids[0];
            String child_login_url = "http://192.168.43.208/cts/parent/register.php";

            if(type.equals("login")){
                System.out.println("I'm in doInBackground ");
                try {
                    String name = voids[1];
                    String mail = voids[2];
                    String password = voids[3];

                    URL url = new URL(child_login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                    String post_data = URLEncoder.encode("name","UTF-8")+"="+
                            URLEncoder.encode(name,"UTF-8")+"&"+

                            URLEncoder.encode("mail","UTF-8")+"="+
                            URLEncoder.encode(mail,"UTF-8")+"&"+

                            URLEncoder.encode("password","UTF-8")+"="+
                            URLEncoder.encode(password,"UTF-8");


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
            System.out.println("Result is AAAAAAAAAAAAAAAAAA " + result + " AAAAAAAAAAAAAA");


            if(result.equals("Csuccess")) {
                //Intent i = new Intent(MainActivity.this,ResultActivity2.class);
                // i.putExtra("number",num);
                //startActivityForResult(i,10);

                //LayoutInflater inflater = LayoutInflater.from(context);
                // View yourView = inflater.inflate(R.layout.activity_main, null, false);
                // then bring it to front
                //yourView.bringToFront();


                Toast.makeText(RegisterActivity.this,etPass.getText().toString(),Toast.LENGTH_SHORT).show();
                Intent intent_name = new Intent();
                intent_name.setClass(getApplicationContext(), NewChildActivity.class);
                intent_name.putExtra("parent_id", etMail.getText().toString());
                startActivity(intent_name);

                // Intent intent_name = new Intent();
                //intent_name.setClass(loginChildActivity.this,MainActivity.class);
                //startActivity(intent_name);

                //context.startActivity(new Intent(context, MainActivity.class));


                //super.setContentView(R.layout.activity_main);

                //MainActivity.testMethod();

                //(new MainActivity()).execute();

                // btnLogin.setEnabled(false);
                //btnLoginHide.setEnabled(true);
                //loginChildActivity.setContentView(R.layout.activity_main);
                System.out.println("OOOOOOOOOOOOOOO" + "OOOOOOOOOOOOOOOO");
            }
            else{
                Toast.makeText(getApplicationContext(),"Registeration ERROR",Toast.LENGTH_LONG).show();
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
