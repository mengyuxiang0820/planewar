package com.neuedu.planewar.client;

import com.neuedu.planewar.common.CommonFrame;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.common.MusicUtil;
import com.neuedu.planewar.entity.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PlaneWarClient extends CommonFrame {
    public Plane myPlane = new Plane(this,400,500);
    public List<Bullet> bullets = new ArrayList<>();
    public List<EnemyPlane> enemyPlanes = new ArrayList<>();
    public List<Explode> explodes = new ArrayList<>();
    public List<Item> items = new ArrayList<>();
    public BG bg = new BG(this,0,30);
    public Boss boss = new Boss(this,1366,0);
    public List<EnemyPlane> enemyPlanes1 = new ArrayList<>();
    public PlaneWarClient() {
    }

    @Override
    public void loadFrame(String title) {
        super.loadFrame(title);
        //添加键盘监听器
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                myPlane.keyEventD(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                myPlane.keyReleasedU(e);
            }
        });
    }
    {
        // 随机生成一个屏幕范围的x坐标

        for (int i = 0;i < 30;i++){
            double x10 = Math.random()*1000 + 400;
            EnemyPlane enemyPlane = new EnemyPlane(this, (int)(x10+(i* -10)),(i*60),10);
            enemyPlanes.add(enemyPlane);
            enemyPlanes1.add(enemyPlane);
        }
        for (int i = 0;i < 20;i++){
            double x11 = Math.random()*1000 + 600;
            EnemyPlane enemyPlane = new EnemyPlane(this, (int)(x11+(i* -10)),(i*60),11);
            enemyPlanes.add(enemyPlane);
            enemyPlanes1.add(enemyPlane);
        }
    }
    @Override
    public void paint(Graphics g) {
        Boss boss1 = new Boss();
        // 画背景图片
        bg.draw(g);
        myPlane.draw(g);
        boss1.collide(myPlane);
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.draw(g);
            b.hitEnemyPlane(enemyPlanes);
            b.hitMyPlane(myPlane);
            b.hitBoss(boss);
        }

        for (int i = 0; i < enemyPlanes.size(); i++) {
            enemyPlanes.get(i).draw(g);
        }
        // 当boss血量小于3000时
        if (boss.HP <= 3000 && boss.HP > 0){
            for (int i = 0; i < enemyPlanes1.size(); i++) {
                enemyPlanes1.get(i).draw(g);
            }
            // g.drawImage(ImageUtil.images.get("hint"),183,219,null);
        }
        // 如果敌机剩余数量小于2架画警告图片
            if (enemyPlanes.size() < 2 && enemyPlanes.size() > 0){
                g.drawImage(ImageUtil.images.get("before_boss"),0,0,null);
            }
        // 金牛座boss 当己方飞机血量大于0时才会画boss
        if (myPlane.HP > 0){
            boss.draw(g);
        }
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).draw(g);
        }

        for (int i = 0; i < items.size(); i++) {
            items.get(i).draw(g);
        }
        myPlane.eatItem(items);
        Font f = g.getFont();
        g.setFont(new Font("微软雅黑",Font.BOLD,30));
        g.setColor(Color.red);
        g.drawString("子弹容器的大小: " + bullets.size(),100,100);
        g.drawString("剩余敌机的数量: " + enemyPlanes.size(),100,140);
        g.drawString("主战飞机的血量: " + myPlane.getHP(),100,180);
        // g.drawString("主战飞机的护盾值: " +myPlane.DEF,100,220);
        g.drawString("评分: " +(50 - enemyPlanes.size()) * 100,100,220);
        // g.drawString("时间: " + (boss.endTime - myPlane.startTime) / 1000 +"S",1000,100);
        g.setFont(f);
    }
    public static void main(String[] args) {
        new MusicUtil("com/neuedu/planewar/video/背景音乐.mp3",true).start();
        new PlaneWarClient().loadFrame("飞机大战");
    }
}
