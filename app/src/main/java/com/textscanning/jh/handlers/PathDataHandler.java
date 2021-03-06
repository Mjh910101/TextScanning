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
public class PathDataHandler {

    private final static String CHI = "chi_sim.traineddata";
    private final static String ENG = "eng.traineddata";
    private final static String NOMEDIA = ".nomedia";

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
    * 获取图片目录
    * */
    public static String getImagePath() {
        return getDataPath() + getSeparator() + "images";
    }

    /*
    * 获取图片数据目录
    * */
    public static String getImageDataPath() {
        return getDataPath() + getSeparator() + "imagesData";
    }


    public static String getImageNomediaPath() {
        return getImagePath() + getSeparator() + NOMEDIA;
    }

    public static String getImageDataNomediaPath() {
        return getImageDataPath() + getSeparator() + NOMEDIA;
    }

    /*
    * 初始化语言包
    * */
    public static void initTextData(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                createFolder(getDataPath());
                createFolder(getTextDataPath());
                createFolder(getImagePath());
                createFolder(getImageDataPath());
                createFile(getImageNomediaPath());
                createFile(getImageDataNomediaPath());
                AssetManager am = context.getAssets();
                createFile(am, CHI);
                createFile(am, ENG);
            }
        }).start();
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
    * 创建文件
    * */
    private static void createFile(String path) {
        File f = new File(path);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    * 创建文件夹
    * */
    private static void createFolder(String path) {
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
