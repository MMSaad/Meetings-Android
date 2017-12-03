package sa.gov.hajj.meetings.helpers;


import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefHelper {
    public static final String SHARED_PREFERENCE_LANGUAGE_KEY = "language";
    public static final String DASHBOARD_SETTINGS_KEY = "dashboardSettings";
    public static final String TERMS_KEY = "terms_agreement";
    public static final String PUSH_ID_KEY = "pushSent";
    public static final String INTRO_SEEN = "introSeen";
    public static final String UNITS = "units";
    public static final String SAVED_FILTER_KEY = "filter";
    public static final int UNIT_METRIC = 0;
    public static final int UNIT_US = 1;
    public static final String WEATHER = "weather";
    public static final String AZAN_NOTIFICATION_TYPE = "azanNotificationType";
    public static final String AZAN_CALCULATION_TYPE = "azanCalculationType";

    public static String getSharedString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static int getSharedInt(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    public static float getSharedFloat(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, 0);
    }

    public static boolean getSharedBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static void setSharedString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static void setSharedInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public static void setSharedFloat(Context context, String key, float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public static void setSharedBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).apply();
    }
}
