package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlaneWarClient;

import java.awt.*;

/*
*项目中所有的父类
*提取公有属性
**/
public abstract class PlaneWarObject implements MoveAble,DrawAble{
    /*
    *图片的属性
    * */
    public int x;
    public int y;
    public Image image;
    public int width;
    public int height;
    public int speed;
    public PlaneWarClient pwc;
    /*
    * 重写方法  不写方法体
    * 子类继承是只需要重写需要的方法
     * （适配器设计模式  “只拿需要的”）
    * */
    @Override
    public void move() {
    }

    @Override
    public void draw(Graphics g) {
    }

    public Rectangle getRectangle(){
        return new Rectangle(x,y,width,height);
    }

}
