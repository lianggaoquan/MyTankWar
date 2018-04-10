package com.company;
/**
 * MyTankWar
 *
 * GameUtil.java
 *
 * @version 1.0
 * @author lianggaoquan
 *
 * copyright (c) 2018-4-10 lianggaoquan.All rights reserved.
 *
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class GameUtil {

    private GameUtil() {

    };

    public static Image getImage(String path) {
        URL u = GameUtil.class.getClassLoader().getResource(path);
        BufferedImage img = null;
        try {
            img = ImageIO.read(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

}