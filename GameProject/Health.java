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
public class Health extends JPanel{
    public boolean isRed=true;
    public int heartRank;
    private int heartSize=50;
    public Health(int heartRank) {
        this.heartRank = heartRank;
    }
    
    @Override
   public void paint(Graphics g){
        URL HeartURL;
        if(isRed){
            HeartURL= getClass().getResource("/image/Heart_Red.png");
        }
        else{
            HeartURL= getClass().getResource("/image/Heart_Black.png");
        }
        Image HeartImg = new ImageIcon(HeartURL).getImage();
        g.drawImage(HeartImg, (Game.WINDOW_WIDTH-80)-(heartRank%15*heartSize), 12+(heartRank/15*heartSize),heartSize,heartSize-3, null);
    }
}
