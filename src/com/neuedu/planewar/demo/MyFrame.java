package com.neuedu.planewar.demo;

import com.neuedu.planewar.content.Constant;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// awt包含了和Java中生成窗口的类
public class MyFrame extends Frame {
    public MyFrame(String title){
        // 设置宽高
        this.setSize(Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
        // 设置位置
        // this.setLocation(30,30);
        // 设置可见性
        this.setVisible(true);
        // 设置标题
        this.setTitle(title);
        // 设置关闭窗口实现
        // 使用匿名内部类
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { System.exit(0);
            }
        });//jdk1.8  lambda表达式
        // 设置改变窗口大小的方法(默认可变)
        this.setResizable(false);
        // 设置窗口居中
        this.setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        MyFrame myframe = new MyFrame("");
    }
    // 图形界面
    @Override
     public void paint(Graphics g) {
        // 画矩形
       // g.drawRect(100,100,400,200);
        // 画线
        // g.drawLine(100,100,300,300);
        // 画圆形
        // g.drawOval(100,100,400,200);
        // 获取系统颜色
         Color c= g.getColor();
        // 设置颜色
         g.setColor(new Color(255,0,0));
        // 圆角矩形
        // g.fillRoundRect(200,200,400,200,5,5);
        // 画多边形
        // int[] x = {100,300,500,900,122,190};
        // int[] y = {100,277,315,775,150,331};
        // g.drawPolygon(x,y,6);
        // 画图片
        // 还原系统颜色
         g.setColor(c);
    }
}
//         g.drawImage(FrameUtil.getImage("com/neuedu/solar/imgs/背景2.jpg"),0,0,Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT,null);