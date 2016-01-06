package vn.hoangphan.trygcm.services;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import vn.hoangphan.trygcm.constants.Constants;

/**
 * Created by Hoang Phan on 11/17/2015.
 */
public class GCMListenerService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
        if (Constants.FACE_RECOGNIZED.equals(data.getString(Constants.COLLAPSE_KEY))) {
            Intent intent = new Intent("android.intent.action.NOTIFICATION");
            sendBroadcast(intent);
        }
        Log.d("Message Received From", from);
        Log.d("data", data.toString());
    }
}
