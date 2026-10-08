/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameProject;

import static GameProject.Game.WINDOW_HEIGHT;
import static GameProject.Game.WINDOW_WIDTH;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author com
 */
public class EndScreen extends JPanel implements ActionListener{
    URL bgURL= getClass().getResource("/image/Background2.jpg");
    Image bgImage = new ImageIcon(bgURL).getImage();
    private JButton[] buttonArr = new JButton[2];
    private String Text;
    EndScreen(String text){
        this.Text = text;
        for(int i=0;i<2;i++){
            buttonArr[i] = new JButton();
            buttonArr[i].setBounds(WINDOW_WIDTH/2-250+i*250, WINDOW_HEIGHT/2+80, 200, 50);
            buttonArr[i].setFocusable(false);
            buttonArr[i].setContentAreaFilled(false);
            buttonArr[i].setFont(new Font("Courier New",Font.BOLD,30));
            buttonArr[i].setForeground(Color.white);
            buttonArr[i].setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.white));
            buttonArr[i].addActionListener(this);
            add(buttonArr[i]);
        }
        buttonArr[0].setText("Play Again");
        buttonArr[1].setText("To Menu");
        setLayout(null);
    }
    @Override
    public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(),getHeight(),this);
            g.setFont(new Font("Courier New",Font.BOLD,80));
            g.setColor(Color.YELLOW);
            if("Stage Cleared".equals(Text)){
                g.drawString(Text, WINDOW_WIDTH/3-120, WINDOW_HEIGHT/2-90);
            }
            else{
                g.drawString(Text, WINDOW_WIDTH/3-40, WINDOW_HEIGHT/2-90);
            }
            g.setFont(new Font("Courier New",Font.BOLD,30));
            g.drawString("Score:"+Score.score+"/"+Score.MaxScore, WINDOW_WIDTH/2-160, WINDOW_HEIGHT/2-30);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonArr[0]){
            buttonArr[0].setForeground(Color.gray);
            Score.score = 0;
            WealthFromAbove.Ready();
        }
        else if(e.getSource()==buttonArr[1]){
            buttonArr[1].setForeground(Color.gray);
            Score.score = 0;
            Game.MaxHealth=5;
            WealthFromAbove.ReturnToMenu();
        }
    }
}
