/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameProject;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 *
 * @author com
 */
public class WealthFromAbove {
    static JFrame frame = new JFrame();
    static Menu menu = new Menu();
    static Audio audio = null;
    public static void main(String[] args){
        frame.add(menu);
        menu.setFocusable(true);
        frame.setVisible(true);
        frame.setSize(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Wealth From Above.Game");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        try {
            audio = new Audio();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            System.getLogger(WealthFromAbove.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        audio.Mute();
        audio.getClip().start();
    }
    public static void ChangeScreen(JPanel panel){
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
    public static void Ready(){
        GameStarter starter = new GameStarter();
        starter.setFocusable(true);
        ChangeScreen(starter);
        starter.requestFocusInWindow();
    }
    public static void StartGame(){
        Game game = new Game();
        game.setFocusable(true);
        ChangeScreen(game);
        game.requestFocusInWindow();
    }
    public static void ReturnToMenu(){
        Menu menu = new Menu();
        menu.setFocusable(true);
        ChangeScreen(menu);
        menu.requestFocusInWindow();
    }
    public static void EndGame(String Text){
        EndScreen end = new EndScreen(Text);
        end.setFocusable(true);
        ChangeScreen(end);
        end.requestFocusInWindow();
    }
    public static void CustomMode(){
        CustomScreen cus = new CustomScreen();
        cus.setFocusable(true);
        ChangeScreen(cus);
        cus.requestFocusInWindow();
    }
    
}

