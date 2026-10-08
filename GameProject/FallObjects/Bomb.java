/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameProject;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author com
 */
public class Bomb extends Square{
    URL URL= getClass().getResource("/image/objects/Bomb.png");
    Image Img = new ImageIcon(URL).getImage();
    @Override
    public void paint(Graphics g){
        g.drawImage(Img, squareXLocation, squareYLocation,squareSize,squareSize, this);
    }
    @Override
    void onPlayerTouched() {
        if(Game.Health>0&&Game.player.protectionTime==0&&Game.player.recoverTime==0){
            Game.player.isDamaged = true;
            Game.Health--;
        }
    }
}
