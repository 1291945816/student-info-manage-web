package controller.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: Hps
 * @date: 2020/5/3 8:28
 * @description: 提供MD5加密实现
 */
public class MD5Utils {

    public static String getMD5String(final String str){
        MessageDigest md5=null;
        try {
            md5=MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "获取MD5实例异常";
        }
        char[] charArray=str.toCharArray();

        byte[] byteArray=new byte[charArray.length];
        //开始加密
        for (int i = 0; i < charArray.length ; i++) {
            byteArray[i]=(byte)charArray[i];
        }
        byte[] digest = md5.digest(byteArray);
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < digest.length ; i++) {
            int var=digest[i] & 0xff;
            if(var < 16)
                stringBuilder.append("0");
            stringBuilder.append(Integer.toHexString(var));
        }
        return stringBuilder.toString();

    }

}
