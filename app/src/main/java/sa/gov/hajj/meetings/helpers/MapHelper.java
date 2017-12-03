package sa.gov.hajj.meetings.helpers;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class MapHelper {
    public static void gotoLocation(Context activity, double lat, double lng) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + lat + "," + lng));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(activity.getPackageManager()) != null)
            activity.startActivity(intent);
    }
}
