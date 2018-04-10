package com.company;

/**
 * MyTankWar
 *
 * GameFrame.java
 *
 * @version 1.0
 * @author lianggaoquan
 *
 * copyright (c) 2018-4-10 lianggaoquan.All rights reserved.
 *
 */

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

public class GameFrame extends Frame {

    public static int flag = 0;

    Image BackGroundimg = GameUtil.getImage("images/map031.png");

    Tank player = new Tank("images/playertank32.png", 100, 100);

    Random rand = new Random();
    private int min = 10;
    private int max = 160;

    private ArrayList<EnemyTank> initEnemy() {

        int[] tankNum = {2,3,4,5,6};
        int randSize = rand.nextInt(tankNum.length);
        ArrayList<EnemyTank> enemyTankArray = new ArrayList<EnemyTank>();
        EnemyTank e;

        for(int i=0;i<tankNum[randSize];i++){
            e = new EnemyTank("images/enemytank44.png",rand.nextInt(max - min + 1) + min,50);
            enemyTankArray.add(e);
        }

        return enemyTankArray;

    }

    ArrayList<EnemyTank> enemyTankArray = initEnemy();

    private void _start(EnemyTank e) {

        e.setLive(true);

        e.move();
    }

    public void launchFrame() {
        setSize(Constant.WIDTH, Constant.HEIGHT);
        setLocation(200, 200);

        setVisible(true);
        new PaintThread().start();

        player.setLive(true);
        addKeyListener(new KeyMonitor());
        player.move();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });

        for (int i = 0; i < enemyTankArray.size(); i++) {
            new MoveThread(enemyTankArray.get(i));
        }

    }


    private int x = 100, y = 100;

    private void set(EnemyTank enemy){
        enemy.setLive(false);
        enemy.isShoot = true;
        enemy.x = -3;
        enemy.y = -3;
    }

    @Override
    public void paint(Graphics g) {

        //int flag = 0;

        g.drawImage(BackGroundimg, 0, 0, null);
        String info = "Tank War!";
        String info2 = "Game Over";

        if (player.isLive()) {
            player.draw(g);
            printInfo(g,info,20,20,50,Color.GREEN);
        }else{
            printInfo(g,info2,20,20,50,Color.RED);
        }

        //如果开火，且敌方坦克在射程之内，则设为死亡

        for (int i = 0; i < enemyTankArray.size(); i++) {
            EnemyTank enemy = enemyTankArray.get(i);

            if (player.isFire) {
                player.bullet.draw(g);

                if (player.headLeft) {
                    if (player.x - enemy.x < player.bullet.range && player.x - enemy.x > 0 && Math.abs(player.y - enemy.y) < 5) {
                        set(enemy);
                    }
                }

                if (player.headRight) {
                    if (enemy.x - player.x < player.bullet.range && enemy.x - player.x > 0 && Math.abs(player.y - enemy.y) < 5) {
                        set(enemy);
                    }
                }


                if (player.headUp) {
                    if (player.y - enemy.y < player.bullet.range && player.y - enemy.y > 0 && Math.abs(player.x - enemy.x) < 5) {
                        set(enemy);
                    }
                }

                if (player.headDown) {
                    if (enemy.y - player.y < player.bullet.range && enemy.y - player.y > 0 && Math.abs(player.x - enemy.x) < 5) {
                        set(enemy);
                    }
                }


            }
            EnemyTank e = enemyTankArray.get(i);
            e.draw(g);

            if(e.getRect().intersects(player.getRect())){
                player.setLive(false);
            }

            if(!enemy.isLive() || !enemy.appear){
                flag += 1;
            }

        }

        if(flag >= enemyTankArray.size()){
            try{
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
            enemyTankArray = initEnemy();

            for (int i = 0; i < enemyTankArray.size(); i++) {
                new MoveThread(enemyTankArray.get(i));
            }
            flag = 0;
        }

    }

    /**
     * 在窗口上打印信息
     * @param g
     * @param str
     * @param size
     */
    public void printInfo(Graphics g,String str,int size,int x,int y,Color color){
        Color c = g.getColor();
        g.setColor(color);
        Font f = new Font("宋体",Font.BOLD,size);
        g.setFont(f);
        g.drawString(str,x,y);
        g.setColor(c);

    }


    /**
     * 双缓冲
     */
    private Image offScreenImage = null;

    public void update(Graphics g) {
        if (offScreenImage == null)
            offScreenImage = this.createImage(Constant.WIDTH, Constant.HEIGHT);

        Graphics gOff = offScreenImage.getGraphics();

        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    class PaintThread extends Thread {
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(40);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 多线程解决同步问题
     */
    class MoveThread implements Runnable {
        Thread t;
        EnemyTank e;

        MoveThread(EnemyTank e) {
            this.e = e;

            t = new Thread(this, "thread 1");

            t.start();
        }

        @Override
        public void run() {
            try {
                _start(e);

                Thread.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.minusDirection(e);
        }
    }

}