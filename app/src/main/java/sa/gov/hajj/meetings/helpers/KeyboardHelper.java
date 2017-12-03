package sa.gov.hajj.meetings.helpers;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

public class KeyboardHelper {
    public void Hide(Activity activity) {
        try {
            InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (activity.getWindow() != null && activity.getWindow().getCurrentFocus() != null && activity.getWindow().getCurrentFocus().getWindowToken() != null) {
                IBinder binder = activity.getWindow().getCurrentFocus().getWindowToken();
                if (manager != null)
                    manager.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
