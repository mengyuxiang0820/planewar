package com.neuedu.planewar.entity;


import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;

public class BG extends PlaneWarObject {
    public static Image[] images = new Image[10];
    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = ImageUtil.images.get("bg1");
        }
    }
    public BG(){}
    public BG(PlaneWarClient pwc,int x, int y){
        this.x = x;
        this.y = y;
        this.width = images[0].getWidth(null);
        this.height = images[0].getHeight(null);
        this.speed = 10;
    }
    int x1 = 0;
    int x2 = 3072;
    @Override
    public void draw(Graphics g) {
            g.drawImage(images[0],x1,y,null);
            g.drawImage(images[1],x2,y,null);
            move();
    }

    @Override
    public void move() {
        x1 -= speed;
        x2 -= speed;
        if (x1 < -3072){
            x1 = 3060;
        }
        if (x2 < -3072){
            x2 = 3060;
        }
    }
}
