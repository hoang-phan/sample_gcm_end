package vn.hoangphan.trygcm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import vn.hoangphan.trygcm.constants.Constants;

public class NotificationActivity extends AppCompatActivity {
    private TextView mTvNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mTvNotification = (TextView)findViewById(R.id.tv_notification);
        mTvNotification.setText(String.format("%s is on the door", getIntent().getStringExtra(Constants.COLLAPSE_KEY)));
    }
}
