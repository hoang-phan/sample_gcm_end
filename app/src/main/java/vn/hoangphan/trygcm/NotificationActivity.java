package vn.hoangphan.trygcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import vn.hoangphan.trygcm.services.AppService;
import vn.hoangphan.trygcm.services.GCMService;

public class NotificationActivity extends AppCompatActivity {
    BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        AppService.getInstance().setContext(this);
        GCMService.getInstance().setActivity(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                GCMService.getInstance().register();
            }
        }).start();
    }

    @Override
    public void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter("android.intent.action.NOTIFICATION");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("receiver", "receive");
            }
        };

        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }
}
