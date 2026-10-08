/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameProject;

import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;

public class Money extends Square{
    URL bgURL= getClass().getResource("/image/objects/Money.png");
    Image MoneyImg = new ImageIcon(bgURL).getImage();
    public static int maxSpeed;
    @Override
    public int generateRandomFallSpeed(){
        return fallSpeed = rand.ints(1,maxSpeed).findFirst().getAsInt();
    }
    @Override
    public void paint(Graphics g){
        g.drawImage(MoneyImg, squareXLocation, squareYLocation,squareSize,squareSize-5, this);
    }
    
    @Override
    void onPlayerTouched() {
        Score.score += 25;
    }
    @Override
    void onFell(){
        if(Game.Health>0)Game.Health--;
    }
}
