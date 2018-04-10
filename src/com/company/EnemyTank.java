package com.company;

/**
 * MyTankWar
 *
 * EnemyTank.java
 *
 * @version 1.0
 * @author lianggaoquan
 *
 * copyright (c) 2018-4-10 lianggaoquan.All rights reserved.
 *
 */

import java.awt.*;

public class EnemyTank extends GameObject {
    private boolean live;
    private final int SPEED = 2;
    public boolean appear = true;
    private boolean isMoving = true;
    private final int TIMES = 200;
    public boolean isShoot = false;

    public Bullet bullet;
    private double cache_x,cache_y;

    public EnemyTank(String imgPath,double x,double y){
        this.img = GameUtil.getImage(imgPath);
        this.x = x;
        this.y = y;
        this.width = 16;
        this.height = 16;
        bullet = new Bullet("images/bullet23.png",this.x,this.y);

        cache_x = bullet.x;
        cache_y = bullet.y;
    }

    public void draw(Graphics g){

        if(live && appear){
            g.drawImage(img,(int)x,(int)y,null);
        }
    }

    public void move(){
        if(isMoving){
            for (int i=0;i<TIMES;i++){
                this.y += SPEED;

                this.appear = this.y > 210 ? false : true;

                try{
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

    }

    public void setLive(boolean live){
        this.live = live;
    }

    public boolean isLive(){
        return this.live;
    }

}