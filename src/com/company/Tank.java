package com.company;
/**
 * MyTankWar
 *
 * Tank.java
 *
 * @version 1.0
 * @author lianggaoquan
 *
 * copyright (c) 2018-4-10 lianggaoquan.All rights reserved.
 *
 */
import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank extends GameObject {
    private boolean left,right,up,down;
    public boolean headLeft,headRight,headUp,headDown;
    private boolean live;
    public Bullet bullet;
    public boolean appearBullet = false;
    public boolean isFire = false;
    public int score;

    public Tank(String imgPath,double x,double y){
        this.img = GameUtil.getImage(imgPath);
        this.x = x;
        this.y = y;
        this.width = 16;
        this.height = 16;
        bullet = new Bullet("images/bullet20.png",this.x,this.y);
        this.score = 0;
    }

    public void draw(Graphics g){
        if(live){
            g.drawImage(img,(int)x,(int)y,null);
            move();
        }

    }

    public void move() {

        if(left){
            x -= speed;
            bullet.x = x;

            headLeft = true;
            headRight = false;
            headUp = false;
            headDown = false;


            this.img = GameUtil.getImage("images/playertank31.png");
        }

        if(right){
            x += speed;
            bullet.x = x;

            headRight = true;
            headLeft = false;
            headDown = false;
            headUp = false;

            this.img = GameUtil.getImage("images/playertank33.png");
        }

        if(up){
            y -= speed;
            bullet.y = y;

            headUp = true;
            headDown = false;
            headLeft = false;
            headRight = false;

            this.img = GameUtil.getImage("images/playertank32.png");
        }

        if(down){
            y += speed;
            bullet.y = y;

            headDown = true;
            headUp = false;
            headRight = false;
            headLeft = false;

            this.img = GameUtil.getImage("images/playertank34.png");
        }

        /**
         * 边框限制
         */

        this.x = this.x < 10 ? 10 : this.x;
        this.x = this.x > 210 ? 210 : this.x;
        this.y = this.y < 30 ? 30 : this.y;
        this.y = this.y > 210 ? 210 : this.y;

    }

    public void fire(){

        isFire = true;

        if(headLeft){

            bullet.x -= bullet.range;

            this.bullet.img = GameUtil.getImage("images/bullet20.png");
        }

        if(headRight){
            bullet.x += bullet.range;
            this.bullet.img = GameUtil.getImage("images/bullet22.png");
        }

        if(headUp){
            bullet.y -= bullet.range;
            this.bullet.img = GameUtil.getImage("images/bullet21.png");
        }

        if(headDown){
            bullet.y += bullet.range;
            this.bullet.img = GameUtil.getImage("images/bullet23.png");
        }
    }

    public void addDirection(KeyEvent e){

        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;

            case KeyEvent.VK_UP:
                up = true;
                break;

            case KeyEvent.VK_DOWN:
                down = true;
                break;

            case KeyEvent.VK_ENTER:
                appearBullet = true;
                fire();
                break;
        }
    }

    /**
     * 按键后，坦克前进一次就停下
     * @param e
     */
    public void minusDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;

            case KeyEvent.VK_UP:
                up = false;
                break;

            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_ENTER:
                try{
                    Thread.sleep(300);
                    appearBullet = false;

                    bullet.x = this.x;
                    bullet.y = this.y;
                    isFire = false;
                }catch (Exception ex){
                    ex.printStackTrace();
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