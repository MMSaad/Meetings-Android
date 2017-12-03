package sa.gov.hajj.meetings.interfaces;


public interface IResponse {


    boolean isSuccess();

    void setSuccess(boolean success);

    boolean isNetworkSuccess();

    void setNetworkSuccess(boolean networkSuccess);
}
