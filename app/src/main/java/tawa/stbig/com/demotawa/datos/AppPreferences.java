package tawa.stbig.com.demotawa.datos;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * Created by helbert on 16/05/15.
 */
public class AppPreferences {

    //private String namePrefernces="ftpConfig";
    private SharedPreferences sharePrefences;
    private Editor editor;

    public AppPreferences(Context context) {
        this.sharePrefences = PreferenceManager.getDefaultSharedPreferences(context);
        this.editor = sharePrefences.edit();
    }

    public String getValue(String key) {
        return sharePrefences.getString(key, "");
    }

    public int getValueInt(String key) {
        return sharePrefences.getInt(key, 0);
    }

    public boolean getValueBoolean(String key) {
        return sharePrefences.getBoolean(key, false);
    }

    public void saveValue(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void saveValueInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void saveValueBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void clearAll() {
        editor.clear();
        editor.apply();
    }

}
