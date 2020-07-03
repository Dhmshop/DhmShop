package dhm.com.dhmshop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    private static SimpleDateFormat sf = null;
    /* 获取系统时间 格式为："yyyy/MM/dd " */
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }
    /* 时间戳转换成字符窜 */
    public static String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /* 将字符串转为时间戳 */
    public static String getStringToDate(String time) {
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime()+"";
    }


//    public static void main(String[] args) {
//        String TIME = TimeUtil.getDateToString(1445875200000L);
//        System.out.println("TIME==" + TIME);
//
//
//        String CURRENTTIME = TimeUtil.getCurrentDate();
//        System.out.println("CURRENTtime" + CURRENTTIME);
//        long CHUO = TimeUtil.getStringToDate(CURRENTTIME);
//        System.out.println(CHUO);
//
//
//    }
}
