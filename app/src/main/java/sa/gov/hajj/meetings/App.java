package sa.gov.hajj.meetings;

import io.realm.Realm;

/**
 * Created by mustafa on 11/6/17.
 * Release the GEEK
 */

public class App extends android.support.multidex.MultiDexApplication {

    @Override public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

}
