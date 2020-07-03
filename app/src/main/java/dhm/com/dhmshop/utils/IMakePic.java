package dhm.com.dhmshop.utils;

/**
 * Created by admin .
 * </p>
 */
public interface IMakePic {

    /**
     * 拍照片
     */
    void takePic();


    /**
     * 图片压缩获取成功以后的回调接口
     */
    void onImageUpload(String url);
}
