/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameProject;

/**
 *
 * @author com
 */
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class Square extends JPanel {
    protected int squareXLocation;
    protected int squareSize;
    protected int squareYLocation = -squareSize;
    protected int fallSpeed;
    protected int respawnDelay = 0;
    protected boolean waitingToRespawn = false;
    Random rand = new Random();
    
    public int generateRandomXLocation(){
        return squareXLocation = rand.nextInt(200, Game.WINDOW_WIDTH - squareSize-200);
    }

    public int generateRandomSquareSize(){
        return squareSize = 60;
    }

    public int generateRandomFallSpeed(){
        return fallSpeed = 2;
    }
   public int generateRespawnDelay(){
       return 30 + (rand.nextInt(40)*30);
   } 
    @Override
    public void paint(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(squareXLocation,squareYLocation,squareSize,squareSize);
    }

    public Square(){
        generateRandomXLocation();
        generateRandomSquareSize();
        generateRandomFallSpeed();
    }
    boolean playerTouched(){
        Rectangle squareHitbox = new Rectangle(
            squareXLocation,
            squareYLocation,
            squareSize,
            squareSize
        );

        Rectangle playerHitbox = new Rectangle(
            Game.player.xActor+30,
            Game.player.yActor+30,
            Player.WIDTH-30,
            Player.HEIGHT-30
        );

    return squareHitbox.intersects(playerHitbox);
    }
    boolean groundTouched(){
        return squareYLocation >= Game.WINDOW_HEIGHT - 270;
    }
    void update() {
         boolean touched = playerTouched();
         boolean fell = groundTouched();
        // Square reached the ground
        if ((fell||touched)&&!waitingToRespawn) {
            if (touched) onPlayerTouched();
            if (fell) onFell();
            waitingToRespawn = true;
            respawnDelay = generateRespawnDelay();
            // Move it off screen while waiting
            squareXLocation = Game.WINDOW_WIDTH+100;
            squareYLocation = Game.WINDOW_HEIGHT + 100;
        }
        if (waitingToRespawn) {
            respawnDelay--;
            if (respawnDelay <= 0) {
                generateRandomXLocation();
                generateRandomFallSpeed();
                generateRandomSquareSize();
                squareYLocation = -squareSize;
                waitingToRespawn = false;
            }

            return;
    }

    // Move square down
    squareYLocation += fallSpeed;
    }
    void onPlayerTouched(){}
    void onFell(){}
}