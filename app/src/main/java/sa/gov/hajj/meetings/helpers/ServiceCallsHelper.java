package sa.gov.hajj.meetings.helpers;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import sa.gov.hajj.meetings.data.models.LoginInfo;
import sa.gov.hajj.meetings.webServices.requests.BaseRequest;
import sa.gov.hajj.meetings.webServices.requests.LoginRequest;
import sa.gov.hajj.meetings.webServices.responses.ServiceNetworkResult;
import sa.gov.hajj.meetings.webServices.viewModels.LoginViewModel;

public class ServiceCallsHelper {

    /**
     * FLAGS
     */
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private final String TAG = "ServiceCallsHelper";

    /**
     * Perform Web api call
     *
     * @param req     Request details
     * @param login   does this request for login or not
     * @param context Context to grape strings values
     * @return Web App Call result
     */
    public ServiceNetworkResult DoPost(BaseRequest req, boolean login, Context context) {
        try {
            Log.v(TAG, context.getString(req.getServiceUrl()));
            Log.v(TAG, req.getData());
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
            RequestBody body = RequestBody.create(JSON, req.getData());
            Request.Builder requestBuilder = new Request.Builder()
                    .url(context.getString(req.getServiceUrl()))
                    .post(body);
            if (!login)
                requestBuilder.addHeader("Authorization", "Bearer " + req.getAccessToken());
            final Response response = client.newCall(requestBuilder.build()).execute();
            Log.wtf(TAG, response.code() + "");
            if (response.code() == 401 && login) {
                //Update Access token
                Realm realm = Realm.getDefaultInstance();
                LoginInfo loginInfo = realm.where(LoginInfo.class).findFirst();
                if (loginInfo != null) {
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setModel(new LoginViewModel(loginInfo.getUserName(), loginInfo.getPassword(), context));
                    final ServiceNetworkResult result = DoPost(loginRequest, true, context);
                    //Recall the method
                    if (result.isSuccess()) {
                        realm.beginTransaction();
                        loginInfo = new Gson().fromJson(result.getResult(), LoginInfo.class);
                        realm.commitTransaction();
                        req.setAccessToken(loginInfo.getAccessToken());
                        return DoPost(req, false, context);
                    }
                }
            }
            String result = response.body().string();
            Log.v(TAG, result);
            return new ServiceNetworkResult(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ServiceNetworkResult();
        }
    }


}
