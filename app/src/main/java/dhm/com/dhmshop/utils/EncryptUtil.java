package dhm.com.dhmshop.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

/**
 * Created by xyj on 2017/6/29.
 */
public class EncryptUtil {

    private static final String KEY = "12baweiyidong345";

    private static final String IV = "67baweiyidong899";

    /**
     *  加密
     * @param passWord
     * @return
     * @throws Exception
     */
    public  String encrypt(String passWord) {
        try {
            Key keySpec = new SecretKeySpec(KEY.getBytes(), "AES"); //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
            IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//实例化加密类，参数为加密方式，要写全
            cipher.init(Cipher.ENCRYPT_MODE,  keySpec, ivSpec);
            byte [] b = cipher.doFinal(passWord.getBytes());
            String ret = Base64.encode(b);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  解密
     * @param password
     * @return
     * @throws Exception
     */
    public String decrypt(String password){
        try {
            byte [] byte1 = Base64.decode(password);
            IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
            Key key = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,  key, ivSpec);
            byte [] ret = cipher.doFinal(byte1);
            return new String(ret, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void main(String [] args)throws Exception
    {
        String a = encrypt("666666");
        System.err.println("加密后: " + a);
        String b = decrypt(a);
        System.err.println("解密后: " + b);

        String md5= MD5("666666");
        System.err.println("MD5: " + md5);
        Long nowTime = TimeUtil.getNowTime();
        System.err.println("系统时间: " + nowTime);

    }



    /**
     *  MD5加密
     * @param sourceStr
     * @return
     */
    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }


    /**
     * 时间工具类
     * @author  java_feng_gen
     */
    public static class TimeUtil{

        /**
         * 获取当天的开始时间 单位：毫秒
         * @return
         */
        private static Long getStartTime(){
            Calendar todayStart = Calendar.getInstance();
            todayStart.set(Calendar.HOUR, 0);
            todayStart.set(Calendar.MINUTE, 0);
            todayStart.set(Calendar.SECOND, 0);
            todayStart.set(Calendar.MILLISECOND, 0);

            return todayStart.getTime().getTime();

        }

        /**
         * 获取当前系统时间 单位：毫秒
         * @return
         */
        public static Long getNowTime() {
            Calendar todayNow = Calendar.getInstance();
            return todayNow.getTime().getTime();
        }

        /**
         * 获取当天的结束时间 单位：毫秒
         * @return
         */
        private static Long getEndTime(){
            Calendar todayEnd = Calendar.getInstance();
            todayEnd.set(Calendar.HOUR, 23);
            todayEnd.set(Calendar.MINUTE, 59);
            todayEnd.set(Calendar.SECOND, 59);
            todayEnd.set(Calendar.MILLISECOND, 999);
            return todayEnd.getTime().getTime();
        }

    }

}
