package vn.hoangphan.trygcm.services;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import vn.hoangphan.trygcm.constants.Constants;
import vn.hoangphan.trygcm.net.APIService;

/**
 * Created by Hoang Phan on 11/17/2015.
 */
public class GCMService {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "GCMService";

    private static GCMService instance = new GCMService();
    private Activity mActivity;

    public static GCMService getInstance() {
        return instance;
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public boolean register() {
        if (checkPlayService()) {
            String regId = "";
            try {
                regId = GoogleCloudMessaging.getInstance(mActivity).register(Constants.SENDER_ID);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(regId)) {
                Log.e(TAG, "No reg id found");
            } else {
                AppService.getInstance().saveConfig(Constants.REG_ID, regId);
                APIService.updateGcmId(regId);
                return true;
            }
        } else {
            Log.i(TAG, "No valid Google Play Services APK found.");
        }
        return false;
    }

    private boolean checkPlayService() {
        final int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mActivity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        GooglePlayServicesUtil.getErrorDialog(resultCode, mActivity, PLAY_SERVICES_RESOLUTION_REQUEST).show();
                    }
                });
            } else {
                Log.i(TAG, "This device is not supported.");
            }
            return false;
        }
        return true;
    }
}
