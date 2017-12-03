package sa.gov.hajj.meetings;

import java.util.ArrayList;
import java.util.Arrays;

import sa.gov.hajj.meetings.viewModels.LanguageViewModel;


/**
 * Created by mustafa on 11/6/17.
 * Release the GEEK
 */

public class Constants {

    /***
     * Constants Data
     */
    public static final ArrayList<LanguageViewModel> APP_LANGUAGES = new ArrayList<LanguageViewModel>(
            Arrays.asList(
                    new LanguageViewModel(R.string.english, R.drawable.ic_usa_flag),
                    new LanguageViewModel(R.string.arabic, R.drawable.ic_sa_flag)
            ));


    /*******************************************************************************************************
     *****************************                  Database Fields                       *******************
     ********************************************************************************************************/
    public static final String FIELD_ID = "Id";
    public static final String FIELD_NAME = "Name";
    public static final String FIELD_DESCRIPTION = "Description";
    public static final String FIELD_LAST_UPDATE_DATE = "LastUpdateDate";
    public static final String FIELD_START_DATE = "StartDate";
    public static final String FIELD_OWNER = "Owner";
    public static final String FIELD_END_DATE = "EndDate";
    public static final String FIELD_IS_OPEN = "IsOpen";
    public static final String FIELD_CLOSED_DATE = "ClosedDate";
    public static final String USER_NAME_FIELD = "userName";
    public static final String PASSWORD_FIELD = "Password";
    public static final String ACCESS_TOKEN_FIELD = "access_token";
    public static final String ACCESS_TOKEN_EXPIRE_FIELD = "accessTokenExpire";
    public static final String DISPLAY_ENGLISH_NAME_FIELD = "DisplayEnglishName";
    public static final String DISPLAY_ARABIC_NAME_FIELD = "DisplayArabicName";
    public static final String ACCESS_TOKEN_EXPIRES_IN_FIELD = "expires_in";
    public static final String ACTIONS_NAVIGATION_PROPERTY_FIELD = "Actions";
    public static final String TASK_ID_FIELD = "TaskId";
    public static final String CHECK_IN_FIELD = "CheckIn";
    public static final String CHECK_OUT_FIELD = "CheckOut";
    public static final String TASKS_NAVIGATION_PROPERTY = "Tasks";
    public static final String SERVICE_ID_FIELD = "ServiceId";
    public static final String MAIN_ASSIGNEE_FIELD = "MainAssignee";
    public static final String ASSIGNEE_DELEGATES_FIELD = "AssigneeDelegates";
    public static final String CLOSED_BY_FIELD = "ClosedBy";
    public static final String OWNERS_FIELD = "Owners";
    public static final String PROJECT_ID_FIELD = "ProjectId";
    public static final String SERVICES_NAVIGATION_PROPERTY = "Services";
    public static final String TITLE_FIELD = "Title";
    public static final String BODY_FIELD = "Body";
    public static final String DATE_FIELD = "Date";
    public static final String IS_READ_FIELD = "IsRead";


    /*******************************************************************************************************
     *****************************                  Filters Flags                       *******************
     ********************************************************************************************************/
    public static final int FILTER_OPEN = 0;
    public static final int FILTER_CLOSED = 1;
    public static final int FILTER_ALL = 2;
    public static final int PROJECTS_FILTER_FLAG = 777;



    /**
     * Views Flags
     */
    public final static int LOGIN_VIEW = 1, LOADING_VIEW = 2, NO_DATA_VIEW = 3;
}
