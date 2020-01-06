package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlaneWarClient;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;
/*
* 爆炸效果
* */
public class Explode extends PlaneWarObject{
    // 爆炸效果
    public static Image[] images = new Image[9];
    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = ImageUtil.images.get("boom_min"+(i+1));
        }
    }
    public Explode(){}
    public Explode(PlaneWarClient pwc,int x,int y){
        this.pwc = pwc;
        this.x = x;
        this.y = y;
        this.width = images[0].getWidth(null);
        this.height = images[0].getHeight(null);
    }
    int count = 0;
    @Override
    public void draw(Graphics g) {
            if (count > 7){
                count = 0;
                pwc.explodes.remove(this);
                return;
            }
            count++;
            g.drawImage(images[count],x,y,null);
        }
}
