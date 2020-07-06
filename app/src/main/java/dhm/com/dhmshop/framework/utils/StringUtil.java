package dhm.com.dhmshop.fromwork.utils;

import android.text.Editable;

import java.text.DecimalFormat;

/**
 * @describe String工具类
 * @date 2020/4/2
 * @author wjw
 */
public class StringUtil {
    /**
     * 字符串拼接
     * @param obj
     * @return
     */
    public static String buildString(Object...obj){
        StringBuffer sb = new StringBuffer();
        for (Object str : obj){
            sb.append(str);
        }
        return sb.toString().trim();
    }

    /**
     * @describe 判断字符串是否为null 或者empty
     * @param str
     * @return 返回 true or false
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
    /**
     * @describe 判断字符串是否为null 或者empty
     * @param str
     * @return 返回 true or false
     */
    public static String preventNull(String str) {
        if (str == null){
            return "";
        }
        return str.trim();
    }
    /**
     * @describe 判断Editable是否为null 或者empty
     * @param str
     * @return 返回 true or false
     */
    public static String editableIsNullToString(Editable str) {
        if (str == null){
            return "";
        }
        return str.toString().trim();
    }
    public static boolean editableIsNull(Editable str) {
        if (str == null){
            return true;
        }
        return isNullOrEmpty(str.toString().trim());
    }

    public static String decimalFormat(Object number){
        return new DecimalFormat(",###").format(number);
    }
}
