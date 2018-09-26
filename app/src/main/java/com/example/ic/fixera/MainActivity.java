package com.example.ic.fixera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ic.fixera.Fragments.Login;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.flContent,new Login()).commit();

    }
}
