package dhm.com.dhmshop.fromwork.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.util.Locale;


/**
 * @author wjw
 * @describe 多语言切换工具类
 * @date 2020/6/2
 */
public class SwitchLanguageUtil {
    //切换中文
    public final static String CHINESE_KEY="zh-cn";
    //切换老挝语言
    public final static String LAOS_KEY="lo-la";

    /**
     * 语言切换
     *
     * @param activity
     * @param language
     */
    public static void switchLanguage(Activity activity, String language) {
        if (!TextUtils.isEmpty(language)) {
            // 获得res资源对象
            Resources resources = activity.getResources();
            // 获得屏幕参数：主要是分辨率，像素等。
            DisplayMetrics metrics = resources.getDisplayMetrics();
            // 获得配置对象
            Configuration config = resources.getConfiguration();
            //区别17版本（其实在17以上版本通过 config.locale设置也是有效的，不知道为什么还要区别）
            //在这里设置需要转换成的语言，也就是选择用哪个values目录下的strings.xml文件
            startSwitchLanguage(config, language);
            resources.updateConfiguration(config, metrics);
        }
    }


    @SuppressLint("ObsoleteSdkInt")
    private static void startSwitchLanguage(Configuration config, String language) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            switch (language) {
                case CHINESE_KEY:
                    config.setLocale(Locale.CHINA);
                    break;
                case LAOS_KEY:
                    config.locale = new Locale("lo", "LA", "");
                    break;
            }

        } else {
            switch (language) {
                case CHINESE_KEY:
                    config.locale = Locale.CHINA;
                    break;
                case LAOS_KEY:
                    config.locale = new Locale("lo", "lo", "");
                    break;
            }
        }
    }
}
