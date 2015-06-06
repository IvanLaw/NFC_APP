package com.fyp.nfc.nfcapp;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import com.fyp.nfc.nfcapp.WifiState;

public class wifi extends Activity {

    private TextView mTextView;
    private String wifi_SSID = "\"CityU WLAN (WPA)\"";
    private WifiState wifiState = new WifiState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        mTextView = (TextView)findViewById(R.id.tv);
        int i = wifiState.checkWifiState(this);
        mTextView.setText(wifiState.StatusMessage(i));
    }

    @Override
    protected void onResume() {
        super.onResume();
        int i = wifiState.checkWifiState(this);
        mTextView.setText(wifiState.StatusMessage(i));
    }





}
