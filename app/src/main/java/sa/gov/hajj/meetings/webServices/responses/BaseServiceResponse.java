package sa.gov.hajj.meetings.webServices.responses;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseServiceResponse {

    @Expose
    @SerializedName("Success")
    private boolean mSuccess;

    public boolean isSuccess() {
        return mSuccess;
    }
}
