package sa.gov.hajj.meetings.interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;


public interface IOnFragmentNavigateRequestListener {
    void onFragmentNavigateRequest(Fragment fragment, Bundle extras);
}
