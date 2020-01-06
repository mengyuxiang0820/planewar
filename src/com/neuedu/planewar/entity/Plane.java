package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.MusicUtil;
import com.neuedu.planewar.content.Constant;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class Plane extends PlaneWarObject{
    static Image[] imgs = new Image[6];
    public long startTime;
    static {
        for (int i=0;i<6;i++){
            imgs[i] = ImageUtil.images.get("myPlane_ylo"+(i+1));
        }
    }


    int good = 1;
    public int HP = 100;
    public double MAX_HP = HP;
    public int DEF = 0;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Plane(){}
    /*
    * 飞机的坐标和位置
    * */
    public Plane(PlaneWarClient pwc,int x,int y){
        this.pwc = pwc;
        this.x = x;
        this.y = y;
        // this.image = ImageUtil.images.get("myPlane_ylo");
        this.width = imgs[0].getWidth(null);
        this.height = imgs[0].getHeight(null);
        this.speed = 10;
    }
    /*public Plane(int x,int y,Image [] images){
        this(x, y);
        this.images = images;
        this.image = FrameUtil.getImage(imgPath);
    }*/
    @Override
    public void move() {
        if (left) x -= speed;
        if (right) x += speed;
        if (up) y -= speed;
        if (down) y += speed;
        outOfBonds();
    }
        int count = 0;
    @Override
    public void draw(Graphics g) {  //每40毫秒画一次
        startTime = System.currentTimeMillis();
        if (HP>0){
            // 己方黄色飞机
            if (count > 5){
                count = 0;
            }
            g.drawImage(imgs[count],x,y,null);
            count++;
            move();
            if (shoot){
                shoot();
            }
            bb.draw(g);
            if (HP<0){
                HP = 0;
            }
        }else {
            g.drawImage(ImageUtil.images.get("gameOver"),183,219,null);
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
                g.setColor(Color.green);
            }else if (HP >=(MAX_HP * 0.3) && HP <MAX_HP * 0.7){
                g.setColor(Color.YELLOW);
            }else {
                g.setColor(Color.red);
            }
            g.drawRect(x,y - 10,width,10);
            g.fillRect(x,y - 10, (int) (width * (HP / MAX_HP)),10);
            g.setColor(c);
        }
    }
    /*
    * 按下方法
    *
    * */
    public boolean left,right,up,down,shoot;
    public void keyEventD(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_L:
                shoot = true;
                break;
        }
    }
    public void keyReleasedU(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_L:
                shoot = false;
                break;
        }
    }

    /*
    * 飞机出界判断
    * */
    private void outOfBonds(){
        if (x <= 0){
            x = 0;
        }
        if (y <= 30){
            y = 30;
        }
        if (x >= Constant.FRAME_WIDTH - this.width){
            x = Constant.FRAME_WIDTH - this.width;
        }
        if (y >= Constant.FRAME_HEIGHT - this.height){
            y = Constant.FRAME_HEIGHT - this.height;
        }
    }
    public void shoot(){
        Bullet bullet = new Bullet(this.pwc,this.x,this.y,good);
        this.pwc.bullets.add(bullet);
        new MusicUtil("com/neuedu/planewar/video/雕鸣.mp3").start();
    }
    /*
    * 吃道具的方法
    */

    public boolean eatItem(Item item) {
        if (this.getRectangle().intersects(item.getRectangle())){
            switch (item.type){
                case 0:
                    this.HP += 20;
                    if (this.HP >= MAX_HP){
                        this.HP = (int) this.MAX_HP;
                        new MusicUtil("com/neuedu/planewar/video/血包.mp3").start();
                    }
                    break;
                case 1:
                    new MusicUtil("com/neuedu/planewar/video/护盾.mp3").start();
                    this.DEF += 100;
                    break;
                case 2:
                    break;
            }
            this.pwc.items.remove(item);
            return true;
        }
        return false;

    }
    public boolean eatItem(List<Item> items){
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (eatItem(item)){
                return true;
            }
        }
        return false;
    }
}
