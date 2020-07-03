package dhm.com.dhmshop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by admin on 2019/4/23.
 * </p>
 */
public class DateUtil {
    private static SimpleDateFormat imageSdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
    private static  SimpleDateFormat waterSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);


    public static String getImageDate(){
        return imageSdf.format(new Date());
    }

    public static String getWaterDate(){
        return waterSdf.format(new Date());
    }
}
