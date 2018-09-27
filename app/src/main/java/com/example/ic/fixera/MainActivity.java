package com.example.ic.fixera;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ic.fixera.Fragments.Login;
import com.example.ic.fixera.Fragments.TabsLayouts;
import com.example.ic.fixera.Fragments.language;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sha=getSharedPreferences("login",MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        String logi=sha.getString("logggin",null);
        if(logi!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent,new TabsLayouts()).commit();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent,new language()).commit();
        }



    }
}
