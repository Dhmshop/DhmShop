package dhm.com.dhmshop.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.IOException;

/**
 * Created by admin on 2019/4/23.
 * 图片处理工具
 */
public class BitmapUtil {

    public static Bitmap getScaleBitmap(String fileResult, int height, int width) {
        Bitmap bitmap;

        if (fileResult == null) {
            return null;
        } else {
            try {
                bitmap = getBitmap(fileResult);
                if (bitmap != null) {
                    if (width < 600) {
                        bitmap = Bitmap.createScaledBitmap(bitmap, 600, 600, true);
                    } else {
                        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);

                    }
                    return bitmap;
                }
            } catch (IOException e) {
                return null;
            }
            return null;

        }
    }


    private static Bitmap getBitmap(String path) throws IOException {
        Bitmap bitmap;
        int width = 1080;
        int height = 1920;

        BitmapFactory.Options factoryOptions = new BitmapFactory.Options();

        factoryOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, factoryOptions);

        int imageWidth = factoryOptions.outWidth;
        int imageHeight = factoryOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = 1;

        if (imageHeight > height || imageWidth > width) {

            int heightRatio = Math.round((float) imageHeight / (float) height);
            int widthRatio = Math.round((float) imageWidth / (float) width);

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.

            scaleFactor = heightRatio < widthRatio ? heightRatio : widthRatio;
            if (scaleFactor > 2) scaleFactor = 2;
        }
        factoryOptions.inJustDecodeBounds = false;
        factoryOptions.inSampleSize = scaleFactor;
        factoryOptions.inPurgeable = true;

        bitmap = BitmapFactory.decodeFile(path, factoryOptions);
        //check the rotation of the image and display it properly
        ExifInterface exif;

        exif = new ExifInterface(path);

        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
        Matrix matrix = new Matrix();
        if (orientation == 6) {
            matrix.postRotate(90);
        } else if (orientation == 3) {
            matrix.postRotate(180);
        } else if (orientation == 8) {
            matrix.postRotate(270);
        }
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bitmap;
    }

}
