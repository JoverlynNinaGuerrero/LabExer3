package com.guerrero.labexer3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

public class SecondActivity extends AppCompatActivity {


    SharedPreferences preferences;
    TextView tv_display;
    EditText et_Filename;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv_display = (TextView) findViewById(R.id.tv_display);
        et_Filename = (EditText) findViewById(R.id.etFilename);
        preferences = getSharedPreferences(et_Filename.getText().toString(), Context.MODE_PRIVATE);
    }
    public void loadShared(View view){
        String message = preferences.getString("message","");
        tv_display.setText(message);

    }
    public void loadIntStore(View view){
        StringBuffer buffer = new StringBuffer();
        int read=0;
        try{
            fis = openFileInput(et_Filename.getText().toString());
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        tv_display.setText(buffer.toString());

    }
    public void loadIntCache(View view){
        StringBuffer stringBuffer = new StringBuffer();
        File folder = getCacheDir();
        File file = new File(folder,et_Filename.getText().toString());
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            int read=1;

            while((read = fis.read())!=-1){
                stringBuffer.append((char)read);
            }
        }catch(Exception e){

        }
        tv_display.setText(stringBuffer.toString());
    }
    public void loadExtCache(View view){
        StringBuffer stringBuffer = new StringBuffer();
        File folder = getExternalCacheDir();
        File file = new File(folder,et_Filename.getText().toString());
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            int read=1;

            while((read = fis.read())!=-1){
                stringBuffer.append((char)read);
            }
        }catch(Exception e){

        }
        tv_display.setText(stringBuffer.toString());
    }
    public void loadExtStorage(View view){
        StringBuffer stringBuffer = new StringBuffer();
        File folder = getExternalFilesDir("Temp");
        File file = new File(folder,et_Filename.getText().toString());
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            int read=1;

            while((read = fis.read())!=-1){
                stringBuffer.append((char)read);
            }
        }catch(Exception e){

        }
        tv_display.setText(stringBuffer.toString());
    }
    public void loadExtPublic(View view){
        StringBuffer stringBuffer = new StringBuffer();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder,et_Filename.getText().toString());
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            int read=1;

            while((read = fis.read())!=-1){
                stringBuffer.append((char)read);
            }
        }catch(Exception e){

        }
        tv_display.setText(stringBuffer.toString());
    }
    public void prev(View view){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}