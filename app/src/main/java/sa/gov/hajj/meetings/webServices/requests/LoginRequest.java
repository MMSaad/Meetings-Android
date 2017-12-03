package sa.gov.hajj.meetings.webServices.requests;


import sa.gov.hajj.meetings.R;
import sa.gov.hajj.meetings.webServices.viewModels.LoginViewModel;

public class LoginRequest extends BaseRequest {
    private LoginViewModel mModel;

    public LoginRequest() {
        setModel(new LoginViewModel());
    }

    public LoginRequest(LoginViewModel model) {
        setModel(model);
    }

    @Override
    public int getServiceUrl() {
        return R.string.url_login;
    }

    @Override
    public String getData() {
        return getModel().serviceFormat();
    }

    public LoginViewModel getModel() {
        return mModel;
    }

    public void setModel(LoginViewModel model) {
        mModel = model;
    }
}
