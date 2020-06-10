package dhm.com.dhmshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/1/3.
 *
 *
 */
public class SpUtils {

    private static final String CONFIG = "kdd-config";
    private static SharedPreferences sp;

    /**
     *
     * @param context :上下文
     *
     *
     *
     */
    public static void exit(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();// 保存参数
    }
    public static String getString(Context context,  String key) {
        // 获取参数
        SharedPreferences preferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        // 读密码
        String value = preferences.getString(key, null);// 参2默认值
        return value;
    }
    public static boolean getBoolean(Context context,String key,boolean defValue){
        if (sp == null) {
            sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }
    public static int getInt(Context context, String key) {
        // 获取参数
        SharedPreferences preferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        int value = preferences.getInt(key, 0);
        return value;
    }
    public static long getLong(Context context, String key) {
        // 获取参数
        SharedPreferences preferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        long value = preferences.getLong(key, -1L);
        return value;
    }
    public static void saveLong(Context context, String key, long value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        editor.commit();// 保存参数
    }

    public static void saveString(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();// 保存参数
    }
    public static void saveInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.commit();// 保存参数
    }
    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.commit();// 保存参数
    }

    public static void putStrListValue(Context context, String key, List<String> strList) {
        if (null == strList) {
            return;
        }
        // 保存之前先清理已经存在的数据，保证数据的唯一性
        removeStrList(context, key);
        int size = strList.size();
        //putIntValue(context, key + "size", size);

        saveInt(context,key +"size",size);
        for (int i = 0; i < size; i++) {
            //putStringValue(context, key + i, strList.get(i));

            saveString(context,key+i,strList.get(i));
        }
    }

    public static List<String> getStrListValue(Context context, String key) {
        List<String> strList = new ArrayList<String>();
        //int size = getIntValue(context, key + "size", 0);
        int size = getInt(context, key + "size");
        //Log.d("sp", "" + size);
        for (int i = 0; i < size; i++) {
            //strList.add(getStringValue(context, key + i, null));
            strList.add(getString(context, key + i));
        }
        return strList;
    }

    public static void removeStrList(Context context, String key) {
        int size = getInt(context, key + "size");
        if (0 == size) {
            return;
        }
        remove(context, key + "size");
        for (int i = 0; i < size; i++) {
            remove(context, key + i);
        }
    }

    public static void removeStrListItem(Context context, String key, String str) {
        int size = getInt(context, key + "size");
        if (0 == size) {
            return;
        }
        List<String> strList = getStrListValue(context, key);
        // 待删除的List<String>数据暂存
        List<String> removeList = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            if (str.equals(strList.get(i))) {
                if (i >= 0 && i < size) {
                    removeList.add(strList.get(i));
                    remove(context, key + i);
                    //putIntValue(context, key + "size", size - 1);
                    saveInt(context, key + "size", size - 1);
                }
            }
        }
        strList.removeAll(removeList);
        // 删除元素重新建立索引写入数据
        putStrListValue(context, key, strList);
    }

    public static void remove(Context context, String key) {
        SharedPreferences.Editor sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE)
                .edit();
        sp.remove(key);
        sp.commit();
    }

    /**
     * 清空所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences.Editor sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE)
                .edit();
        sp.clear();
        sp.commit();
    }


}
