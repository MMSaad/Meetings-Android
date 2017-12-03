package sa.gov.hajj.meetings.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by mustafa on 5/23/16.
 * Release the GEEK
 */
public class IntentHelper {
    public static void openUrl(String url, Context context) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (myIntent.resolveActivity(context.getPackageManager()) != null)
            context.startActivity(myIntent);
    }
}
