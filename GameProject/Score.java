/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameProject;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author com
 */
public class Score extends JPanel{
    public static int MaxScore=500;
    public static int score=0;
    Score(){
        score = 0;
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.setFont(new Font("Courier New",Font.BOLD,50));
        g.setColor(Color.WHITE);
        g.drawString("Score:"+score+"/"+MaxScore, 30, 50);
    }
}
