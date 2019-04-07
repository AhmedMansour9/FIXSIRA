package com.example.ic.fixera.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ic.fixera.Fragments.TabsLayouts;
import com.example.ic.fixera.Fragments.language;
import com.fixsira.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sha;
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sha=getSharedPreferences("login",MODE_PRIVATE);
        shared=getSharedPreferences("Language",MODE_PRIVATE);
        String Lan=shared.getString("Lann",null);
        if(Lan!=null) {
            Locale locale = new Locale(Lan);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
        setContentView(R.layout.activity_main);
        String logi=sha.getString("logggin",null);
        String uservendor=sha.getString("uservendor",null);
        if(uservendor!=null){
            startActivity(new Intent(MainActivity.this,UserVendor_Orders.class));
            finish();
        }else {
            if (logi != null) {
                startActivity(new Intent(MainActivity.this, Navigation.class));
                finish();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new language()).commit();
            }
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
