package sa.gov.hajj.meetings.webServices.requests;


import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import sa.gov.hajj.meetings.Constants;
import sa.gov.hajj.meetings.R;


/**
 * Request to Sync App Date
 */
public class UpdateDataRequest extends BaseRequest {

    /**
     * Newest Last update date field in all app local data
     */
    @Expose
    @SerializedName(Constants.FIELD_LAST_UPDATE_DATE)
    private String LastUpdateDate;


    public UpdateDataRequest(String lastUpdateDate, String accessToken) {
        setLastUpdateDate(lastUpdateDate);
        setAccessToken(accessToken);
    }

    @Override
    public int getServiceUrl() {
        return R.string.url_update_data;
    }

    @Override
    public String getData() {
        return new Gson().toJson(this);
    }

    private void setLastUpdateDate(String lastUpdateDate) {
        LastUpdateDate = lastUpdateDate;
    }
}
