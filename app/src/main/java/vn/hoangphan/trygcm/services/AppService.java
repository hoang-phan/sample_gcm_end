package vn.hoangphan.trygcm.services;

import android.content.Context;
import android.content.SharedPreferences;

import vn.hoangphan.trygcm.constants.Constants;

/**
 * Created by Hoang Phan on 9/12/2015.
 */
public class AppService {
    private static AppService instance = new AppService();

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static AppService getInstance() {
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
        setPreferences(Constants.SHARED_PREFERENCES_TAG);
    }

    public void setPreferences(String tag) {
        preferences = context.getSharedPreferences(tag, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setConfig(String key, String value) {
        editor.putString(key, value);
    }

    public void setConfig(String key, long value) {
        editor.putLong(key, value);
    }

    public void setConfig(String key, double value) {
        setConfig(key, Double.doubleToRawLongBits(value));
    }

    public void saveConfig(String key, long value) {
        setConfig(key, value);
        editor.commit();
    }

    public void saveConfig(String key, String value) {
        setConfig(key, value);
        editor.commit();
    }

    public void saveConfig(String key, double value) {
        setConfig(key, value);
        editor.commit();
    }

    public String getConfigString(String key) {
        return preferences.getString(key, "");
    }

    public long getConfigLong(String key) {
        return preferences.getLong(key, 0);
    }

    public double getConfigDouble(String key) {
        return Double.longBitsToDouble(getConfigLong(key));
    }

    public void reset() {
        editor.clear().commit();
    }
}
