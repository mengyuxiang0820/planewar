package com.neuedu.planewar.demo;

import com.neuedu.planewar.common.CommonFrame;
import com.neuedu.planewar.common.FrameUtil;
import com.neuedu.planewar.content.Constant;


import java.awt.*;

public class MyFrame01 extends CommonFrame {
    int x;
    int y;
    int longAxis = 400;
    int shortAxis = 200;
    double theta = 0.0;
    /*
    * x坐标，y坐标
    * 长半轴，短半轴
    * 角度
    * */
    double speed = 0.05;
    static Image image = FrameUtil.getImage("com/neuedu/solar/imgs/地球.png");// 同步加载图片
    @Override
    public void paint(Graphics g) {
    g.drawImage(image,x,y,60,60,null);
    x= Constant.FRAME_WIDTH / 2 + (int)(longAxis*Math.cos(theta));
    y= Constant.FRAME_HEIGHT / 2 + (int)(shortAxis*Math.sin(theta));
    theta += speed;
    }
    public static void main(String[] args) {
        new MyFrame01().loadFrame("椭圆的轨迹");
    }
}
