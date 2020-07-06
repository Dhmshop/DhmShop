package dhm.com.dhmshop.fromwork.network;

import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by zb on 2019/12/1.
 * 描述：参数转换
 */
public class RequestBodyUtil {
    /**
     * retrofit @part
     * 文本参数
     */
    public static RequestBody getTextBody(String body){

        return RequestBody.create(MediaType.parse("text/plain"), body);
    }

    /**
     * retrofit @part
     * 图片或文件
     * @param  param 与服务器匹配的请求参数名
     * @param path 文件路径
     */
    public static MultipartBody.Part getPartBody(String param, String path){
        if (TextUtils.isEmpty(path)){
            return MultipartBody.Part.createFormData(param, "");
        }
        File file = new File(path);
        RequestBody photoBody = RequestBody.create(MediaType.parse("multipart/form-data") ,file);
        return MultipartBody.Part.createFormData(param, file.getName(), photoBody);
    }

    /**
     * retrofit @part
     * 上传多张图片
     * @param  param 与服务器匹配的请求参数名
     * @param  imgsPath 图片路径集合
     */
    public static MultipartBody.Part[] getPartBodys(String param, List<String> imgsPath){
        List<String> pathes = new ArrayList<String>();
        pathes.clear();
        for (int i = 0; i < imgsPath.size(); i++){
            if (imgsPath.get(i).equals("添加")){
                continue;
            }else {
                pathes.add(imgsPath.get(i));
            }
        }
        if (pathes.size() <= 0){
            return null;
        }else {
            MultipartBody.Part[] multipartBodys = new MultipartBody.Part[pathes.size()];
            for (int i = 0; i < pathes.size(); i++){
                File file  = new File(pathes.get(i));
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                String name = file.getName();
                String lastType = name.substring(name.lastIndexOf("."));
                if (!lastType.equals(".jpg")){
                    name = name.substring(0, name.lastIndexOf("."))+".jpg";
                }
                multipartBodys[i] = MultipartBody.Part.createFormData(param, name, requestBody);
            }
            return multipartBodys;
        }
    }
}
