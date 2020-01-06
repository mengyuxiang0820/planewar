package com.neuedu.planewar.common;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageUtil {
    /*
    * 用
    * Map<key,value> 容器装图片
    * */
    public static Map<String, Image> images = new HashMap<>();
    static {
        for (int i=0;i<6;i++){
            // 己方飞机黄色战机图片组
            images.put("myPlane_ylo"+(i+1), FrameUtil.getImage("com/neuedu/planewar/imgs/yellow/黄色战机"+(i+1)+".png"));
        }
            // 己方J键普通攻击
        for (int i = 0; i < 20; i++) {
            images.put("myPlane_J"+(i+1), FrameUtil.getImage("com/neuedu/planewar/imgs/bullet/bullet_my/red.png"));
        }
        // 己方L键火凤凰技能
        for (int i = 0; i < 5; i++) {
            images.put("skill_fire"+(i+1), FrameUtil.getImage("com/neuedu/planewar/imgs/skill_fire/火凤"+(i+1)+".png"));
        }
            // 己方U键蝙蝠技能
        
            // 敌方红色飞机
        images.put("enemyPlane_red", FrameUtil.getImage("com/neuedu/planewar/imgs/enemyPlane/红色敌机.png"));
        // 敌方墨绿色飞机
        images.put("enemyPlane_green", FrameUtil.getImage("com/neuedu/planewar/imgs/enemyPlane/墨绿战机-小.png"));
        // 金牛座boss出现警告
        images.put("before_boss", FrameUtil.getImage("com/neuedu/planewar/imgs/enemyPlane/before_boss.png"));
        // 金牛座boss
        images.put("boss", FrameUtil.getImage("com/neuedu/planewar/imgs/enemyPlane/金牛座boss.png"));
        // boss子弹
        images.put("boss_bullet", FrameUtil.getImage("com/neuedu/planewar/imgs/bullet/bullet_em/bullet_boss.png"));
        // 敌方最小战机爆炸效果
        for (int i = 0; i < 8; i++) {
            images.put("boom_min"+(i+1), FrameUtil.getImage("com/neuedu/planewar/imgs/boom/boom_em/boom_min0"+(i+1)+".png"));
        }
        // 敌方子弹发射
        images.put("red", FrameUtil.getImage("com/neuedu/planewar/imgs/bullet/bullet_my/red.png"));
        // 护盾道具
        images.put("shield", FrameUtil.getImage("com/neuedu/planewar/imgs/item/光盾.png"));
        // 血量道具
        images.put("HP", FrameUtil.getImage("com/neuedu/planewar/imgs/item/爱心.png"));
        // 火力升级道具
        images.put("UP_J", FrameUtil.getImage("com/neuedu/planewar/imgs/item/火力升级.png"));
        // 背景图片加载
        for (int i=0;i<2;i++){
            images.put("bg"+(i+1), FrameUtil.getImage("com/neuedu/planewar/imgs/bg"+(i+1)+".png"));
        }
        // 游戏结束界面
        images.put("gameOver", FrameUtil.getImage("com/neuedu/planewar/imgs/任务失败.png"));
        // 击败boss胜利界面
        images.put("victory", FrameUtil.getImage("com/neuedu/planewar/imgs/任务完成.png"));
        // 攻击boss提示
        images.put("hint", FrameUtil.getImage("com/neuedu/planewar/imgs/血量警告.png"));
    }
}
