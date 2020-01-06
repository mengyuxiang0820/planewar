package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.content.Constant;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;
/*
* 敌机爆炸后的道具类
*
* */
public class Item extends PlaneWarObject{
    // 随机生成 0到2PI 的 随机数
    double theta = Math.random() * (Math.PI * 2);
    public Item(){}
    int type;
    public Item(PlaneWarClient pwc,int x,int y,int type){
        this.pwc = pwc;
        this.x = x;
        this.y = y;
        this.speed = 5;
        this.image = ConFirByType(type);
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    private Image ConFirByType(int type) {
        switch (type){
            case 0:
                this.image = ImageUtil.images.get("HP");
                break;
            case 1:
                this.image = ImageUtil.images.get("shield");
                break;
            case 2:
                this.image = ImageUtil.images.get("UP_J");
                break;
            default:
                break;
        }
        return image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,x,y,null);
        move();
    }

    @Override
    public void move() {
        // 碰到边界后x,y的反弹公式
        x += (int) (speed * Math.cos(theta));
        y += (int) (speed * Math.sin(theta));
        /*
        * 弹上下边
        *
        * */
        if (y <= 30 || y >= Constant.FRAME_HEIGHT){
            theta = -theta;
        }
        if (x <= 0 || x >= Constant.FRAME_WIDTH){
            theta = Math.PI -theta;
        }
    }
}
