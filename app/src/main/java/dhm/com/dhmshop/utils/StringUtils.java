package dhm.com.dhmshop.utils;

import android.annotation.SuppressLint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import dhm.com.dhmshop.base.netWork.Constant;

import static java.util.regex.Pattern.*;

/**
 * Created by mwqi on 2014/6/8.
 */
public class StringUtils {
    public final static String UTF_8 = "UTF-8";

    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证手机号码
     *
     * @param mobiles
     * @return [0-9]{5,9}
     */
    public static boolean isMobileNO(String mobiles) {
        try {
            Pattern p = compile("/^[1][3,4,5,7,8][0-9]{9}$/");
            Matcher m = p.matcher(mobiles);
            return m.matches();
        } catch (Exception e) {
        }
        return false;
    }


    public static boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return !m.matches();
    }

    /**
     * 正则验证手机号
     */
    public static boolean isRegistMobileNO(String phone) {
        @SuppressLint("WrongConstant")
        Pattern patternRegistMobile = compile(Constant.REGEX_MOBILE, CASE_INSENSITIVE);
        Matcher matcherRegistMobile = patternRegistMobile.matcher(phone);
        return matcherRegistMobile.matches();
    }



    /**
     * 正则验证只能是中文
     */
    public static boolean isRegistchinse(String phone) {
        @SuppressLint("WrongConstant")
        Pattern patternRegistMobile = compile(Constant.CHINESE, CASE_INSENSITIVE);
        Matcher matcherRegistMobile = patternRegistMobile.matcher(phone);
        return !matcherRegistMobile.matches();
    }

    /**
     * 正则验证邮箱
     */
    public static boolean isRegistemail(String email) {
        @SuppressLint("WrongConstant")
        Pattern patternRegistMobile = compile(Constant.REGEX_EMAIL, CASE_INSENSITIVE);
        Matcher matcherRegistMobile = patternRegistMobile.matcher(email);
        return !matcherRegistMobile.matches();
    }



    /**
     * 正则验证密码
     */
    public static boolean isRegistMobileNO1(String pwd) {
        @SuppressLint("WrongConstant")
        Pattern patternRegistMobile = compile(Constant.reg, CASE_INSENSITIVE);
        Matcher matcherRegistMobile = patternRegistMobile.matcher(pwd);
        return !matcherRegistMobile.matches();
    }


    /**
     * 正则验证公司名称
     */
    public static boolean isRegistCOMPANY(String company) {
        @SuppressLint("WrongConstant")
        Pattern patternRegistMobile = compile(Constant.reg, CASE_INSENSITIVE);
        Matcher matcherRegistMobile = patternRegistMobile.matcher(company);
        return !matcherRegistMobile.matches();
    }




}
