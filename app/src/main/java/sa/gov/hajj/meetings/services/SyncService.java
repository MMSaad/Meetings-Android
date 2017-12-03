package sa.gov.hajj.meetings.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import sa.gov.hajj.meetings.Constants;
import sa.gov.hajj.meetings.R;
import sa.gov.hajj.meetings.data.models.LoginInfo;
import sa.gov.hajj.meetings.helpers.DateDeserializer;
import sa.gov.hajj.meetings.helpers.DateHelper;
import sa.gov.hajj.meetings.helpers.ServiceCallsHelper;
import sa.gov.hajj.meetings.webServices.requests.UpdateDataRequest;
import sa.gov.hajj.meetings.webServices.responses.ServiceNetworkResult;
import sa.gov.hajj.meetings.webServices.responses.UpdateDataResponse;


/**
 * Created by mustafa on 11/16/17.
 * Release the GEEK
 */

public class SyncService extends IntentService {

    /**
     * FLags
     */
    private static final String TAG = "SyncService";

    /**
     * Vars
     */
    public static boolean isWorking = false;

    public SyncService() {
        super("SyncService");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        isWorking = true;
        SyncAppDate();
        isWorking = false;
    }

    /**
     * Call Web Api Sync method and sync app data stored in local database
     */
    private void SyncAppDate() {
        Realm realm = Realm.getDefaultInstance();

        LoginInfo loginInfo = realm.where(LoginInfo.class).equalTo(Constants.FIELD_ID, 1).findFirst();

        Date lastUpdateDate = new Date();

        //TODO: Move data queries to repos
//     repos   RealmResults<Notification> notifications = realm.where(Notification.class).findAllSorted(Constants.DATE_FIELD, Sort.DESCENDING);
//        if (notifications.size() > 0 && notifications.first().getDate().getTime() > lastUpdateDate.getTime())
//            lastUpdateDate = (notifications.first()).getDate();

        //TODO: Move data queries to repos
//        RealmResults<Project> projects = realm.where(Project.class).findAllSorted(Constants.FIELD_LAST_UPDATE_DATE, Sort.DESCENDING);
//        if (projects.size() > 0 && projects.first().getLastUpdateDate().getTime() > lastUpdateDate.getTime())
//            lastUpdateDate = (projects.first()).getLastUpdateDate();

        //TODO: Move data queries to repos
//        RealmResults<Service> services = realm.where(Service.class).findAllSorted(Constants.FIELD_LAST_UPDATE_DATE, Sort.DESCENDING);
//        if (services.size() > 0 && services.first().getLastUpdateDate().getTime() > lastUpdateDate.getTime())
//            lastUpdateDate = (services.first()).getLastUpdateDate();

        //TODO: Move data queries to repos
//        RealmResults<Task> tasks = realm.where(Task.class).findAllSorted(Constants.FIELD_LAST_UPDATE_DATE, Sort.DESCENDING);
//        if (tasks.size() > 0 && tasks.first().getLastUpdateDate().getTime() > lastUpdateDate.getTime())
//            lastUpdateDate = (tasks.first()).getLastUpdateDate();

        //TODO: Move data queries to repos
//        RealmResults<TaskAction> taskActions = realm.where(TaskAction.class).findAllSorted(Constants.FIELD_LAST_UPDATE_DATE, Sort.DESCENDING);
//        if (taskActions.size() > 0 && taskActions.first().getLastUpdateDate().getTime() > lastUpdateDate.getTime())
//            lastUpdateDate = (taskActions.first()).getLastUpdateDate();

        ServiceNetworkResult result = new ServiceCallsHelper().DoPost(new UpdateDataRequest(DateHelper.ToDotNetDate(new DateHelper().getDateWithExtraMillisecond(lastUpdateDate), Locale.UK), loginInfo.getAccessToken()), false, this);

        if (result.isSuccess()) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            UpdateDataResponse response = gsonBuilder.create().fromJson(result.getResult(), UpdateDataResponse.class);


            realm.beginTransaction();

            //TODO: Move data queries to repos
//            if (response.getNotifications() != null) {
//                for (Notification n : response.getNotifications()) {
//                    realm.copyToRealmOrUpdate(n);
////                    showNotification(n.getId(), n, String.format(locale, "%s %s", getString(R.string.new_notification), n.getTitle()), n.getBody(), "NOTIFICATION", NotificationDetailActivity.class, Constants.NOTIFICATION_ITEM);
//                }
//                BroadcastHelper.sendInform(this, BroadcastHelper.NEW_NOTIFICATION);
//            }

            //TODO: Move data queries to repos
//            if (response.getProjects() != null) {
//                for (Project i : response.getProjects())
//                    realm.copyToRealmOrUpdate(i);
////                BroadcastHelper.sendInform(this, BroadcastHelper.NEW_SOS);
//            }

            realm.commitTransaction();
        }
    }

    //TODO: Move it to notification helper
    public void showNotification(int id, Serializable object, String title, String body, String tag, Class<?> cls, String extraTag) {
        try {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(title)
                            .setContentText(body);
//            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            mBuilder.setSound(alarmSound);
            Intent resultIntent = new Intent(this, cls);
            resultIntent.putExtra(extraTag, object);
            resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            android.app.Notification no = mBuilder.build();
            no.flags |= android.app.Notification.FLAG_AUTO_CANCEL;
            mNotifyMgr.notify(tag, id, no);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}