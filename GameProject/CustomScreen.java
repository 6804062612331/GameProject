/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameProject;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class CustomScreen extends JPanel implements ActionListener{
    URL bgURL= getClass().getResource("/image/Background3.jpg");
    Image bgImage = new ImageIcon(bgURL).getImage();
    private int N = 9;
    private JButton[] buttonArr = new JButton[N];
    private JTextField[] fieldArr = new JTextField[N];
    private JButton Submit = new JButton("Start");
    private JButton Return = new JButton("Menu");
    private String[] nameArr ={"MoneyAmount","BombAmount","MedkitAmount","MeteoriteAmount","SheildAmount","PoisonAmount","MaxHealth","MaxFallSpeed(>1)","Score to Win"};
    CustomScreen(){
        for(int i=0;i<N;i++){
            buttonArr[i] = new JButton(nameArr[i]);
            if(i<5)buttonArr[i].setBounds(50, 50+i*100, 350, 50);
            else buttonArr[i].setBounds(600, 50+(i-5)*100, 350, 50);
            buttonArr[i].setFocusable(false);
            buttonArr[i].setContentAreaFilled(false);
            buttonArr[i].setFont(new Font("Courier New",Font.BOLD,30));
            buttonArr[i].setForeground(Color.white);
            buttonArr[i].setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.white));
            fieldArr[i] = new JTextField();
            if(i<5)fieldArr[i].setBounds(450, 50+i*100, 80, 50);
            else fieldArr[i].setBounds(1000, 50+(i-5)*100, 80, 50);
            fieldArr[i].setFont(new Font("Courier New",Font.BOLD,30));
            add(buttonArr[i]);
            add(fieldArr[i]);
        }
        Submit.setBounds(Game.WINDOW_WIDTH/2-200, 600, 350, 50);
        Submit.setFont(new Font("Courier New",Font.BOLD,50));
        Submit.setForeground(Color.white);
        Submit.setContentAreaFilled(false);
        Submit.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.white));
        Submit.addActionListener(this);
        
        Return.setBounds(Game.WINDOW_WIDTH/2-200, 700, 350, 50);
        Return.setFont(new Font("Courier New",Font.BOLD,50));
        Return.setForeground(Color.white);
        Return.setContentAreaFilled(false);
        Return.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.white));
        Return.addActionListener(this);
        add(Submit);
        add(Return);
        setLayout(null);
    }
    @Override
    public  void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT+555,this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean Error=false;
        int[] Amounts = new int[N];
        if(e.getSource()==Return){
            WealthFromAbove.ReturnToMenu();
        }
        if(e.getSource()==Submit){
            for(int i=0;i<N;i++){
                String currText = fieldArr[i].getText();
                if(!currText.isEmpty()){
                    String input = "";
                    try {
                      if(Integer.parseInt(currText)>=0)Amounts[i] = Integer.parseInt(currText);
                      else {
                          System.err.println(nameArr[i]+"Can't be lower than 0");
                          Error=true;
                      }
                    }catch(NumberFormatException err) {
                      System.err.println(nameArr[i]+" is not an int value"); 
                      Error=true;
                    }
                }
                else{
                    System.err.println("Please Put in the valid values for "+nameArr[i]);
                    Error=true;
                }
            }
            if(Error) return;
                Game.MaxHealth=Amounts[N-3];
                if(Amounts[N-2]>1)Money.maxSpeed=Amounts[N-2];
                else Money.maxSpeed=2;
                Score.MaxScore=Amounts[N-1];
                Game.gameSetting(Amounts[0], Amounts[1], Amounts[2], Amounts[3], Amounts[4],Amounts[5]);
                WealthFromAbove.Ready();
        }
        
    }
}
