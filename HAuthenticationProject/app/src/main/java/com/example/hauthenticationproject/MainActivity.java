package com.example.hauthenticationproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edit_username, edit_phoneno;
    Button button_submit;
    String username,phoneno;
    ArrayList<NameValuePair> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            edit_username = (EditText) findViewById(R.id.edit_username);
            edit_phoneno = (EditText) findViewById(R.id.edit_password);
            button_submit = (Button) findViewById(R.id.button_submit);
        Spinner spinner = (Spinner) findViewById(R.id.statespinnerl);



        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Choose State");
        categories.add("Andra Pradesh");
        categories.add("Arunachal Pradesh");
        categories.add("Assam");
        categories.add("Bihar");
        categories.add("Chhattisgarh");
        categories.add("Goa");
        categories.add("Gujarat");
        categories.add("Haryana");
        categories.add("Himachal Pradesh");
        categories.add("Jammu and Kashmir");
        categories.add("Jharkhand");
        categories.add("Karnataka");
        categories.add("Kerala");
        categories.add("Madya Pradesh");
        categories.add("Maharashtra");
        categories.add("Manipur");
        categories.add("Meghalaya");
        categories.add("Mizoram");
        categories.add("Nagaland");
        categories.add("Orissa");
        categories.add("Punjab");
        categories.add("Rajasthan");
        categories.add("Sikkim");
        categories.add("Tamil Nadu");
        categories.add("Telagana");
        categories.add("Tripura");
        categories.add("Uttaranchal");
        categories.add("Uttar Pradesh");
        categories.add("West Bengal");


        // Creating adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.statespinner,categories);

        // attaching data adapter to spinner
        spinner.setAdapter(adapter);
            button_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   username =  edit_username.getText().toString();
                   phoneno = edit_phoneno.getText().toString();
//                   DataSubmitClass dataSubmitClass = new DataSubmitClass();
//                   dataSubmitClass.execute(username, phoneno);
                    Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

    }

    public class DataSubmitClass extends AsyncTask<String,Integer,Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params) {
            list=new ArrayList<>();
            list.add(new BasicNameValuePair("username",params[0]));
            list.add(new BasicNameValuePair("phoneno",params[1]));

            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://192.168.43.124/AuthenticationScript.php");
            try
            {
                httpPost.setEntity(new UrlEncodedFormEntity(list));

                HttpResponse httpResponse=httpClient.execute(httpPost);
                HttpEntity httpEntity=httpResponse.getEntity();

                InputStream responseget;
                responseget=httpEntity.getContent();
                InputStreamReader inputStreamReader=new InputStreamReader(responseget);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String getline;
                StringBuilder sb=new StringBuilder();

                while((getline=bufferedReader.readLine())!=null)
                {
                    sb.append(getline);
                }

                String data=sb.toString();
                Log.d("log_response",data);

                JSONObject json=new JSONObject(data);
                String r = json.getString("response");
                Log.d("log",r);
                if(r.equalsIgnoreCase("insert")) {
                    return true;
                }
            }
            catch (Exception e)
            {
                Log.d("Log_Exception",e.toString());
            }
            return false;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            if(aBoolean){
                Toast.makeText(MainActivity.this,"Inserted",Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(MainActivity.this,"Not Inserted",Toast.LENGTH_SHORT).show();

            }
            super.onPostExecute(aBoolean);
        }
    }
}
