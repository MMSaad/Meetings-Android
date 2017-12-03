package sa.gov.hajj.meetings;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import sa.gov.hajj.meetings.data.models.LoginInfo;
import sa.gov.hajj.meetings.helpers.LanguageHelper;

public class SplashActivity extends AppCompatActivity {

    /***
     * Views
     */
    @BindView(R.id.ivSplash) ImageView ivSplash;

    /***
     * Vars
     */
    boolean isUserLogged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new LanguageHelper().initLanguage(this, true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        new GetLoginStatusAsync(this).execute();
        startAnimation();
    }

    /**
     * Animate splash image
     */
    private void startAnimation() {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fader);
        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {

            }

            @Override public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, (isUserLogged) ? MainActivity.class : LoginActivity.class));
                finish();
            }

            @Override public void onAnimationRepeat(Animation animation) {

            }
        });
        ivSplash.startAnimation(fadeInAnimation);
    }

    /**
     * Async task to get login status outside UI Thread
     */
    private static class GetLoginStatusAsync extends AsyncTask<Void, Void, Void> {
        private WeakReference<SplashActivity> mActivityWeakReference;

        GetLoginStatusAsync(SplashActivity context) {
            mActivityWeakReference = new WeakReference<>(context);
        }

        @Override protected Void doInBackground(Void... voids) {
            Realm realm = Realm.getDefaultInstance();
            if (mActivityWeakReference != null) {
                mActivityWeakReference.get().isUserLogged = realm.where(LoginInfo.class).equalTo(Constants.FIELD_ID, 1).count() > 0;
            }
            realm.close();
            return null;
        }
    }
}
