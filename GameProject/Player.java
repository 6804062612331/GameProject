/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameProject;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author com
 */
public class Player{
    public int speed;
    public int jumpPower;
    private int jumpFrameCount = 0;
    private String playerState = "Player.png";
    private boolean upPressed = false;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean jumpLocked = false;
    public boolean isDamaged = false,isFallen =false;
    public int hurtAction = -1,protectionTime=0,recoverTime=0,speedEffeft=0;
    public static final int WIDTH = 90;
    public static final int HEIGHT = 130;
    public int xActor=Game.WINDOW_WIDTH/2,yActor=Game.WINDOW_HEIGHT-350;
    Player(){
        speed = 5;
        jumpPower = 6;
        jumpFrameCount = 0;
        isDamaged = false;
        isFallen =false;
        protectionTime=0;
        recoverTime=0;
        hurtAction = -1;
        xActor=Game.WINDOW_WIDTH/2;
        yActor=Game.WINDOW_HEIGHT-350;
    }
    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                playerState = "Player_Jump.png";
                upPressed = true;
                break;

            case KeyEvent.VK_LEFT:
                playerState = "Player_Left.png";
                leftPressed = true;
                break;

            case KeyEvent.VK_RIGHT:
                playerState = "Player_Right.png";
                rightPressed = true;
                break;
        }
    }

    public void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                upPressed = false;
                break;

            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;

            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
        }
    }
    public void paint(Graphics g){
        URL PlayerURL= getClass().getResource("/image/player/"+playerState);
        Image PlayerImg = new ImageIcon(PlayerURL).getImage();
        g.drawImage(PlayerImg, xActor, yActor,WIDTH,HEIGHT, null);
        if(protectionTime>0){
            if(protectionTime<100&&protectionTime%10==0) return;
            URL BarrierURL= getClass().getResource("/image/objects/Barrier.png");
            Image BarrierImg = new ImageIcon(BarrierURL).getImage();
            g.drawImage(BarrierImg, xActor-35, yActor-20,WIDTH+60,HEIGHT+30, null);
        }
    } 
    
    public void update(){
        //Cannot move if damaged
        if(isDamaged){
            recoverTime = 60;
            isDamaged = false;
            hurtAction = 1;
        }
        if(isFallen){
            recoverTime = 120;
            playerState = "Player_Fell.png";
            isFallen = false;
            hurtAction = 2;
        }
        if(recoverTime>0){
            recoverTime--;
            if(hurtAction==1)playerState = "Player_Shocked.png";
            else if(hurtAction ==2)playerState = "Player_Fell.png";
            if(recoverTime==0){
                playerState = "Player.png";
                if(hurtAction == 2)xActor = Game.WINDOW_WIDTH/2;
                if(protectionTime<150)protectionTime=150;
            }
            return;
        }
        if(protectionTime>0){
            protectionTime--;
        }
        if(speedEffeft>0){
            speedEffeft--;
        }
        else speed = 5;
        //Left,Right and Up control
        if (upPressed&&!jumpLocked){
            jumpLocked = true;
            jumpFrameCount = 30;
        }
        if(jumpFrameCount>0){
            yActor -= jumpPower;
            jumpFrameCount--;
        }
        if (leftPressed&&xActor>=0)xActor -= speed;
        if (rightPressed&&xActor<=Game.WINDOW_WIDTH-WIDTH)xActor += speed;
        
        //pull player down after jumping
        if(jumpFrameCount==0){
            if(yActor<Game.WINDOW_HEIGHT-350){
                int gravity = 4;
                yActor += gravity;
            }
            else{
                if(!leftPressed&&!rightPressed){
                    playerState = "Player.png";
                }
                jumpLocked = false;
            }
        }
    }
}
