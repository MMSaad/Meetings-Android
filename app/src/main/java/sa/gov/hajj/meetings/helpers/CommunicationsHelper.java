package sa.gov.hajj.meetings.helpers;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class CommunicationsHelper {

    public static void sendEmail(Context context, String email) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        i.putExtra(Intent.EXTRA_SUBJECT, " ");
        i.putExtra(Intent.EXTRA_TEXT, " ");
        if (i.resolveActivity(context.getPackageManager()) != null)
            context.startActivity(Intent.createChooser(i, "Send mail..."));
    }

    public static void Dial(Activity context, String number) {
        String uri = "tel:" + number;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        if (intent.resolveActivity(context.getPackageManager()) != null)
            context.startActivity(intent);
    }

    public static void openUrl(String url, Context context) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (myIntent.resolveActivity(context.getPackageManager()) != null)
            context.startActivity(myIntent);
    }
}
