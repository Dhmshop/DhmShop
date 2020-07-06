package dhm.com.dhmshop.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author wjw
 * @describe 时间转换类
 * @date 2020/4/20
 */
public class TimeUtil {
    //根据秒数转化为时分秒   00:00:00
    public static String getTime(int second) {
        if (second < 10) {
            return "00:" +"00:0" + second;
        }
        if (second < 60) {
            return "00:" +"00:" + second;
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "00:" +"0" + minute + ":0" + second;
                }
                return "00:" +"0" + minute + ":" + second;
            }
            if (second < 10) {
                return "00:" +minute + ":0" + second;
            }
            return "00:" + minute + ":" + second;
        }
        int hour = second / 3600;
        int minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return "00" + hour + ":0" + minute + ":0" + second;
                }
                return "00" + hour + ":0" + minute + ":" + second;
            }
            if (second < 10) {
                return "00" + hour + ":"+ minute + ":0" + second;
            }
            return "00" + hour + ":"+ minute + ":" + second;
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + ":"+ ":0" + minute + ":0" + second;
            }
            return hour + ":"+ ":0" + minute + ":" + second;
        }
        if (second < 10) {
            return hour + ":" + minute + ":0" + second;
        }
        return hour + ":"+ minute + ":" + second;
    }

    public static String datFormat(Date date, String pattern){
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, new Locale("zh"));
        return dateFormat.format(date);

    }

    public static Date parseServerTime(String serverTime, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = null;
        try {
            date = sdf.parse(serverTime);
        } catch (Exception e) {
            LogUtil.errorMsg(e);
        }
        return date;
    }
}
