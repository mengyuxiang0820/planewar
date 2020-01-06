package com.neuedu.planewar.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class FrameUtil {
public static Image getImage(String path){
    URL u = FrameUtil.class.getClassLoader().getResource(path);
    BufferedImage img = null;
    try {
        img = ImageIO.read(u);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return img;
}



}
