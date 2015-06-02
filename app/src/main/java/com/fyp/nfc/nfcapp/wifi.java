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


public class wifi extends Activity {

    private TextView mTextView;
    private String wifi_SSID = "\"CityU WLAN (WPA)\"";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        mTextView = (TextView)findViewById(R.id.tv);
        checkWifiState(this, wifi_SSID);

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkWifiState(this, wifi_SSID);
    }

    public boolean checkWifiState(Context context, String wifi_SSID){
        try {
            WifiManager mWifiManager=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo=mWifiManager.getConnectionInfo();
            if (!mWifiManager.isWifiEnabled() || wifiInfo.getSSID() == null) {
                mTextView.setText("Wi-Fi is not enabled");
                return false;
            }

            if (!wifiInfo.getSSID().equals(wifi_SSID)) {
                mTextView.setText("Please connect our Wi-Fi: " + wifi_SSID);
                return false;
            }

            mTextView.setText("Wi-Fi is connected");
            return true;
        }
        catch (  Exception e) {
            return false;
        }
    }



}
