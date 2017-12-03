package sa.gov.hajj.meetings.webServices.responses;


import com.google.gson.Gson;

import sa.gov.hajj.meetings.data.models.LoginInfo;
import sa.gov.hajj.meetings.interfaces.IResponse;


public class LoginResponse implements IResponse {
    private LoginInfo mLoginInfo;
    private boolean mSuccess = false, mNetworkSuccess = false;

    public LoginResponse() {

    }

    public LoginResponse(String response) {
        try {

            mLoginInfo = new Gson().fromJson(response, LoginInfo.class);
            if (mLoginInfo.getAccessToken() != null && mLoginInfo.getAccessToken().length() > 10)
                setSuccess(true);
            setNetworkSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            setSuccess(false);
        }
    }


    @Override
    public boolean isNetworkSuccess() {
        return mNetworkSuccess;
    }

    @Override
    public void setNetworkSuccess(boolean networkSuccess) {
        mNetworkSuccess = networkSuccess;
    }

    @Override
    public boolean isSuccess() {
        return mSuccess;
    }

    @Override
    public void setSuccess(boolean success) {
        mSuccess = success;
    }

    public LoginInfo getLoginInfo() {
        return mLoginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        mLoginInfo = loginInfo;
    }
}
