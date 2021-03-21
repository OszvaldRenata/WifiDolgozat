package com.example.wifidolgozat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    TextView wifiTextView;
    BottomNavigationView bottomNavigationView;
    WifiManager wifiManager;
    WifiInfo wifiInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.wifi_on:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            wifiTextView.setText("Nincs engedélyezve");
                            Intent panel = new Intent(Settings.Panel.ACTION_WIFI);
                            startActivityForResult(panel, 0);
                        }
                        wifiManager.setWifiEnabled(true);
                        wifiTextView.setText("Wifi bekapcsolva");
                        break;

                    case R.id.wifi_off:
                        wifiManager.setWifiEnabled(false);
                        wifiTextView.setText("Wifi kikapcsolva");
                        break;

                    case R.id.wifi_info:
                        break;


                }
                return true;
            }
        });



    }

    private void init() {
        wifiTextView = findViewById(R.id.textViewId);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
    }
}