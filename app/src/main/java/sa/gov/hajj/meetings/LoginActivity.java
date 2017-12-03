package sa.gov.hajj.meetings;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import sa.gov.hajj.meetings.activities.ForgetPasswordActivity;
import sa.gov.hajj.meetings.data.models.LoginInfo;
import sa.gov.hajj.meetings.fragments.dialogs.LanguageSelectorSheet;
import sa.gov.hajj.meetings.helpers.CommunicationsHelper;
import sa.gov.hajj.meetings.helpers.DialogsHelper;
import sa.gov.hajj.meetings.helpers.KeyboardHelper;
import sa.gov.hajj.meetings.helpers.LanguageHelper;
import sa.gov.hajj.meetings.helpers.ServiceCallsHelper;
import sa.gov.hajj.meetings.interfaces.ILanguageChangeListener;
import sa.gov.hajj.meetings.webServices.requests.LoginRequest;
import sa.gov.hajj.meetings.webServices.responses.LoginResponse;
import sa.gov.hajj.meetings.webServices.responses.ServiceNetworkResult;
import sa.gov.hajj.meetings.webServices.viewModels.LoginViewModel;
import sa.gov.hajj.meetings.webServices.viewModels.ValidationResult;


public class LoginActivity extends AppCompatActivity implements ILanguageChangeListener {



    /**
     * Vars
     */
    public LoginViewModel mModel = new LoginViewModel();

    /**
     * Views
     */
    @BindView(R.id.etUserName) EditText etUserName;
    @BindView(R.id.etPassword) EditText etPassword;
    @BindView(R.id.imgLang) ImageView imgLang;
    @BindView(R.id.v_loading) View v_loading;
    @BindView(R.id.v_data) View v_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new LanguageHelper().initLanguage(this, true);
        super.onCreate(savedInstanceState);
        LanguageHelper helper = new LanguageHelper();
        int LanguageIndex = helper.getCurrentLanguageIndex(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Drawable currentLanguageFLag = ContextCompat.getDrawable(this, R.drawable.ic_usa_flag);
        if (LanguageIndex == LanguageHelper.LANGUAGE_ARABIC_INDEX)
            currentLanguageFLag = ContextCompat.getDrawable(this, R.drawable.ic_sa_flag);
        imgLang.setImageDrawable(currentLanguageFLag);

    }


    @OnClick(R.id.imgGamaLogo) void gamaLogoPressed(View v) {
        CommunicationsHelper.openUrl(getString(R.string.url_gama), this);
    }

    @OnClick(R.id.btnForgetPassword) void forgetPasswordButtonPressed(View v) {
        startActivity(new Intent(this, ForgetPasswordActivity.class));
    }

    @OnClick(R.id.btnLogin) void loginButtonPressed(View v) {
        this.bindViewModel();
    }

    @OnClick(R.id.imgLang) void languageButtonPressed(View v) {
        LanguageSelectorSheet bottomSheetDialogFragment = LanguageSelectorSheet.newInstance(new LanguageHelper().getCurrentLanguageIndex(this));
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
    }


    private void bindViewModel() {
        this.mModel.setUserName(this.etUserName.getText().toString());
        this.mModel.setPassword(this.etPassword.getText().toString());
        ValidationResult validationResult = this.mModel.validate();
        if (!validationResult.isSuccess()) {
            DialogsHelper.getAlert(this, getString(R.string.error), getString(validationResult.getMessage())).show();
            return;
        }
        new LoginAsync(this).execute(this.mModel);
    }

    @Override public void LanguageChanged() {
        recreate();
    }

    private void switchView(int view) {
        v_data.setVisibility(view == Constants.LOGIN_VIEW ? View.VISIBLE : View.INVISIBLE);
        v_loading.setVisibility(view == Constants.LOGIN_VIEW ? View.INVISIBLE : View.VISIBLE);
    }

    private static class LoginAsync extends AsyncTask<LoginViewModel, Integer, LoginResponse> {

        private WeakReference<LoginActivity> mActivityWeakReference;

        LoginAsync(LoginActivity activity) {
            mActivityWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mActivityWeakReference != null) {
                new KeyboardHelper().Hide(mActivityWeakReference.get());
                mActivityWeakReference.get().switchView(Constants.LOADING_VIEW);
            }
        }

        @Override
        protected void onPostExecute(final LoginResponse result) {
            if (mActivityWeakReference != null) {
                mActivityWeakReference.get().switchView(Constants.LOGIN_VIEW);
                if (!result.isNetworkSuccess()) {
                    DialogsHelper.showToast(mActivityWeakReference.get(), mActivityWeakReference.get().getString(R.string.service_error));
                    return;
                }

                if (!result.isSuccess()) {
                    DialogsHelper.showToast(mActivityWeakReference.get(), mActivityWeakReference.get().getString(R.string.login_fail));
                    return;
                }

                result.getLoginInfo().setLoginDate(new Date());
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        LoginInfo loginInfo = realm.createObject(LoginInfo.class, 1);
                        loginInfo.setAccessToken(result.getLoginInfo().getAccessToken());
                        loginInfo.setPassword(mActivityWeakReference.get().mModel.getPassword());
                        loginInfo.setUserName(mActivityWeakReference.get().mModel.getUserName());
                        loginInfo.setLoginDate(new Date());
                    }
                });
                mActivityWeakReference.get().startActivity(new Intent(mActivityWeakReference.get(), MainActivity.class));
                mActivityWeakReference.get().finish();
            }
        }

        @Override
        protected LoginResponse doInBackground(LoginViewModel... params) {
            try {
                if (mActivityWeakReference != null) {
                    ServiceNetworkResult result = new ServiceCallsHelper().DoPost(new LoginRequest(params[0]), true, mActivityWeakReference.get());
                    return result.isSuccess() ? new LoginResponse(result.getResult()) : new LoginResponse();
                }
            } catch (Exception e) {
                e.printStackTrace();
                //TODO: FIRE BASE ANALYTICS
            }
            return null;
        }
    }


}
