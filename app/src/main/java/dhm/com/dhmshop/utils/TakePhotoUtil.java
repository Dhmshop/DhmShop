package dhm.com.dhmshop.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.base.netWork.Constant;

/**
 * Created by admin on 2019/4/23.
 * </p>
 */
public class TakePhotoUtil {

    private Activity mContext;
    private File file;
    private String fileResult;//照片的url
    private Uri fileUri;//照片的uri
    private IMakePic iMakePic;
    private int mRequestCode;
    private String pathUrl = Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/Ledian/Image/Advicegoods/";
    private int position = 1;
    private List<String> options = new ArrayList<>();


    /**
     * 图片压缩获取成功以后的回调接口
     */

    public TakePhotoUtil(int requestCode, Context context, IMakePic imakePic) {
        this.mContext = (Activity) context;
        this.iMakePic = imakePic;
        this.mRequestCode = requestCode;

        options.clear();
        options.add("拍照");
        options.add("相册");
    }


    //    开启相机
    public void doTakePhoto() {
        String SDState = Environment.getExternalStorageState();
        if (SDState != null && SDState.equals(Environment.MEDIA_MOUNTED)) {
            OptionsPickerView pickerView = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    switch (options1) {
                        case 0:
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (Build.VERSION.SDK_INT >= 24) {
                                file = new File(mContext.getExternalCacheDir(), "advcieimg" + ++position + ".jpg");
                                FileUtils.createFile(file);
                                fileUri = FileProvider.getUriForFile(mContext, Constant.Tag, file);
                                fileResult = file.getAbsolutePath();
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            } else {
                                file = new File(pathUrl, "advcieimg" + ++position + ".jpg");
                                FileUtils.createFile(file);
                                fileUri = Uri.fromFile(file);
                                fileResult = fileUri.getPath();
                            }

                            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                            mContext.startActivityForResult(intent, mRequestCode);
                            break;
                        case 1:
                            //相册
                            //相册
                            Intent albumIntent = new Intent(Intent.ACTION_PICK);
                            albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                            mContext.startActivityForResult(albumIntent, Constant.IMAGE_REQUEST_CODE);
                            break;
                    }
                }
            }).build();
            pickerView.setPicker(options);
            pickerView.show();

        } else {
            Toast.makeText(mContext, "内存卡不存在!", Toast.LENGTH_SHORT).show();

        }

    }


    //压缩图片
    public void luBanCompress(int height,int width) {
        Bitmap bitmap;
        bitmap = BitmapUtil.getScaleBitmap(fileResult,height,width);

        if (bitmap != null && iMakePic != null) {
            //生成水印
            iMakePic.onImageUpload(saveTodisk(bitmap, pathUrl));
        }

    }

    /**
     * @param bitmap t图片
     * @param path   保存目录
     * @return
     */
    public static String saveTodisk(Bitmap bitmap, String path) {
        try {
            File pictureFile = new File(path, DateUtil.getImageDate() + ".jpg");
            FileUtils.createFile(pictureFile);
            //压缩图片
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] bytes = bos.toByteArray();
            //将图片封装成File对象
            FileOutputStream outputStream = new FileOutputStream(pictureFile);

            outputStream.write(bytes);
            outputStream.close();
            bos.close();
            return pictureFile.getAbsolutePath();
        } catch (Exception e) {
            return null;
        }
    }

}
