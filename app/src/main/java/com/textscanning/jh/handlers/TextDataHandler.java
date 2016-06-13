package com.textscanning.jh.handlers;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * *
 * * ┏┓      ┏┓
 * *┏┛┻━━━━━━┛┻┓
 * *┃          ┃
 * *┃          ┃
 * *┃ ┳┛   ┗┳  ┃
 * *┃          ┃
 * *┃    ┻     ┃
 * *┃          ┃
 * *┗━┓      ┏━┛
 * *  ┃      ┃
 * *  ┃      ┃
 * *  ┃      ┗━━━┓
 * *  ┃          ┣┓
 * *  ┃         ┏┛
 * *  ┗┓┓┏━━━┳┓┏┛
 * *   ┃┫┫   ┃┫┫
 * *   ┗┻┛   ┗┻┛
 * Created by Hua on 16/6/13.
 */
public class TextDataHandler {

    private final static String CHI = "chi_sim.traineddata";
    private final static String ENG = "eng.traineddata";

    /**
     * 获取sd卡的路径
     *
     * @return 路径的字符串
     */
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取外存目录
        }
        return sdDir.toString();
    }

    /*
    *获取分隔符
    *
    * @return 分隔符
    * */
    public static String getSeparator() {
        return File.separator;
    }

    /*
    * 获取项目根目录
    * */
    public static String getDataPath() {
        return getSDPath() + getSeparator() + "TextScanning";
    }

    /*
    * 获取语言包目录
    * */
    public static String getTextDataPath() {
        return getDataPath() + getSeparator() + "tessdata";
    }

    /*
    * 初始化语言包
    * */
    public static void initTextData(Context context) {
        createFile(getDataPath());
        createFile(getTextDataPath());
        AssetManager am = context.getAssets();
        createFile(am, CHI);
        createFile(am, ENG);
    }

    /*
    * 创建语言包
    * */
    private static void createFile(AssetManager am, String fileName) {
        Log.e("", "初始化 " + fileName);
        String path = getTextDataPath() + getSeparator() + fileName;
        File f = new File(path);
        if (!f.exists()) {
            try {
                InputStream inputStream = am.open(fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = inputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, count);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
                Log.e("", "创建 " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * 创建文件夹
    * */
    private static void createFile(String path) {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

    }

    private static boolean exists(String path) {
        File f = new File(path);
        return f.exists();
    }
}
