package com.example.ic.fixera.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ic.fixera.Fragments.language;
import com.example.ic.fixera.R;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sha=getSharedPreferences("login",MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        String logi=sha.getString("logggin",null);
        if(logi!=null){
            startActivity(new Intent(MainActivity.this,TabsLayouts.class));
            finish();
        }else {
            getSupportFragmentManager().beginTransaction().replace(R.id.flContent,new language()).commit();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.flContent);
        fragment.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
