package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;
import java.util.Random;

public class EnemyPlane extends PlaneWarObject{
    public static Image[] images = new Image[10];
    static {
            images[0] = ImageUtil.images.get("enemyPlane_red");
            images[1] = ImageUtil.images.get("enemyPlane_green");
    }
    int good = 0;
    boolean rank = false;
    // 控制画那个敌方飞机的变量
    int enemys;
    public EnemyPlane(){}
    int HP = 100;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public EnemyPlane(PlaneWarClient pwc, int x, int y, int enemys){
        this.enemys = enemys;
        this.pwc = pwc;
        this.x = x;
        this.y = y;
        this.speed = 10;
        this.width = images[0].getWidth(null);
        this.height = images[0].getHeight(null);
    }
    static Random r = new Random();
    // 当enemyS=10时画红色飞机当enemyS=11时画绿色飞机
    @Override
    public void draw(Graphics g) {
        if (pwc.myPlane.HP > 0){
            if (enemys == 10){
                g.drawImage(images[0],x,y,null);
            }
            if (enemys == 11){
                g.drawImage(images[1],x,y,width,height,null);
            }
            // 敌机是否加血条
            if (pwc.boss.HP > 3000 && pwc.boss.HP<=10000){
                bb.draw(g);
                move();
                // 敌方飞机射击的概率
                if (r.nextInt(1000) >= 990){
                    shoot();
                }
            }else if (pwc.boss.HP <= 3000 && pwc.boss.HP > 0){
                move();
                // 敌方飞机射击的概率
                if (r.nextInt(1000) >= 990){
                    shoot();
                }
            }

        }
    }
    // 敌方飞机的血条
    public BloodBar bb = new BloodBar();
    class BloodBar{
        public void draw(Graphics g) {
            Color c =g.getColor();
            if (HP >=(100 * 0.7) && HP <=100){
                g.setColor(Color.red);
            }else if (HP >=(100 * 0.3) && HP <100 * 0.7){
                g.setColor(Color.orange);
            }else {
                g.setColor(Color.black);
            }
            g.drawRect(x,y - 10,width,6);
            g.fillRect(x,y - 10,(width * (HP / 100)),6);
            g.setColor(c);
        }
    }
    @Override
    public void move() {
        x -= speed;
        y += speed-5;
        if (x<0 && y>768){
            x = (int) (Math.random() * 1000) + 400; // 随机到屏幕范围的x值400到1400
            y = 60;
        }
    }
    public void shoot(){
        Bullet bullet = new Bullet(this.pwc,this.x,this.y,good);
        this.pwc.bullets.add(bullet);
    }
}
