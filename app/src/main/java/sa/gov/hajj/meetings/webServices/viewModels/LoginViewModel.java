package sa.gov.hajj.meetings.webServices.viewModels;


import android.content.Context;

import sa.gov.hajj.meetings.R;
import sa.gov.hajj.meetings.interfaces.IViewModel;


public class LoginViewModel implements IViewModel {
    private String mUserName, mPassword;

    public LoginViewModel() {

    }

    public LoginViewModel(String _userName, String _password, Context context) {
        setUserName(_userName);
        setPassword(_password);
    }

    @Override
    public ValidationResult validate() {
        if (getUserName() == null || getUserName().length() < 3)
            return new ValidationResult(false, R.string.please_enter_user_name);
        if (getPassword() == null || getPassword().length() < 3)
            return new ValidationResult(false, R.string.please_enter_password);

        return new ValidationResult(true, R.string.success);
    }

    @Override
    public String serviceFormat() {
        return "username=" + getUserName() + "&password=" + getPassword() + "&grant_type=password";

    }


    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

}
