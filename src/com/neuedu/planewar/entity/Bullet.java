package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.MusicUtil;
import com.neuedu.planewar.content.Constant;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;
import java.util.List;
import java.util.Random;

/*
* 调停者设计模式: 在一个类中要改变其他类的成员变量的值，这个类要持有这个类的引用
*
* */
public class Bullet extends PlaneWarObject{
    static Image[] imgs = new Image[100];
    // 表示敌我的变量 1为我方，0为敌方
    int good;
    // 表示boss和小怪的布尔变量 true为boss，false为小怪
    Random r = new Random();
    public Bullet (){}
    public  Bullet(PlaneWarClient pwc,int x, int y,int good){
        this.good = good;
        this.pwc = pwc;
        this.x = x;
        this.y = y;
        // 火凤凰技能
        if (good == 1){
                for (int i=0;i<5;i++){
                    imgs[i] = ImageUtil.images.get("skill_fire"+(i+1));
                }
            this.width = imgs[0].getWidth(null);
            this.height = imgs[0].getHeight(null);
        }else if (good == 0){
            // 红色子弹
            this.image = ImageUtil.images.get("red");
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
        }/*else {
            this.image = ImageUtil.images.get("boss_bullet");
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
        }*/

        this.speed = 30;
    }

    int count = 0;
    @Override
    public void draw(Graphics g) {  //每40毫秒画一次
        // 画火凤凰技能
        if (good==1){
            if (count > 4){
                count = 0;
            }
            g.drawImage(imgs[count],x + 120,y - 10,null);
            count++;
        }else if (good==0){
            g.drawImage(image,x-45,y+30,null);
        }
        move();
    }

    @Override
    public void move() {
        // 当good=1时为己方，当good=0时为敌方
        if (good==1){
            x += speed;
        }else if (good==0){
            x -= speed;
        }
        outOfBounds();
    }

    private void outOfBounds(){
        if (x < -500 || x > Constant.FRAME_WIDTH + 500 || y < -500||y > Constant.FRAME_HEIGHT){
            this.pwc.bullets.remove(this);
        }
    }
    private boolean hitEnemyPlane(EnemyPlane enemyPlane){
        if (this.good != enemyPlane.good && this.getRectangle().intersects(enemyPlane.getRectangle())){
            // 子弹打到之后，移除子弹和敌机
            enemyPlane.HP -= 21;
            if (enemyPlane.HP<0){
                this.pwc.enemyPlanes.remove(enemyPlane);
                // 敌方飞机死亡时爆炸效果
                Explode e = new Explode(pwc,enemyPlane.x-10,enemyPlane.y-10);
                // 爆炸音效
                new MusicUtil("com/neuedu/planewar/video/爆炸2.mp3").start();
                pwc.explodes.add(e);
                // 道具的爆率为 60%
                if (r.nextInt(100) > 70){
                    // 出道具
                    Item item = new Item(pwc,enemyPlane.x,enemyPlane.y,r.nextInt(3));
                    this.pwc.items.add(item);
                    // 掉落道具音效
                    new MusicUtil("com/neuedu/planewar/video/道具掉落.mp3").start();
                }
            }
            pwc.bullets.remove(this);
            return true;
        }else {
            return false;
        }
    }
    public boolean hitMyPlane(Plane myPlane){
        if (this.good != myPlane.good && this.getRectangle().intersects(myPlane.getRectangle())){
            if (myPlane.HP>0){
                myPlane.setHP(myPlane.getHP() - 10);
            }
            if (myPlane.getHP() == 0 && pwc.boss.HP > 0){
                new MusicUtil("com/neuedu/planewar/video/可惜不是你.mp3").start();
                Explode e = new Explode(pwc,myPlane.x-10,myPlane.y-10);
                pwc.explodes.add(e);
            }
            // 移除子弹
            pwc.bullets.remove(this);
            return true;
        }else {
            return false;
        }
    }
    // 攻击boss类
    public boolean hitBoss(Boss boss){
        if (this.good != boss.good && this.getRectangle().intersects(boss.getRectangle())){
            // 控制boss掉血速度
            if (boss.HP >= 3000 && boss.HP <= 10000){
                boss.setHP(boss.getHP() - 10);
            }
            if (boss.HP < 3000 && boss.HP >= 0){
                boss.setHP(boss.getHP() - 40);
            }
            if (boss.getHP() <0){
                // 当 boss 爆炸是添加胜利音效
                new MusicUtil("com/neuedu/planewar/video/任务完成.mp3").start();
                Explode e = new Explode(pwc,boss.x-10,boss.y-10);
                pwc.explodes.add(e);
            }
            // 移除子弹
            pwc.bullets.remove(this);
            return true;
        }else {
            return false;
        }
    }
    public boolean hitEnemyPlane(List<EnemyPlane> enemyPlanes){
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane ep = enemyPlanes.get(i);
            if (hitEnemyPlane(ep)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        long i=0xfffL;
        double i2=0.9239d;
        System.out.println(i+" "+i2);
    }
}
