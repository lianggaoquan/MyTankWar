package com.company;
/**
 * MyTankWar
 *
 * 游戏实体类
 *
 * GameObject.java
 *
 * @version 1.0
 * @author lianggaoquan
 *
 * copyright (c) 2018-4-10 lianggaoquan.All rights reserved.
 *
 */

import java.awt.*;

public class GameObject {
    Image img;
    double x,y;
    int speed = 3;

    int width,height;

    public Rectangle getRect(){
        return new Rectangle((int)x,(int)y,width,height);
    }

    public GameObject(Image img,double x,double y,int width,int height){
        super();
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public GameObject(){

    }
}
