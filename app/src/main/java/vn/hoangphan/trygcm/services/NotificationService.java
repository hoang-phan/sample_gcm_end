package vn.hoangphan.trygcm.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import java.io.FileDescriptor;

import vn.hoangphan.trygcm.MainActivity;
import vn.hoangphan.trygcm.NotificationActivity;
import vn.hoangphan.trygcm.R;
import vn.hoangphan.trygcm.constants.Constants;

/**
 * Created by eastagile-tc on 1/8/16.
 */
public class NotificationService extends Service {

    private static String TAG = "NotificationService";
    private BroadcastReceiver mReceiver;

    @Override
    public void onCreate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GCMService.getInstance().register();
            }
        }).start();

        IntentFilter intentFilter = new IntentFilter("android.intent.action.FACEREC_NOTIFICATION");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Intent activityIntent = new Intent(NotificationService.this, NotificationActivity.class);
                activityIntent.putExtra(Constants.COLLAPSE_KEY, intent.getStringExtra(Constants.COLLAPSE_KEY));
                activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(activityIntent);
            }
        };

        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Intent intent1 = new Intent(this, MainActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, 0);

        Notification noti = new Notification.Builder(getApplicationContext())
                .setContentTitle("Pratikk")
                .setContentText("Subject")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1234, noti);

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
        stopForeground(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IBinder() {
            @Override
            public String getInterfaceDescriptor() throws RemoteException {
                return TAG;
            }

            @Override
            public boolean pingBinder() {
                return false;
            }

            @Override
            public boolean isBinderAlive() {
                return false;
            }

            @Override
            public IInterface queryLocalInterface(String descriptor) {
                return null;
            }

            @Override
            public void dump(FileDescriptor fd, String[] args) throws RemoteException {

            }

            @Override
            public void dumpAsync(FileDescriptor fd, String[] args) throws RemoteException {

            }

            @Override
            public boolean transact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
                return false;
            }

            @Override
            public void linkToDeath(DeathRecipient recipient, int flags) throws RemoteException {

            }

            @Override
            public boolean unlinkToDeath(DeathRecipient recipient, int flags) {
                return false;
            }
        };
    }
}
