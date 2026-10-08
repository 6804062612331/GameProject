/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameProject;

import static GameProject.Game.WINDOW_HEIGHT;
import static GameProject.Game.WINDOW_WIDTH;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author com
 */
public class Menu extends JPanel implements ActionListener{
    URL bgURL= getClass().getResource("/image/Background3.jpg");
    Image bgImage = new ImageIcon(bgURL).getImage();
    URL logoURL= getClass().getResource("/image/LOGO.png");
    Image logoImage = new ImageIcon(logoURL).getImage();
    URL volURL;
    private JButton[] buttonArr = new JButton[4];
    private JButton muteButton = new JButton();
    Menu(){
        for(int i=0;i<4;i++){
            if(i!=3)buttonArr[i] = new JButton("Level "+(i+1));
            else buttonArr[i] = new JButton("Custom Lv.");
            buttonArr[i].setBounds(WINDOW_WIDTH/2-150, (WINDOW_HEIGHT/2-50)+i*80, 200, 50);
            buttonArr[i].setFocusable(false);
            buttonArr[i].setContentAreaFilled(false);
            buttonArr[i].setFont(new Font("Courier New",Font.BOLD,30));
            buttonArr[i].setForeground(Color.white);
            buttonArr[i].setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.white));
            buttonArr[i].addActionListener(this);
            add(buttonArr[i]);
        }
        muteButton.setBounds(0, 0, 50, 50);
        muteButton.setContentAreaFilled(false);
        muteButton.addActionListener(this);
        muteButton.setFocusable(false);
        muteButton.setBorderPainted(false);
        add(muteButton);
        setLayout(null);
    }
    
    @Override
    public  void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT+555,this);
        g.drawImage(logoImage, 180, 0, 900, 450,this);
        if(!Audio.Mute)volURL= getClass().getResource("/image/UI/Volume.png");
        else volURL= getClass().getResource("/image/UI/Muted.png");
        Image volImage = new ImageIcon(volURL).getImage();
        g.drawImage(volImage, 0, 0, 50, 50,this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==buttonArr[0]){
            buttonArr[0].setForeground(Color.gray);
            Score.MaxScore=500;
            GameStarter.pickedStage=1;
            Game.gameSetting(7, 2, 1, 0, 0,0);
            WealthFromAbove.Ready();
            Money.maxSpeed=2;
        }
        else if(e.getSource()==buttonArr[1]){
            buttonArr[1].setForeground(Color.gray);
            Score.MaxScore=900;
            GameStarter.pickedStage=2;
            Game.gameSetting(12, 4, 2, 1, 1,0);
            WealthFromAbove.Ready();
            Money.maxSpeed=3;
        }
        else if(e.getSource()==buttonArr[2]){
            buttonArr[2].setForeground(Color.gray);
            Score.MaxScore=1200;
            GameStarter.pickedStage=3;
            Game.gameSetting(16, 4, 3, 2, 2,1);
            WealthFromAbove.Ready();
            Money.maxSpeed=4;
        }
        else if(e.getSource()==buttonArr[3]){
            GameStarter.pickedStage=0;
            buttonArr[3].setForeground(Color.gray);
            WealthFromAbove.CustomMode();
        }
        else if(e.getSource()==muteButton){
            if(!Audio.Mute){
                Audio.Mute = true;
                WealthFromAbove.audio.Mute();
            }
            else {
                WealthFromAbove.audio.UnMute();
                Audio.Mute = false;
            }
        }
    }
}
