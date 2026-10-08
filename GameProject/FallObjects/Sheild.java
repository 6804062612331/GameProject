/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameProject;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author com
 */
public class Sheild extends Square{
    URL URL= getClass().getResource("/image/objects/Sheild.png");
    Image Img = new ImageIcon(URL).getImage();
    @Override
    public void paint(Graphics g){
        g.drawImage(Img, squareXLocation, squareYLocation,squareSize,squareSize, this);
    }
    @Override
    public int generateRandomSquareSize(){
        return squareSize = 50;
    }
    @Override
    public int generateRespawnDelay(){
       return 350 + (rand.nextInt(80)*30);
    } 
    @Override
    void onPlayerTouched() {
        Game.player.protectionTime=400;
    }
}
