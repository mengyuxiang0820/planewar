package com.neuedu.planewar.common;

import com.neuedu.planewar.demo.MyFrame;
import com.neuedu.planewar.content.Constant;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CommonFrame extends Frame {
    // 解决图片闪烁的问题，用双缓冲方法解决闪烁问题
    Image backImg = null;

    // 重写update()方法，在窗口的里层添加一个虚拟的图片
    @Override
    public void update(Graphics g) {
        if (backImg == null) {
            // 如果虚拟图片不存在，创建一个和窗口一样大小的图片
            backImg = createImage(Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
        }
        // 获取到虚拟图片的画笔
        Graphics backg = backImg.getGraphics();
        Color c = backg.getColor();
        backg.setColor(Color.WHITE);
        backg.fillRect(0, 0, Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
        backg.setColor(c);
        // 调用虚拟图片的paint()方法，每50ms刷新一次
        paint(backg);
        g.drawImage(backImg, 0, 0, null);
    }
    public void loadFrame(String title)
    {
        this.setTitle(title);
        this.setSize(Constant.FRAME_WIDTH,Constant.FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        new MyThread().start();
    }
    class MyThread extends Thread
    {
        @Override
        public void run() {
            for (;;){ //死循环
                repaint();
                try {
                    Thread.sleep(50);//解决图片失帧
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        MyFrame myframe = new MyFrame("第二个窗口程序");
    }
}
