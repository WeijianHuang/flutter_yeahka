package org.bigbug.flutter_yeahka.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {
    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss  *   * @param dateDate  * @return
     */
    public static String dateToStrLong(long date) {
        Date dateDate = new Date(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    public static String getOrderId() {
        Date dateDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    public static String getCurrentData() {
        Date dateDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
        return formatter.format(dateDate);
    }

    public static String getCurrentTime() {
        Date dateDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        return formatter.format(dateDate);
    }

    public static String getAmountString(long amount) {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        String str = "" + nf.format((double) amount / (double) 100);
        int index = str.lastIndexOf(".");
        if (index == -1) {
            str += ".00";
            return str;
        }
        if (str.lastIndexOf(".") == str.length() - 2)
            str += "0";
        return str;
    }

    public static int getAmountOfPenny(String amount) {
        return new BigDecimal(amount).multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 将16进制的String转化为字节数组
     *
     * @param hexString
     * @return
     */
    public static byte[] hexString2bytes(String hexString) {
        hexString = hexString.toUpperCase();
        byte[] b = new byte[hexString.length() / 2];
        char[] hexStringByte = hexString.toCharArray();
        for (int i = 0; i < hexString.length() / 2; i++) {
            byte byteTemp = (byte) ((toByte(hexStringByte[i * 2]) << 4) | toByte(hexStringByte[i * 2 + 1]));
            b[i] = byteTemp;
        }
        return b;
    }

    public static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    public static boolean isActionSupport(Context context, String action){
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> resolveInfo =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo.size() > 0) {
            return true;
        }
        return false;
    }
}
