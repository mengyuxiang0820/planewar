package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;
import java.util.Random;

public class Boss extends PlaneWarObject{
    public static Image image;
    static {
        image = ImageUtil.images.get("boss");
    }
    int good = 0;
    // boss血量
    public int HP = 10000;
    public double MAX_HP = HP;
    public int getHP() {
        return HP;
    }
    public void setHP(int HP) {
        this.HP = HP;
    }
    Random r = new Random();
    public Boss (){}
    public Boss (PlaneWarClient pwc,int x,int y){
        this.pwc = pwc;
        this.x = x;
        this.y = y;
        this.width = image.getWidth(null);
        this.height =image.getHeight(null);
    }
    @Override
    public void draw(Graphics g) {
        if (HP > 0){
            if (pwc.enemyPlanes.size()==0){
                g.drawImage(image,x,y,null);
            }
            move();
            bb.draw(g);
            // boss 射击概率
            if (pwc.enemyPlanes.size() == 0 && r.nextInt(1000) >= 900){
                shoot();
            }
        }else {
            // 击败boss图片
            g.drawImage(ImageUtil.images.get("victory"),183,219,null);
        }
    }
    public void collide(Plane myPlane) {
        if (myPlane.getRectangle().intersects(this.getRectangle())) {
            myPlane.setHP(myPlane.getHP() - 100);
        }
    }
    public BloodBar bb = new BloodBar();
    /*
     * 血条的内部类  不让外部的其它类直接调用   只在被外部类使用
     * 内部类可以直接访问该外部类的所有成员方法和属性，直接使用
     *
     * */
    class BloodBar{
        public void draw(Graphics g) {
            Color c =g.getColor();
            if (HP >=(MAX_HP * 0.7) && HP <=MAX_HP){
                g.setColor(Color.red);
            }else if (HP >=(MAX_HP * 0.3) && HP <MAX_HP * 0.7){
                g.setColor(Color.orange);
            }else {
                g.setColor(Color.black);
            }
            g.drawRect(x,y - 10,width,10);
            g.fillRect(x,y - 10, (int) (width * (HP / MAX_HP)),10);
            g.setColor(c);
        }
    }

    @Override
    public void move() {
        this.speed = 3; // boss的移动速度
        if (pwc.enemyPlanes.size() == 0){
            x -= speed + 4;
            y += speed;
            if (x < 0 && y > 768){
                x = 1366;
                y = 0;
            }
        }
    }

    public void shoot(){
        Bullet bullet = new Bullet(this.pwc,this.x,this.y,good);
        this.pwc.bullets.add(bullet);
    }
}
/*double x2 = Math.pow(this.x,2);  // x的平方
    double y2 = Math.pow(this.y,2);  // y的平方
    double a = 300; // 双扭线的半径
    double theta;
    double p = Math.pow(a,2)*Math.cos(2*theta);*/

/*if (Math.pow(p,2) == x2+y2){
            for (theta = 0;theta<=2*Math.PI;theta++){
                theta++;
            }
        }*/