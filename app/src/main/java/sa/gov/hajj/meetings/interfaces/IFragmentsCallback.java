package sa.gov.hajj.meetings.interfaces;

import android.support.v4.app.Fragment;

/**
 * Created by mustafa on 11/28/17.
 * Release the GEEK
 */

public interface IFragmentsCallback {
    void navigateToFragment(Fragment fragment, boolean addToBackStack);
}
