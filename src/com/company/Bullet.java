package com.company;
/**
 * MyTankWar
 *
 * Bullet.java
 *
 * @version 1.0
 * @author lianggaoquan
 *
 * copyright (c) 2018-4-10 lianggaoquan.All rights reserved.
 *
 */
import java.awt.*;

public class Bullet extends GameObject {

    public int range = 70;  //炮弹射程
    public boolean valid;   //炮弹是否还有效
    public Bullet(String imgPath,double x,double y){
        this.img = GameUtil.getImage(imgPath);
        this.x = x;
        this.y = y;
        this.width = 12;
        this.height = 12;
        this.valid = true;
    }

    public void draw(Graphics g){
        g.drawImage(this.img,(int)x,(int)y,null);
    }
}
