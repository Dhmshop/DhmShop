package dhm.com.dhmshop.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * <pre>
 * json数据解析工具
 * 如果要解析的json数据根元素是一个对象，则使用{@link #jsonToBean(String, Class)}
 * 如果要解析的json数据根元素是一个集合，则使用{@link #jsonToBean(String, Type)}
 * </pre>
 */
public class JsonUtil {

	private static Context context;

	/** 用于解析json的类 */
	private static Gson GSON = new Gson();

	/**
	 * 把json字符串转换为JavaBean
	 * @param json json字符串
	 * @param beanClass JavaBean的Class
	 * @return
	 */
	public static <T> T jsonToBean(String json, Class<T> beanClass) {
		T bean = null;
		try {
			bean = GSON.fromJson(json, beanClass);
		} catch (Exception e) {
			Log.i("JsonUtil", "解析json数据时出现异常\njson = " + json, e);
		}
		return bean;
	}

	/**
	 * 把json字符串转换为JavaBean。如果json的根节点就 是一个集合，则使用此方法<p>
	 * type参数的获取方式为：TypeBean type = new TypeToken<集合泛型>(){}.getType();
	 * @param json json字符串
	 * @return type 指定要解析成的数据类型
	 */
	public static <T> T jsonToBean(String json, Type type) {
		T bean = null;
		try {
			bean = GSON.fromJson(json, type);
		} catch (Exception e) {
			Log.i("JsonUtil", "解析json数据时出现异常\njson = " + json, e);
		}
		return bean;
	}

}