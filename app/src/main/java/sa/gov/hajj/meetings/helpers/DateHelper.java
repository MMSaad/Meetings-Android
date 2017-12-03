package sa.gov.hajj.meetings.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateHelper {

    //TODO: Documentation
    public static String ToDotNetDate(Date date, Locale locale) {
        if (date == null) return null;
        SimpleDateFormat format;

        format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("AST"));
        return format.format(date);
    }

    //TODO: Documentation
    public static String formatDateTime(Date date, Locale locale) {
        if (date == null) return null;
        SimpleDateFormat format;

        format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa", locale);
        format.setTimeZone(TimeZone.getTimeZone("AST"));
        return format.format(date);
    }

    //TODO: Documentation
    public static String formatDate(Date date, Locale locale) {
        if (date == null) return null;
        SimpleDateFormat format;

        format = new SimpleDateFormat("dd-MM-yyyy", locale);
        format.setTimeZone(TimeZone.getTimeZone("AST"));
        return format.format(date);
    }

    //TODO: Documentation
    public static String formatMonth(Date date, Locale locale) {
        if (date == null) return null;
        SimpleDateFormat format;

        format = new SimpleDateFormat("MMM", locale);
        format.setTimeZone(TimeZone.getTimeZone("AST"));
        return format.format(date);
    }

    //TODO: Documentation
    public static String formatYear(Date date, Locale locale) {
        if (date == null) return null;
        SimpleDateFormat format;

        format = new SimpleDateFormat("yyyy", locale);
        format.setTimeZone(TimeZone.getTimeZone("AST"));
        return format.format(date);
    }

    //TODO: Documentation
    public static String formatDayName(Date date, Locale locale) {
        if (date == null) return null;
        SimpleDateFormat format;

        format = new SimpleDateFormat("EEE", locale);
        format.setTimeZone(TimeZone.getTimeZone("AST"));
        return format.format(date);
    }

    //TODO: Documentation
    public static String formatDay(Date date, Locale locale) {
        if (date == null) return null;
        SimpleDateFormat format;

        format = new SimpleDateFormat("dd", locale);
        format.setTimeZone(TimeZone.getTimeZone("AST"));
        return format.format(date);
    }

    //TODO: Documentation
    public static String formatJustTime(Date date, Locale locale) {
        SimpleDateFormat format = new SimpleDateFormat("kk:mm", locale);
        //format.setTimeZone(TimeZone.getTimeZone("AST"));
        return format.format(date);
    }

    //TODO: Documentation
    public static String formatTime(long date, Locale locale) {
        long seconds = date / 1000;
        long mins = seconds / 60;
        long hours = mins / 60;
        long minutes = mins - (hours * 60);
        return hours > 9 ? hours + "" : "0" + hours + ":" + (minutes > 9 ? minutes + "" : "0" + minutes);
    }

    //TODO: Documentation
    public static String getMonths(Date date1, Date date2, Locale locale) {
        Date date = new Date(date1.getTime() - date2.getTime());
        SimpleDateFormat format = new SimpleDateFormat("MM", locale);
        format.setTimeZone(TimeZone.getTimeZone("AST"));
        return format.format(date);
    }

    //TODO: Documentation
    public static String getDays(Date date1, Date date2, Locale locale) {
        Date date = new Date(date1.getTime() - date2.getTime());
        SimpleDateFormat format = new SimpleDateFormat("dd", locale);
        format.setTimeZone(TimeZone.getTimeZone("AST"));
        return format.format(date);
    }

    //TODO: Documentation
    public static String getHours(Date date1, Date date2, Locale locale) {
        Date date = new Date(date1.getTime() - date2.getTime());
        SimpleDateFormat format = new SimpleDateFormat("hh", locale);
        format.setTimeZone(TimeZone.getTimeZone("AST"));
        return format.format(date);
    }

    //TODO: Documentation
    public Date getNewerDate(Date... dates) {
        if (dates.length == 0)
            return null;
        Date date = dates[0];
        for (Date d : dates) {
            if (d.getTime() > date.getTime())
                date = d;
        }
        return date;
    }

    /**
     * Add 1 Millisecond to date
     *
     * @param date the target date value
     * @return the date param with 1 millisecond
     */
    public Date getDateWithExtraMillisecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date.getTime() + 1);
        return c.getTime();
    }
}
