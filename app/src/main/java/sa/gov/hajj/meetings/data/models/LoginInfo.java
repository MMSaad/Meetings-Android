package sa.gov.hajj.meetings.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import sa.gov.hajj.meetings.Constants;


/**
 * Login Info database model details
 * Received from successful login invoke
 */
public class LoginInfo extends RealmObject {


    /**
     * Primary Key
     */
    @PrimaryKey
    private int Id;

    /***
     * Login User Name
     * Used for silent login on access token expire
     */
    @Expose
    @SerializedName(Constants.USER_NAME_FIELD)
    private String UserName;

    /**
     * Login Password
     * Used for silent login on access token expire
     */
    @Expose
    @SerializedName(Constants.PASSWORD_FIELD)
    private String Password;

    /**
     * Current access token
     */
    @Expose
    @SerializedName(Constants.ACCESS_TOKEN_FIELD)
    private String AccessToken;

    /**
     * Access token validate length on login
     */
    @Expose
    @SerializedName(Constants.ACCESS_TOKEN_EXPIRES_IN_FIELD)
    private long AccessTokenExpire;

    /**
     * Login Date Operation
     */
    private Date LoginDate;

    /**
     * User English Display Name - Friendly name for UI Render
     */
    @Expose
    @SerializedName(Constants.DISPLAY_ENGLISH_NAME_FIELD)
    private String DisplayEnglishName;

    /**
     * User Arabic Display Name - Friendly name for UI Render
     */
    @Expose
    @SerializedName(Constants.DISPLAY_ARABIC_NAME_FIELD)
    private String DisplayArabicName;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public long getAccessTokenExpire() {
        return AccessTokenExpire;
    }

    public void setAccessTokenExpire(long accessTokenExpire) {
        AccessTokenExpire = accessTokenExpire;
    }

    public Date getLoginDate() {
        return LoginDate;
    }

    public void setLoginDate(Date loginDate) {
        LoginDate = loginDate;
    }

    public String getDisplayEnglishName() {
        return DisplayEnglishName;
    }

    public void setDisplayEnglishName(String displayEnglishName) {
        DisplayEnglishName = displayEnglishName;
    }

    public String getDisplayArabicName() {
        return DisplayArabicName;
    }

    public void setDisplayArabicName(String displayArabicName) {
        DisplayArabicName = displayArabicName;
    }
}
