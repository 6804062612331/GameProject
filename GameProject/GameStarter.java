/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
/**
 *
 * @author com
 */
public class GameStarter extends JPanel{
    private URL[] urlArr = new URL[7];
    private Image[] imgArr = new Image[7];
    private String[] Path = {"Background3.jpg","player/Player.png","PressToStart.png","UI/Control.png","UI/BombUI.png","UI/MeteoUI.png","UI/PoisonUI.png"};
    private Timer timer = new Timer(10, new TimerListener());
    private int yBG=0; boolean Gamestarted=false;
    public static int pickedStage=0;
    private class TimerListener implements ActionListener {
        @Override /** Handle the action event */
        public void actionPerformed(ActionEvent e) {
          update();
          repaint();
        }
    }
    public void update(){
        if(yBG>-593)yBG-=2;
        else{
            WealthFromAbove.StartGame();
            timer.stop();
        }
    }
    
    GameStarter(){
        for(int i=0;i<7;i++){
            String path = "/image/" + Path[i];

            urlArr[i] = getClass().getResource(path);

            if (urlArr[i] == null) {
                System.out.println("IMAGE NOT FOUND: " + path);
                continue;
            }

            imgArr[i] = new ImageIcon(urlArr[i]).getImage();
            
        }
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(!Gamestarted){
                    timer.start();
                    Gamestarted = true;
                }
            }
        
        });
    }
     @Override
    public void paintComponent(Graphics graphics) { 
        super.paintComponent(graphics);
        graphics.drawImage(imgArr[0], 0, yBG, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT+555,this);
        graphics.drawImage(imgArr[1], Game.WINDOW_WIDTH/2, Game.WINDOW_HEIGHT+245+yBG, Player.WIDTH, Player.HEIGHT,this);
        graphics.drawImage(imgArr[2], Game.WINDOW_WIDTH/2-550, 300+yBG, 1100, 120,this);
        if(!Gamestarted){
            graphics.drawImage(imgArr[3], Game.WINDOW_WIDTH/2-550, 500+yBG, 500, 220,this);
            switch (pickedStage) {
                case 1:graphics.drawImage(imgArr[4], Game.WINDOW_WIDTH/2-100, 430+yBG, 650, 470,this);break;
                case 2:graphics.drawImage(imgArr[5], Game.WINDOW_WIDTH/2-100, 430+yBG, 650, 470,this);break;
                case 3:graphics.drawImage(imgArr[6], Game.WINDOW_WIDTH/2-100, 430+yBG, 650, 470,this);break;
                default:break;
            }
        }
        
        graphics.setFont(new Font("Courier New",Font.BOLD,20));
        graphics.setColor(Color.white);
        graphics.drawString("Only Rule: Don't let money fall", Game.WINDOW_WIDTH/2-250, Game.WINDOW_HEIGHT/2-30+yBG);
    }
}
