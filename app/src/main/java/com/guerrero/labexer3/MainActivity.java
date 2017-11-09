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
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    EditText et_Data;
    EditText et_Filename;
    FileInputStream fis;
    TextView text_View;
    TextView text_View2;
    FileOutputStream fos;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_Data = (EditText) findViewById(R.id.etData);
        text_View = (TextView) findViewById(R.id.textView);
        text_View2 = (TextView) findViewById(R.id.textView2);
        et_Filename = (EditText) findViewById(R.id.etFilename);
        btn_next = (Button) findViewById(R.id.btn_Next);
        preferences = getSharedPreferences(et_Filename.getText().toString(), Context.MODE_PRIVATE);
    }

    public void writeData(File file, String message){

        try{
            fos = new FileOutputStream(file);
            fos.write(message.getBytes());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void nexttt (View view) {
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivity(intent);
    }

    public void sharedPref(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("message",et_Data.getText().toString());
        editor.commit();
        Toast.makeText(this,"Successfully Written to Shared Preference",Toast.LENGTH_LONG).show();
    }
    public void intStore(View view) {
        String message = et_Data.getText().toString();
        try{
            fos = openFileOutput(et_Filename.getText().toString(), Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully Written to Internal Storage", Toast.LENGTH_LONG).show();
    }
    public void intCache(View view){
        File folder = getCacheDir();
        File file = new File(folder,et_Filename.getText().toString());
        String message = et_Data.getText().toString();
        writeData(file,message);
        Toast.makeText(this,"Successfully Written to Internal Cache",Toast.LENGTH_LONG).show();

    }
    public void extCache(View view){
        File folder = getExternalCacheDir();
        File file = new File(folder,et_Filename.getText().toString());
        String message = et_Data.getText().toString();
        writeData(file,message);
        Toast.makeText(this,"Successfully Written to External Cache",Toast.LENGTH_LONG).show();
    }
    public void extStorage(View view){
        File folder = getExternalFilesDir("Temp");
        File file = new File(folder,et_Filename.getText().toString());
        String message = et_Data.getText().toString();
        writeData(file,message);
        Toast.makeText(this,"Successfully Written to External Storage",Toast.LENGTH_LONG).show();
    }
    public void extPublic(View view){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder,et_Filename.getText().toString());
        String message = et_Data.getText().toString();
        writeData(file,message);
        Toast.makeText(this,"Successfully Written to External Public Storage",Toast.LENGTH_LONG).show();

    }

}