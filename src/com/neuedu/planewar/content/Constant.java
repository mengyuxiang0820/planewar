package com.neuedu.planewar.content;


import java.io.IOException;
import java.util.Properties;

public class Constant {

    /*创建加载properties文件的对象*/

    public static Properties prop = new Properties();
    /*static {
        try {
            prop.load(Constant.class.getResourceAsStream("game.properties")); // 字节流加载
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    /*
    * 窗口的宽度
    * */
    public static final int FRAME_WIDTH = 1366;// Integer.parseInt(prop.getProperty("FRAME_WIDTH")); //字符串转换为Int类型(前提可转)
    /*
    * 窗口的高度
    *
    *
    * */
    public static final int FRAME_HEIGHT= 768;// Integer.parseInt(prop.getProperty("FRAME_HEIGHT"));
    // public static final String IMG_PATH_PRE= prop.getProperty("IMG_PATH_PRE");
}
