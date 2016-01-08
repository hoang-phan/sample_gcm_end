package vn.hoangphan.trygcm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.hoangphan.trygcm.services.AppService;
import vn.hoangphan.trygcm.services.GCMService;
import vn.hoangphan.trygcm.services.NotificationService;

public class MainActivity extends Activity {
    private Button mStartBtn, mStopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppService.getInstance().setContext(this);
        GCMService.getInstance().setActivity(this);

        mStartBtn = (Button)findViewById(R.id.btn_start);
        mStopBtn = (Button)findViewById(R.id.btn_stop);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentService = new Intent(MainActivity.this, NotificationService.class);
                startService(intentService);
                finish();
            }
        });

         mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentService = new Intent(MainActivity.this, NotificationService.class);
                stopService(intentService);
                finish();
            }
        });
    }
}
