package sa.gov.hajj.meetings.webServices.responses;


public class ServiceNetworkResult {

    /***
     * Vars
     */
    private boolean mSuccess = false;

    private String mResult = "";

    public ServiceNetworkResult() {

    }

    public ServiceNetworkResult(String result) {
        this.setSuccess(true);
        this.setResult(result);
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public void setSuccess(boolean success) {
        mSuccess = success;
    }
}
