package sa.gov.hajj.meetings.helpers;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

public class DeviceHelper {
    public static String getIMEINumber(Context context) {
        try {
            TelephonyManager mngr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String S = mngr.getDeviceId();

            return (S != null) ? S : "CCVV-SDSD-KKFDFK";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "XCVV-SDSD-KKFDFK";
        }
        // ;
    }

    public static String getOsVersion(Context context) {
        return android.os.Build.VERSION.RELEASE;
    }

    public static String getAppVersion(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }
}
