package com.fyp.nfc.nfcapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class main extends Activity {

    public void beginActivity(View v){
        Intent i = null;
        switch(v.getId()){
            case R.id.button1:
                i = new Intent(this, intro.class);
                break;
            case R.id.button2:
                i = new Intent(this, wifi.class);
                break;
            case R.id.button3:
                i = new Intent(this, scan.class);
                break;
            case R.id.button4:
                i = new Intent(this, info.class);
                break;
        }

        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("user_name");
            Log.v("hahahahahhahahaha", value);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
