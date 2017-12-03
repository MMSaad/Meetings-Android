package sa.gov.hajj.meetings.helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import sa.gov.hajj.meetings.R;
import sa.gov.hajj.meetings.interfaces.ISinglePickDialogDelegate;


public class DialogsHelper {

    //TODO:DOCUMENTATION
    public static AlertDialog getAlert(Activity activity, String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setTitle(title).setMessage(message);
        dialog.setPositiveButton(activity.getString(R.string.ok), null);
        return dialog.create();
    }

    //TODO:DOCUMENTATION
    public static AlertDialog getAlert(Activity activity, String title, String message, String okButton) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setTitle(title).setMessage(message);
        dialog.setPositiveButton(okButton, null);
        return dialog.create();
    }

    //TODO:DOCUMENTATION
    public static AlertDialog getAlert(Activity activity, final IItemDialogClickListener listener, final int id, String title, String message, String okButton) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setTitle(title).setMessage(message);
        dialog.setPositiveButton(activity.getString(R.string.ok), null);
        dialog.setNegativeButton(okButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null)
                    listener.onClick(id);
            }
        });
        return dialog.create();
    }

    //TODO:DOCUMENTATION
    public static ProgressDialog getProgressDialog(Activity activity, String title) {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setMessage(title);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    //TODO:DOCUMENTATION
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    //TODO:DOCUMENTATION
    public static AlertDialog getSingleChoiceDialog(Activity activity, String title, final ISinglePickDialogDelegate mDelegate, int sourceArray, int selectedItemPosition, final int code) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title)
                .setSingleChoiceItems(sourceArray, selectedItemPosition, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDelegate.Picked(code, which);
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}
