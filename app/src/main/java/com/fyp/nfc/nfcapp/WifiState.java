package com.fyp.nfc.nfcapp;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Ivan on 06/06/2015.
 */
public class WifiState {

    private final String STATE1  = "Wi-Fi is not enabled";
    private final String STATE2  = "Wi-Fi is not suitable";
    private final String STATE3  = "Error";
    private final String STATE0  = "Wi-Fi is connected";
    private final String wifi_SSID = "\"CityU WLAN (WPA)\"";

    public WifiState() {

    }

    public int checkWifiState(Context context){
        try {
            WifiManager mWifiManager=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo=mWifiManager.getConnectionInfo();
            if (!mWifiManager.isWifiEnabled() || wifiInfo.getSSID() == null) {
                return 1;
            }

            if (!wifiInfo.getSSID().equals(this.wifi_SSID)) {
                return 2;
            }

            return 0;
        }
        catch (  Exception e) {
            return 3;
        }
    }

    public String StatusMessage(int i) {
        switch (i){
            case 0: return STATE0;
            case 1: return STATE1;
            case 2: return STATE2;
            case 3: return STATE3;
            default: return STATE3;
        }
    }
}
